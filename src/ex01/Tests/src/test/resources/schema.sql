DROP SCHEMA IF EXISTS tests CASCADE;

CREATE SCHEMA IF NOT EXISTS tests;

CREATE TABLE IF NOT EXISTS tests.product (
    id bigint PRIMARY KEY,
    name varchar(20) NOT NULL,
    price bigint NOT NULL
);