insert into fauna(name, avg_age, discovery_date)
values('Catfish', 15000, '1700-05-05'),
('Giraffe', 25000, '1900-05-05'),
('Rhinoceros', 35000, '2000-05-05'),
('Whitefish', 9000, '1800-05-05'),
('Crayfish', 5000, '1700-05-05'),
('Cat', 5000, '700-05-05'),
('Dog', 7000, '600-05-05'),
('Pig', 15000, '1960-05-05'),
('Honeybee', 2000, '1970-05-05');

select * from fauna
where name like '%fish%';

select * from fauna
where avg_age between 10000 and 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date < '1950-01-01';