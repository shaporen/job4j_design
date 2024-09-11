create table Rooms(
    id serial primary key,
    home_type varchar(255),
    address varchar(255),
    has_tv boolean,
    has_internet boolean,
    has_air_cond boolean,
    price integer
);

//Чтение подтвержденных данных (проверка на "грязное чтение")
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('apartments', 'Primorskoe highway, 15-2', true, true, false, 500);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('flat', 'Lyigniy alleyway, 2-690', false, true, false, 700);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('hostel', 'Savushkina street, 50-10', false, false, false, 300);

//1-я транзакция
begin transaction;
//2-я транзакция
begin transaction;

//проверка данных в 1-й транзакции
select * from rooms;
//проверка данных во 2-й транзакции
select * from rooms;

//манипуляция с данными в 1-й транзакции
insert into rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('apartments', 'Primorskoe highway, 15-5', true, true, true, 600);
delete from rooms where price = 300;
update rooms set price = 600 where home_type = 'flat';

//проверка данных во 2-й транзакции (без изменений)
select * from rooms;

//коммит в 1-й транзакции
commit;

//проверка данных во 2-й транзакции (изменения есть)
select * from rooms;

delete from rooms;

//Повторяющееся чтение
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('apartments', 'Primorskoe highway, 15-2', true, true, false, 500);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('flat', 'Lyigniy alleyway, 2-690', false, true, false, 700);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('hostel', 'Savushkina street, 50-10', false, false, false, 300);

//1-я транзакция с уровнем изоляции "повторяющееся чтение"
begin transaction isolation level repeatable read;
//2-я транзакция с уровнем изоляции "повторяющееся чтение"
begin transaction isolation level repeatable read;

//проверка данных в 1-й транзакции
select * from rooms;
//проверка данных во 2-й транзакции (идентичны)
select * from rooms;

//манипуляция с данными в 1-й транзакции
insert into rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('apartments', 'Primorskoe highway, 15-5', true, true, true, 600);
delete from rooms where price = 300;
update rooms set price = 600 where home_type = 'flat';

//попытка манипуляции с данными во 2-й транзакции
update rooms set price = 600 where home_type = 'flat'; //получаем lock

//откат в 1-й транзакции (при этом во 2-й зашла операция update)
rollback;
//откат во 2-й транзакции (для повторения эксперимента уже с коммитом)
rollback;

//манипуляция с данными в 1-й транзакции
insert into rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('apartments', 'Primorskoe highway, 15-5', true, true, true, 600);
delete from rooms where price = 300;
update rooms set price = 600 where home_type = 'flat';

//попытка манипуляции с данными во 2-й транзакции
update rooms set price = 600 where home_type = 'flat'; //получаем lock

//коммит в 1-й транзакции (при этом во 2-й транзакции - не удалось сериализовать доступ из-за параллельного изменения)
commit;

delete from rooms;

//Сериализация
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('apartments', 'Primorskoe highway, 15-2', true, true, false, 500);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('flat', 'Lyigniy alleyway, 2-690', false, true, false, 700);
insert into Rooms(home_type, address, has_tv, has_internet, has_air_cond, price)
values ('hostel', 'Savushkina street, 50-10', false, false, false, 300);

begin transaction isolation level serializable; //1-я транзакция с уровнем изоляции "сериализация"
begin transaction isolation level serializable; //2-я транзакция с уровнем изоляции "сериализация"

//запрос в 1-й транзакции = 1500
select sum(price) from rooms;
//манипуляции с данными в 1-й транзакции
update rooms set price = 600 where home_type = 'flat';

//запрос во 2-й транзакции = 1500
select sum(price) from rooms;
//манипуляции с данными во 2-й транзакции
update rooms set price = 400 where home_type = 'hostel';

//коммит во 2-й транзакции
commit;

//коммит в 1-й транзакции (ошибка)
commit;