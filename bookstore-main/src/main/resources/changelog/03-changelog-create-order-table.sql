--liquibase formatted sql

--changeset liquibase:1
CREATE TABLE orders (
                ID SERIAL PRIMARY KEY,
                customer_id INT NOT NULL,
                opening_timestamp TIMESTAMP NOT NULL,
                closing_timestamp TIMESTAMP,
                total_amount INT NOT NULL,
                status VARCHAR(50) NOT NULL,
                CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);