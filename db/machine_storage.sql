create table car_bodies(
	id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name)
values('Седан'),('Пикап'),('Хэтчбэк'),('Минивэн'),('Грузовик');

insert into car_engines(name)
values('Бензиновый'),('Дизельный'),('Гибридный'),('Электродвигатель');

insert into car_transmissions(name)
values('Механика'),('Автомат'),('Вариатор'),('Робот');

insert into cars(name, body_id, engine_id, transmission_id)
values('Toyota Camry',1,1,3),
('Toyota Highlander',2,2,1),
('Kia Carnival',4,2,2),
('Kia Ceed',3,1,3),
('Kia Soul',3,1,2),
('BMW',1,3,null),
('VW Amarok',2,null,1);

select c.id, c.name as car_name, b.name as body_name, e.name as engine_name, t.name as transmission_name
from cars as c
	left join car_bodies as b
	on c.body_id = b.id
	left join car_engines as e
	on c.engine_id = e.id
	left join car_transmissions as t
	on c.transmission_id = t.id;

select b.name
from car_bodies as b left join cars as c
on b.id = c.body_id
where c.body_id is null;

select e.name
from car_engines as e left join cars as c
on e.id = c.engine_id
where c.engine_id is null;

select t.name
from car_transmissions as t left join cars as c
on t.id = c.transmission_id
where c.transmission_id is null;

