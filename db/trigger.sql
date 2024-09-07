CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

// пункт 1
CREATE OR REPLACE FUNCTION tax_on_insert()
    RETURNS TRIGGER AS
$$
    BEGIN
        UPDATE products
        SET price = price * 1.2
        WHERE id IN (SELECT id FROM inserted);
        RETURN new;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER calculate_tax_on_insert
AFTER INSERT ON products
REFERENCING NEW TABLE AS inserted
FOR EACH STATEMENT
EXECUTE PROCEDURE tax_on_insert();

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 50),
('product_2', 'producer_1', 2, 40),
('product_3', 'producer_1', 3, 20),
('product_4', 'producer_1', 1, 10)

//пункт 2
CREATE OR REPLACE FUNCTION calculate_tax_on_insert_row()
RETURNS TRIGGER AS $$
BEGIN
  NEW.price = NEW.price * 1.2;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER calculate_tax_on_insert_row_trigger
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE calculate_tax_on_insert_row();

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 50),
('product_2', 'producer_1', 2, 40),
('product_3', 'producer_1', 3, 20),
('product_4', 'producer_1', 1, 10)

//пункт 3
CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

CREATE OR REPLACE FUNCTION log_price_change()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO history_of_price (name, price, date)
  VALUES (NEW.name, NEW.price, NOW());
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_price_change
AFTER INSERT ON products
FOR EACH ROW
EXECUTE PROCEDURE log_price_change();

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 50),
('product_2', 'producer_1', 2, 40),
('product_3', 'producer_1', 3, 20),
('product_4', 'producer_1', 1, 10)