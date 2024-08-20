create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into people(name)
	values('Сергей'),
	('Артем'),
	('Евгений'),
	('Николай');

insert into devices(name, price)
	values('Компьютер', 4500.00),
	('Ноутбук', 3500.00),
	('Смартфон', 1500.00),
	('Пылесос', 2000.00);

insert into devices_people(device_id,people_id)
values (1, 1),
(2, 1),
(3, 1),
(3, 2),
(4, 2),
(2, 3),
(3, 3),
(1, 4);

select avg(price)
from devices;

select p.name, avg(d.price)
from people as p
inner join devices_people as dp
on p.id = dp.people_id
inner join devices as d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people as p
inner join devices_people as dp
on p.id = dp.people_id
inner join devices as d
on dp.device_id = d.id
group by p.name
	having avg(d.price) > 5000;