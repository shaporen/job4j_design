create table positions(
	id serial primary key,
	position varchar(255)
);

create table players(
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	position_id int references positions(id),
	birthday date,
	salary_week decimal(15, 2)
);