-- =====================================================================
-- NORTHWINDS schema rebuild (Docker entrypoint friendly)
-- Runs inside the database created by POSTGRES_DB
-- Intended to be executed as: modern_java_admin
-- =====================================================================

-- ---------------------------------------------------------------------
-- Tablespaces (must exist before tables / indexes)
-- ---------------------------------------------------------------------


-- Ensure schema exists and is owned by admin
CREATE SCHEMA IF NOT EXISTS NORTHWINDS;
ALTER SCHEMA NORTHWINDS OWNER TO modern_java_admin;

-- Lock down schema from PUBLIC (CIS-ish)
REVOKE ALL ON SCHEMA NORTHWINDS FROM PUBLIC;
GRANT USAGE, CREATE ON SCHEMA NORTHWINDS TO modern_java_admin;
GRANT USAGE ON SCHEMA NORTHWINDS TO modern_java_read;
GRANT USAGE ON SCHEMA NORTHWINDS TO modern_java_write;
REVOKE CREATE ON SCHEMA NORTHWINDS FROM modern_java_read;
REVOKE CREATE ON SCHEMA NORTHWINDS FROM modern_java_write;

-- ---------------------------------------------------------------------
-- Drop tables (dependency-safe)
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS NORTHWINDS.customer_customer_demo;
DROP TABLE IF EXISTS NORTHWINDS.employee_territories;
DROP TABLE IF EXISTS NORTHWINDS.order_details;
DROP TABLE IF EXISTS NORTHWINDS.orders;
DROP TABLE IF EXISTS NORTHWINDS.products;
DROP TABLE IF EXISTS NORTHWINDS.shippers;
DROP TABLE IF EXISTS NORTHWINDS.suppliers;
DROP TABLE IF EXISTS NORTHWINDS.territories;
DROP TABLE IF EXISTS NORTHWINDS.us_states;
DROP TABLE IF EXISTS NORTHWINDS.customers;
DROP TABLE IF EXISTS NORTHWINDS.customer_demographics;
DROP TABLE IF EXISTS NORTHWINDS.categories;
DROP TABLE IF EXISTS NORTHWINDS.region;
DROP TABLE IF EXISTS NORTHWINDS.employees;

-- ---------------------------------------------------------------------
-- Create tables (heap storage in NORTHWIND_TABLES)
-- NOTE: constraints are added later so their indexes can be placed
--       explicitly into NORTHWIND_INDICES.
-- ---------------------------------------------------------------------

