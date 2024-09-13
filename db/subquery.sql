CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into customers(first_name, last_name, age, country)
values ('Sergey', 'Shaporenko', 36, 'Russia');
insert into customers(first_name, last_name, age, country)
values ('Tatiana', 'Ivanova', 37, 'Kazakhstan');
insert into customers(first_name, last_name, age, country)
values ('Anton', 'Ponomarev', 32, 'Russia');
insert into customers(first_name, last_name, age, country)
values ('Dmitriy', 'Zavialov', 42, 'Ukraine');

insert into orders(amount, customer_id)
values (3, 1), (2, 2);

select * from customers as c
where c.id not in (
    select customer_id
    from orders
);