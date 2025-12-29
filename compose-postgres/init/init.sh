#!/usr/bin/env bash
set -euo pipefail

echo "==> NORTHWINDS init starting (first-time init only)"

: "${POSTGRES_USER:?required}"
: "${POSTGRES_DB:?required}"

: "${ADMIN_DB_USER:?required}"
: "${ADMIN_DB_PASSWORD:?required}"
: "${APP_DB_USER:?required}"
: "${APP_DB_PASSWORD:?required}"
: "${READ_DB_USER:?required}"
: "${READ_DB_PASSWORD:?required}"

DDL_FILE="/docker-entrypoint-initdb.d/10-northwinds_rebuild.sql"
DATA_FILE="/docker-entrypoint-initdb.d/20-northwind_data.sql"

# ----------------------------------------------------------------------
# 1) Prepare tablespace directories (OS-level)
# ----------------------------------------------------------------------
echo "==> Preparing tablespace directory: /var/lib/postgresql/tablespaces"
mkdir -p /var/lib/postgresql/tablespaces/northwind_tables
mkdir -p /var/lib/postgresql/tablespaces/northwind_indices
chown -R postgres:postgres /var/lib/postgresql/tablespaces
chmod 700 /var/lib/postgresql/tablespaces/northwind_tables
chmod 700 /var/lib/postgresql/tablespaces/northwind_indices

# ----------------------------------------------------------------------
# 2) Create roles FIRST (so tablespaces can be owned by ADMIN_DB_USER)
# ----------------------------------------------------------------------
echo "==> Creating roles (if missing)"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "postgres" <<SQL
DO \$\$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = '${ADMIN_DB_USER}') THEN
    CREATE ROLE ${ADMIN_DB_USER} LOGIN PASSWORD '${ADMIN_DB_PASSWORD}';
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = '${APP_DB_USER}') THEN
    CREATE ROLE ${APP_DB_USER} LOGIN PASSWORD '${APP_DB_PASSWORD}';
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = '${READ_DB_USER}') THEN
    CREATE ROLE ${READ_DB_USER} LOGIN PASSWORD '${READ_DB_PASSWORD}';
  END IF;
END
\$\$;
SQL

# ----------------------------------------------------------------------
# 3) Create tablespaces SECOND (cannot be in DO; use psql \gexec)
# ----------------------------------------------------------------------
echo "==> Creating tablespaces (if missing)"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "postgres" <<SQL
SELECT 'CREATE TABLESPACE "NORTHWIND_TABLES" OWNER ${ADMIN_DB_USER} LOCATION ''/var/lib/postgresql/tablespaces/northwind_tables'';'
WHERE NOT EXISTS (SELECT 1 FROM pg_tablespace WHERE spcname = 'NORTHWIND_TABLES')
\gexec

SELECT 'CREATE TABLESPACE "NORTHWIND_INDICES" OWNER ${ADMIN_DB_USER} LOCATION ''/var/lib/postgresql/tablespaces/northwind_indices'';'
WHERE NOT EXISTS (SELECT 1 FROM pg_tablespace WHERE spcname = 'NORTHWIND_INDICES')
\gexec
SQL

# ----------------------------------------------------------------------
# 4) Ensure database is owned by ADMIN_DB_USER
# ----------------------------------------------------------------------
echo "==> Ensuring database owner is ${ADMIN_DB_USER}"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "postgres" \
  -c "ALTER DATABASE \"${POSTGRES_DB}\" OWNER TO ${ADMIN_DB_USER};"

# ----------------------------------------------------------------------
# 5) Run DDL as modern_java_admin
# ----------------------------------------------------------------------
echo "==> Running DDL as ${ADMIN_DB_USER} in db ${POSTGRES_DB}"
PGPASSWORD="${ADMIN_DB_PASSWORD}" psql -v ON_ERROR_STOP=1 \
  --username "${ADMIN_DB_USER}" \
  --dbname "${POSTGRES_DB}" \
  -f "${DDL_FILE}"

echo "==> DDL completed successfully."

# ----------------------------------------------------------------------
# 6) Load seed data (also as admin)
# ----------------------------------------------------------------------
if [[ -f "${DATA_FILE}" ]]; then
  echo "==> Loading seed data: ${DATA_FILE}"
  PGPASSWORD="${ADMIN_DB_PASSWORD}" psql -v ON_ERROR_STOP=1 \
    --username "${ADMIN_DB_USER}" \
    --dbname "${POSTGRES_DB}" \
    -f "${DATA_FILE}"
  echo "==> Seed data loaded successfully."
else
  echo "==> Seed data skipped: ${DATA_FILE} not found."
fi

echo "==> NORTHWINDS init finished."
