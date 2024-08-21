CREATE TABLE type(
	id serial primary key,
	name  varchar(255)
);

CREATE TABLE product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name)
values('Колбаса'),
('Сыр'),
('Молоко'),
('Мороженое'),
('Масло');

insert into product(name, type_id, expired_date, price)
values('Колбаса копченая', 1, '2024-10-01', 350.00),
	('Колбаса вареная', 1, '2024-11-01', 400.00),
	('Колбаса сырокопченая',1, '2024-10-15', 420.00),
	('Колбаса домашняя', 1,'2024-12-01', 380.00),
	('Колбаса охотничья',1,'2024-08-01', 450.00),
	('Сыр моцарелла',2,'2024-10-15',700.00),
	('Сыр гауда',2,'2024-12-31',500.00),
	('Сыр плавленный',2,'01-10-2024',250.00),
	('Сыр пармезан',2,'2024-10-01',700.00),
	('Сыр сливочный',2,'2024-11-01',400.00),
	('Молоко коровье',3,'2024-12-31',100.00),
	('Молоко козье',3,'2024-08-01',150.00),
	('Молоко топленое',3,'2024-10-15',90.00),
	('Мороженое шоколадное',4,'2024-10-01',70.00),
	('Мороженое крем-брюле',4,'2024-11-01',80.00),
	('Мороженое эскимо',4,'2024-10-15',95.00),
	('Масло сливочное',5,'2024-10-15',700.00),
	('Масло растительное',5,'2024-11-01',180.00),
	('Масло оливковое',5,'2024-10-01',420.00),
	('Масло топленое',5,'2024-08-01',380.00);

select * from product
where name like '%Мороженое%';

select * from product
where expired_date < current_date;

select t.name from type as t
inner join product as p
	on t.id = p.type_id
	where price = (select max(price) from product);

select t.name, count(p.type_id) from type as t
inner join product as p
	on t.id = p.type_id
    group by t.name;

select * from product as p
inner join type as t
	on t.id = p.type_id
	where t.name in ('Молоко','Сыр');

select t.name, count(p.type_id) from type as t
inner join product as p
	on t.id = p.type_id
	group by t.name
	having count(p.type_id) < 10;

select * from product as p
inner join type as t
	on t.id = p.type_id;
