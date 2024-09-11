create table Rooms(
    id serial primary key,
    home_type varchar(255),
    address varchar(255),
    has_tv boolean,
    has_internet boolean,
    has_air_cond boolean,
    price integer
);

insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('apartments', 'Primorskoe highway, 15-2', true, true, false, 500);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('flat', 'Lyigniy alleyway, 2-690', false, true, false, 700);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('hostel', 'Savushkina street, 50-10', false, false, false, 300);

begin transaction;
set session characteristics as transaction isolation level repeatable read;
set session characteristics as transaction isolation level serializable;

savepoint first_savepoint;

delete from rooms;
drop table rooms;
rollback to savepoint first_savepoint;
select * from rooms;

insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('office', 'Novgorodskaya street, 17', true, true, true, 1000);
update rooms set price = 600 where home_type = 'flat';

release savepoint first_savepoint;
commit;
select * from rooms;

begin transaction;

savepoint first_sp;
delete from rooms where has_tv = true and has_air_cond = false;
select * from rooms;

savepoint second_sp;
delete from rooms where price > 700;
select * from rooms;

rollback to savepoint first_sp;
select * from rooms;
commit;

