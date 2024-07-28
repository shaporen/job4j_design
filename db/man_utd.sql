create table players (
	id serial primary key,
	name varchar(255) NOT NULL,
	surname varchar(255) NOT NULL,
	position varchar(255) NOT NULL,
	birthday date NOT NULL,
	salary_week decimal(15, 2) NOT NULL
);

insert into players(name, surname, position, birthday, salary_week) values
('Джейдон', 'Санчо', 'нападающий', '2000-03-25', 250000.00),
('Бруно', 'Фернандеш', 'полузащитник', '1994-09-08', 240000.00);

update players
set salary_week = 250000.00
where id = 2

delete from players

select * from players