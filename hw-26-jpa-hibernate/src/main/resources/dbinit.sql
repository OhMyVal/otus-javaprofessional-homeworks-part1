BEGIN;

CREATE SCHEMA IF NOT EXISTS hiber;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('bread', 10),
('butter', 50),
('milk', 30),
('tea', 20),
('meat', 150),
('cookies', 80),
('cheese', 120),
('coffee', 70),
('tomatoes', 50),
('chocolate', 90);

DROP TABLE IF EXISTS clients CASCADE;
CREATE TABLE clients (id bigserial PRIMARY KEY, first_name VARCHAR(255));
INSERT INTO clients (first_name) VALUES
('Alexander'),
('Bob'),
('Tom'),
('Jim');

DROP TABLE IF EXISTS product_buyers CASCADE;
CREATE TABLE product_buyers (product_id bigint, client_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (client_id) REFERENCES clients (id));
INSERT INTO product_buyers (product_id, client_id) VALUES
(1, 1),
(2, 1),
(4, 1),
(6, 1),
(3, 2),
(7, 2),
(8, 2),
(1, 3),
(4, 3),
(7, 3),
(9, 3),
(10, 3);

COMMIT;