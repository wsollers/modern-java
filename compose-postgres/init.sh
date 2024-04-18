#!/bin/bash
#
##!/bin/bash
set -e

CREATE DATABASE "CITIZENS"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA "CITIZENS"
    AUTHORIZATION postgres;

COMMENT ON SCHEMA "CITIZENS"
    IS 'SCHEMA FOR MODERN-JAVA';

CREATE TABLE "CITIZENS"."CITIZEN"
(
    "ID" character varying(36) NOT NULL DEFAULT NULL,
    "FIRST_NAME" character varying(64) DEFAULT NULL,
    "LAST_NAME" character varying(64) DEFAULT NULL,
    "SSN" character varying(11) DEFAULT NULL,
    "AGE" integer DEFAULT NULL,
    PRIMARY KEY ("ID")
);

ALTER TABLE IF EXISTS "CITIZENS"."CITIZEN"
    OWNER to postgres;

COMMENT ON TABLE "CITIZENS"."CITIZEN"
    IS 'TABLE TO HOLD CITIZENS';

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  REVOKE CREATE ON SCHEMA public FROM PUBLIC;
  REVOKE ALL ON DATABASE CITIZENS_DB FROM PUBLIC;
  CREATE ROLE READONLY;
  GRANT CONNECT ON DATABASE CITIZENS_DB TO readonly;
  GRANT USAGE ON SCHEMA myschema TO readonly;
	CREATE USER modern_java_admin WITH PASSWORD 'modern_java_admin';
  CREATE USER modern_java_read WITH PASSWORD 'modern_java_read';
  CREATE USER modern_java_write WITH PASSWORD 'modern_java_write';
	GRANT ALL PRIVILEGES ON DATABASE CITIZENS_DB TO modern_java_admin;
EOSQL

