--liquibase formatted sql

--changeset liquibase:1
CREATE TABLE orderBook (
                            order_id INT NOT NULL,
                            book_id INT NOT NULL,
                            PRIMARY KEY (order_id, book_id),
                            CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id),
                            CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id)
);