CREATE TABLE NORTHWINDS.categories (
                                       category_id smallint NOT NULL,
                                       category_name character varying(15) NOT NULL,
                                       description text,
                                       picture bytea
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.customer_demographics (
                                                  customer_type_id bpchar NOT NULL,
                                                  customer_desc text
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.customers (
                                      customer_id bpchar NOT NULL,
                                      company_name character varying(40) NOT NULL,
                                      contact_name character varying(30),
                                      contact_title character varying(30),
                                      address character varying(60),
                                      city character varying(15),
                                      region character varying(15),
                                      postal_code character varying(10),
                                      country character varying(15),
                                      phone character varying(24),
                                      fax character varying(24)
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.employees (
                                      employee_id smallint NOT NULL,
                                      last_name character varying(20) NOT NULL,
                                      first_name character varying(10) NOT NULL,
                                      title character varying(30),
                                      title_of_courtesy character varying(25),
                                      birth_date date,
                                      hire_date date,
                                      address character varying(60),
                                      city character varying(15),
                                      region character varying(15),
                                      postal_code character varying(10),
                                      country character varying(15),
                                      home_phone character varying(24),
                                      extension character varying(4),
                                      photo bytea,
                                      notes text,
                                      reports_to smallint,
                                      photo_path character varying(255)
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.suppliers (
                                      supplier_id smallint NOT NULL,
                                      company_name character varying(40) NOT NULL,
                                      contact_name character varying(30),
                                      contact_title character varying(30),
                                      address character varying(60),
                                      city character varying(15),
                                      region character varying(15),
                                      postal_code character varying(10),
                                      country character varying(15),
                                      phone character varying(24),
                                      fax character varying(24),
                                      homepage text
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.products (
                                     product_id smallint NOT NULL,
                                     product_name character varying(40) NOT NULL,
                                     supplier_id smallint,
                                     category_id smallint,
                                     quantity_per_unit character varying(20),
                                     unit_price real,
                                     units_in_stock smallint,
                                     units_on_order smallint,
                                     reorder_level smallint,
                                     discontinued integer NOT NULL
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.region (
                                   region_id smallint NOT NULL,
                                   region_description bpchar NOT NULL
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.shippers (
                                     shipper_id smallint NOT NULL,
                                     company_name character varying(40) NOT NULL,
                                     phone character varying(24)
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.orders (
                                   order_id smallint NOT NULL,
                                   customer_id bpchar,
                                   employee_id smallint,
                                   order_date date,
                                   required_date date,
                                   shipped_date date,
                                   ship_via smallint,
                                   freight real,
                                   ship_name character varying(40),
                                   ship_address character varying(60),
                                   ship_city character varying(15),
                                   ship_region character varying(15),
                                   ship_postal_code character varying(10),
                                   ship_country character varying(15)
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.territories (
                                        territory_id character varying(20) NOT NULL,
                                        territory_description bpchar NOT NULL,
                                        region_id smallint NOT NULL
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.employee_territories (
                                                 employee_id smallint NOT NULL,
                                                 territory_id character varying(20) NOT NULL
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.order_details (
                                          order_id smallint NOT NULL,
                                          product_id smallint NOT NULL,
                                          unit_price real NOT NULL,
                                          quantity smallint NOT NULL,
                                          discount real NOT NULL
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.us_states (
                                      state_id smallint NOT NULL,
                                      state_name character varying(100),
                                      state_abbr character varying(2),
                                      state_region character varying(50)
) TABLESPACE "NORTHWIND_TABLES";

CREATE TABLE NORTHWINDS.customer_customer_demo (
                                                   customer_id bpchar NOT NULL,
                                                   customer_type_id bpchar NOT NULL
) TABLESPACE "NORTHWIND_TABLES";

-- ---------------------------------------------------------------------
-- Primary keys (indexes forced into NORTHWIND_INDICES)
-- ---------------------------------------------------------------------

ALTER TABLE NORTHWINDS.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.customer_demographics
    ADD CONSTRAINT customer_demographics_pkey PRIMARY KEY (customer_type_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (employee_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.suppliers
    ADD CONSTRAINT suppliers_pkey PRIMARY KEY (supplier_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.region
    ADD CONSTRAINT region_pkey PRIMARY KEY (region_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.shippers
    ADD CONSTRAINT shippers_pkey PRIMARY KEY (shipper_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.territories
    ADD CONSTRAINT territories_pkey PRIMARY KEY (territory_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.employee_territories
    ADD CONSTRAINT employee_territories_pkey PRIMARY KEY (employee_id, territory_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.order_details
    ADD CONSTRAINT order_details_pkey PRIMARY KEY (order_id, product_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.us_states
    ADD CONSTRAINT us_states_pkey PRIMARY KEY (state_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

ALTER TABLE NORTHWINDS.customer_customer_demo
    ADD CONSTRAINT customer_customer_demo_pkey PRIMARY KEY (customer_id, customer_type_id)
    USING INDEX TABLESPACE "NORTHWIND_INDICES";

-- ---------------------------------------------------------------------
-- Foreign keys (no indexes created automatically by PostgreSQL)
-- ---------------------------------------------------------------------

ALTER TABLE NORTHWINDS.employees
    ADD CONSTRAINT employees_reports_to_fkey
        FOREIGN KEY (reports_to) REFERENCES NORTHWINDS.employees(employee_id);

ALTER TABLE NORTHWINDS.products
    ADD CONSTRAINT products_category_id_fkey
        FOREIGN KEY (category_id) REFERENCES NORTHWINDS.categories(category_id);

ALTER TABLE NORTHWINDS.products
    ADD CONSTRAINT products_supplier_id_fkey
        FOREIGN KEY (supplier_id) REFERENCES NORTHWINDS.suppliers(supplier_id);

ALTER TABLE NORTHWINDS.orders
    ADD CONSTRAINT orders_customer_id_fkey
        FOREIGN KEY (customer_id) REFERENCES NORTHWINDS.customers(customer_id);

ALTER TABLE NORTHWINDS.orders
    ADD CONSTRAINT orders_employee_id_fkey
        FOREIGN KEY (employee_id) REFERENCES NORTHWINDS.employees(employee_id);

ALTER TABLE NORTHWINDS.orders
    ADD CONSTRAINT orders_ship_via_fkey
        FOREIGN KEY (ship_via) REFERENCES NORTHWINDS.shippers(shipper_id);

ALTER TABLE NORTHWINDS.territories
    ADD CONSTRAINT territories_region_id_fkey
        FOREIGN KEY (region_id) REFERENCES NORTHWINDS.region(region_id);

ALTER TABLE NORTHWINDS.employee_territories
    ADD CONSTRAINT employee_territories_employee_id_fkey
        FOREIGN KEY (employee_id) REFERENCES NORTHWINDS.employees(employee_id);

ALTER TABLE NORTHWINDS.employee_territories
    ADD CONSTRAINT employee_territories_territory_id_fkey
        FOREIGN KEY (territory_id) REFERENCES NORTHWINDS.territories(territory_id);

ALTER TABLE NORTHWINDS.order_details
    ADD CONSTRAINT order_details_order_id_fkey
        FOREIGN KEY (order_id) REFERENCES NORTHWINDS.orders(order_id);

ALTER TABLE NORTHWINDS.order_details
    ADD CONSTRAINT order_details_product_id_fkey
        FOREIGN KEY (product_id) REFERENCES NORTHWINDS.products(product_id);

ALTER TABLE NORTHWINDS.customer_customer_demo
    ADD CONSTRAINT customer_customer_demo_customer_id_fkey
        FOREIGN KEY (customer_id) REFERENCES NORTHWINDS.customers(customer_id);

ALTER TABLE NORTHWINDS.customer_customer_demo
    ADD CONSTRAINT customer_customer_demo_customer_type_id_fkey
        FOREIGN KEY (customer_type_id) REFERENCES NORTHWINDS.customer_demographics(customer_type_id);

-- ---------------------------------------------------------------------
-- Optional: recommended indexes on FK columns (placed in NORTHWIND_INDICES)
-- PostgreSQL does NOT auto-create FK indexes; these help JOIN/DELETE performance.
-- ---------------------------------------------------------------------

CREATE INDEX employees_reports_to_idx
    ON NORTHWINDS.employees (reports_to)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX products_category_id_idx
    ON NORTHWINDS.products (category_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX products_supplier_id_idx
    ON NORTHWINDS.products (supplier_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX orders_customer_id_idx
    ON NORTHWINDS.orders (customer_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX orders_employee_id_idx
    ON NORTHWINDS.orders (employee_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX orders_ship_via_idx
    ON NORTHWINDS.orders (ship_via)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX territories_region_id_idx
    ON NORTHWINDS.territories (region_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX employee_territories_employee_id_idx
    ON NORTHWINDS.employee_territories (employee_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX employee_territories_territory_id_idx
    ON NORTHWINDS.employee_territories (territory_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX order_details_order_id_idx
    ON NORTHWINDS.order_details (order_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX order_details_product_id_idx
    ON NORTHWINDS.order_details (product_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX customer_customer_demo_customer_id_idx
    ON NORTHWINDS.customer_customer_demo (customer_id)
    TABLESPACE "NORTHWIND_INDICES";

CREATE INDEX customer_customer_demo_customer_type_id_idx
    ON NORTHWINDS.customer_customer_demo (customer_type_id)
    TABLESPACE "NORTHWIND_INDICES";

-- ---------------------------------------------------------------------
-- Privileges (existing objects)
-- ---------------------------------------------------------------------

REVOKE ALL ON SCHEMA NORTHWINDS FROM PUBLIC;
REVOKE ALL ON ALL TABLES    IN SCHEMA NORTHWINDS FROM PUBLIC;
REVOKE ALL ON ALL SEQUENCES IN SCHEMA NORTHWINDS FROM PUBLIC;
REVOKE ALL ON ALL FUNCTIONS IN SCHEMA NORTHWINDS FROM PUBLIC;

GRANT USAGE, CREATE ON SCHEMA NORTHWINDS TO modern_java_admin;
GRANT USAGE ON SCHEMA NORTHWINDS TO modern_java_read;
GRANT USAGE ON SCHEMA NORTHWINDS TO modern_java_write;

GRANT ALL PRIVILEGES ON ALL TABLES    IN SCHEMA NORTHWINDS TO modern_java_admin;
GRANT ALL           ON ALL SEQUENCES IN SCHEMA NORTHWINDS TO modern_java_admin;

GRANT SELECT ON ALL TABLES IN SCHEMA NORTHWINDS TO modern_java_read;

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA NORTHWINDS TO modern_java_write;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA NORTHWINDS TO modern_java_write;

-- ---------------------------------------------------------------------
-- Default privileges (future objects created by modern_java_admin)
-- ---------------------------------------------------------------------

ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
REVOKE ALL ON TABLES FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
REVOKE ALL ON SEQUENCES FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
REVOKE ALL ON FUNCTIONS FROM PUBLIC;

ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT ALL ON TABLES TO modern_java_admin;
ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT ALL ON SEQUENCES TO modern_java_admin;
ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT ALL ON FUNCTIONS TO modern_java_admin;

ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT SELECT ON TABLES TO modern_java_read;
ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT EXECUTE ON FUNCTIONS TO modern_java_read;

ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO modern_java_write;
ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT USAGE, SELECT ON SEQUENCES TO modern_java_write;
ALTER DEFAULT PRIVILEGES FOR ROLE modern_java_admin IN SCHEMA NORTHWINDS
GRANT EXECUTE ON FUNCTIONS TO modern_java_write;
