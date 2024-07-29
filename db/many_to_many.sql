create table customers(
	id serial primary key,
	name varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	email varchar(255) NOT NULL
);

create table books(
	id serial primary key,
	title varchar(255) NOT NULL,
	author varchar(255) NOT NULL,
	genre varchar(255) NOT NULL,
	price decimal(15,2) NOT NULL
);

create table orders(
	id serial primary key,
	customer_id int references customers(id) NOT NULL,
	book_id int references books(id) NOT NULL,
	quantity int
);