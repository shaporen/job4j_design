create table positions(
	id serial primary key,
	position varchar(255)
);

create table players(
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	position_id int references positions(id),
	birthday date
);

insert into positions(position)
values('Вратарь'),('Защитник'),('Полузащитник'),('Нападающий')

insert into players(name, surname, position_id, birthday)
values('Марк-Андре', 'тер Стеген', 1, '1992-04-30'),
('Иньяки', 'Пенья', 1, '1999-03-02'),
('Клеман', 'Лангле', 2, '1995-06-17'),
('Алехандро', 'Бальде', 2, '2003-10-18'),
('Рональд', 'Араухо', 2, '1999-03-07'),
('Ферран', 'Торрес', 3, '2000-02-29'),
('Френки', 'де Йонг', 3, '1997-05-12'),
('Илкай', 'Гюндоган', 3, '1990-10-24'),
('Фермин', 'Лопес', 3, '2003-05-11'),
('Ансу', 'Фати', 4, '2002-10-13'),
('Дани', 'Ольмо', 4, '1998-05-07'),
('Роберт', 'Левандовски', 4, '1988-08-21');

select pl.name, pl.surname, pos.position from players AS pl
inner join positions AS pos on pl.position_id = pos.id;

select pl.surname, pl.birthday, pos.position from players AS pl
inner join positions AS pos
	on pl.position_id = pos.id
	where pos.position = 'Защитник';

select pl.name, pl.surname, pos.position from players AS pl
inner join positions AS pos
	on pl.position_id = pos.id
	where pl.birthday > '2000-01-01';