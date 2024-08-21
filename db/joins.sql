create table departments
(
    id   serial primary key,
    name text
);

create table employees
(
    id   serial primary key,
    name text,
	dep_id int references departments (id)
);

insert into departments(name)
values ('Управление'),
	('Бухгалтерия'),
	('Казначейство'),
	('Отдел кадров');

insert into employees(name, dep_id)
values ('Алексеев',1),
	('Петров',1),
	('Скворцов',1),
	('Иванов', null),
	('Сидоров', null),
	('Арсентьев',2),
	('Яковлев',2),
	('Сыромицкий',2),
	('Пяткин',3),
	('Еволенко',3),
	('Шапоренко',3);

select * from departments d
         left join employees e on e.dep_id = d.id;

select * from departments d
         right join employees e on e.dep_id = d.id;

select * from departments d
         full join employees e on e.dep_id = d.id;

select * from departments d
         cross join employees e;