create table teens(
	id   serial primary key,
    name text,
	gender text
);

insert into teens(name, gender)
	values ('Мария','ж'),
	('Василий','м'),
	('Татьяна','ж'),
	('Петр','м'),
	('Сергей','м'),
	('Валентина','ж'),
	('Алексей','м'),
	('София','ж'),
	('Антон','м');

select t1.name as teen1, t2.name AS teen2
from teens t1
CROSS JOIN teens t2
WHERE t1.gender != t2.gender and t1.id < t2.id;