--liquibase formatted sql

--changeset liquibase:1
CREATE TABLE customer (
                      ID  SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      balance INT NOT NULL
);
