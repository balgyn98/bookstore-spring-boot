--liquibase formatted sql

--changeset liquibase:1
CREATE TABLE book
(
    ID             SERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    author         VARCHAR(255) NOT NULL,
    published_year DATE         NOT NULL,
    price          INT          NOT NULL
);