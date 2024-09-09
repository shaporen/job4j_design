CREATE TABLE products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
            select into result count
            from products
            where id = u_id;
        end if;
        if tax > 0 THEN
            update products
            set price = price + price * tax;
            select
            into result sum(price)
            from products;
        end if;
        return result;
    end;
$$;

select f_insert_data('product_1', 'producer_1', 20, 40);
select f_insert_data('product_2', 'producer_2', 10, 30);
select f_insert_data('product_3', 'producer_3', 15, 50);

select * from products;

select f_update_data(10, 0, 1);
select f_update_data(10, 0, 2);

select * from products;

create or replace function delete_products_with_zero_count()
returns void
language 'plpgsql'
as $$
begin
    delete from products
    where count = 0;
end;
$$;

select delete_products_with_zero_count();

select * from products;

create or replace procedure delete_product_by_id(product_id integer)
language 'plpgsql'
as $$
begin
    delete from products
    where id = product_id;
end;
$$;

call delete_product_by_id(3);

select * from products;