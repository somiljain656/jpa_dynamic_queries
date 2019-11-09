
-- Database: sample_db

-- DROP DATABASE sample_db;

CREATE DATABASE sample_db
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default;

-- Table: public.customer

-- DROP TABLE public.customer

CREATE TABLE public.customer
(
   customer_id SERIAL,
   customer_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
   mobile_number character varying(10) COLLATE pg_catalog."default" NOT NULL,
   address character varying(255) COLLATE pg_catalog."default",
   city character varying(100) COLLATE pg_catalog."default",
   state character varying(100) COLLATE pg_catalog."default",
   country character varying(100) COLLATE pg_catalog."default",
   modified_on timestamp without time zone,
   created_on timestamp without time zone,
   CONSTRAINT customer_pkey PRIMARY KEY (customer_id),
   CONSTRAINT uk_mobile_number UNIQUE (mobile_number)
)
WITH (
   OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.customer
   OWNER to postgres;


-- Table: public.product

-- DROP TABLE public.product

CREATE TABLE public.product
(
   product_id SERIAL,
   product_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
   product_price double precision,
   modified_on timestamp without time zone,
   created_on timestamp without time zone,
   CONSTRAINT product_pkey PRIMARY KEY (product_id)
)
WITH (
   OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.product
   OWNER to postgres;


-- Table: public.orders

-- DROP TABLE public.orders

CREATE TABLE public.orders
(
   order_id SERIAL,
   customer_id bigint NOT NULL,
   product_id bigint NOT NULL,
   created_on timestamp without time zone,
   CONSTRAINT order_pkey PRIMARY KEY (order_id),
   CONSTRAINT order_customer_fk FOREIGN KEY (customer_id)
        REFERENCES public.customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
   CONSTRAINT order_product_fk FOREIGN KEY (product_id)
        REFERENCES public.product (product_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
   OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.orders
   OWNER to postgres;

