create table products
(
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price)
VALUES ('product_1', 3, 50);
insert into products (name, count, price)
VALUES ('product_2', 15, 32);
insert into products (name, count, price)
VALUES ('product_3', 8, 115);