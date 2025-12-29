# one-time or when schema changes
docker compose --env-file secrets.env down -v
docker compose --env-file secrets.env build --no-cache
docker compose --env-file secrets.env up -d