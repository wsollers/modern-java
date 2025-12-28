#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

CREATE SCHEMA "CITIZENS"
    AUTHORIZATION postgres;

COMMENT ON SCHEMA "CITIZENS"
    IS 'SCHEMA FOR MODERN-JAVA';

CREATE TABLESPACE "CITIZENS_TBS"
  OWNER postgres
  LOCATION '/data/postgres';

ALTER TABLESPACE "CITIZENS_TBS"
  OWNER TO postgres;

-- Table: CITIZENS.CITIZEN

-- DROP TABLE IF EXISTS "CITIZENS"."CITIZEN";

CREATE TABLE IF NOT EXISTS "CITIZENS"."CITIZEN"
(
    "ID" character varying(36) COLLATE pg_catalog."default" NOT NULL DEFAULT NULL::character varying,
    "FIRST_NAME" character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    "LAST_NAME" character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    "SSN" character varying(11) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    "AGE" integer,
    "CREATED_BY" character varying(64)[] COLLATE pg_catalog."default" NOT NULL DEFAULT NULL::character varying[],
    "CREATED_DATE" timestamp with time zone NOT NULL,
    "LAST_MODIFIED_BY" character varying(64)[] COLLATE pg_catalog."default" NOT NULL DEFAULT NULL::character varying[],
    "LAST_MODIFIED_DATE" timestamp with time zone NOT NULL,
    CONSTRAINT "CITIZEN_pkey" PRIMARY KEY ("ID")
) TABLESPACE "CITIZENS_TBS";

ALTER TABLE IF EXISTS "CITIZENS"."CITIZEN"
    OWNER to postgres;

COMMENT ON TABLE "CITIZENS"."CITIZEN"
    IS 'TABLE TO HOLD CITIZENS';

COMMENT ON COLUMN "CITIZENS"."CITIZEN"."CREATED_BY"
    IS 'USER/SERVICE THAT CREATED THE ENTRY';

COMMENT ON COLUMN "CITIZENS"."CITIZEN"."CREATED_DATE"
    IS 'DATE TIME RECORD CREATED';

COMMENT ON COLUMN "CITIZENS"."CITIZEN"."LAST_MODIFIED_BY"
    IS 'USER/SERVICE THAT LAST UPDATED THE ENTRY';

COMMENT ON COLUMN "CITIZENS"."CITIZEN"."LAST_MODIFIED_DATE"
    IS 'DATE TIME RECORD CREATED';

CREATE TABLE IF NOT EXISTS "CITIZENS"."ADDRESS"
(
    "ID" character varying(36) COLLATE pg_catalog."default" NOT NULL DEFAULT NULL::character varying,
    "STREET_ADDRESS" character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    "CITY" character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    "ZIP_CODE" character varying(12) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    "CREATED_BY" character varying(64)[] COLLATE pg_catalog."default" NOT NULL DEFAULT NULL::character varying[],
    "CREATED_DATE" timestamp with time zone NOT NULL,
    "LAST_MODIFIED_BY" character varying(64)[] COLLATE pg_catalog."default" NOT NULL DEFAULT NULL::character varying[],
    "LAST_MODIFIED_DATE" timestamp with time zone NOT NULL,
    CONSTRAINT "ADDRESS_pkey" PRIMARY KEY ("ID")
) TABLESPACE "CITIZENS_TBS";


ALTER TABLE IF EXISTS "CITIZENS"."ADDRESS"
    OWNER to postgres;

COMMENT ON TABLE "CITIZENS"."ADDRESS"
    IS 'ADDRESS TABLE';

COMMENT ON COLUMN "CITIZENS"."ADDRESS"."CREATED_BY"
    IS 'USER/SERVICE THAT CREATED THE ENTRY';

COMMENT ON COLUMN "CITIZENS"."ADDRESS"."CREATED_DATE"
    IS 'DATE TIME RECORD CREATED';

COMMENT ON COLUMN "CITIZENS"."ADDRESS"."LAST_MODIFIED_BY"
    IS 'USER/SERVICE THAT LAST UPDATED THE ENTRY';

COMMENT ON COLUMN "CITIZENS"."ADDRESS"."LAST_MODIFIED_DATE"
    IS 'DATE TIME RECORD CREATED';



REVOKE CREATE ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON DATABASE CITIZENS_DB FROM PUBLIC;
CREATE ROLE READONLY;
GRANT CONNECT ON DATABASE CITIZENS_DB TO readonly;
GRANT USAGE ON SCHEMA myschema TO readonly;
CREATE USER modern_java_admin WITH PASSWORD 'modern_java_admin';
CREATE USER modern_java_read WITH PASSWORD 'modern_java_read';
CREATE USER modern_java_write WITH PASSWORD 'modern_java_write';
GRANT ALL PRIVILEGES ON DATABASE CITIZENS_DB TO modern_java_admin;

CREATE SCHEMA "NORTHWINDS"
    AUTHORIZATION postgres;

COMMENT ON SCHEMA "NORTHWINDS"
    IS 'SCHEMA FOR MODERN-JAVA';

CREATE TABLESPACE "NORTHWINDS_TBS"
  OWNER postgres
  LOCATION '/data/postgres';

ALTER TABLESPACE "NORTHWINDS_TBS"
  OWNER TO postgres;


SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;



SET default_tablespace = '';

SET default_with_oids = false;



EOSQL
