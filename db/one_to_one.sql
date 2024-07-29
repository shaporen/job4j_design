create table tins (
	id serial primary key,
	tin numeric(12, 0) NOT NULL
);

create table persons(
	id serial primary key,
	name varchar(255) NOT NULL,
	surname varchar(255) NOT NULL,
	tin_id int references tins(id) unique
);