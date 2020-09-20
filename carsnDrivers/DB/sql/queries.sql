create table driver
(
    id         bigserial primary key,
    first_name varchar(20),
    last_name  varchar(20),
    age        integer
);

insert into driver(first_name, last_name, age)
values ('Миша', 'Ховаев', 19),
       ('Дима', 'Лысенков', 20);

select first_name, age
from driver
where last_name ilike '%ов'
  and first_name = 'Миша'
order by age desc;

update driver
set age = 27
where first_name = 'Марсель';

delete
from driver
where id = 2;

drop table driver;

create table car
(
    id        bigserial primary key,
    model     varchar(20),
    color     varchar(20),
    driver_id bigint,
    foreign key (driver_id) references driver (id)
);

insert into car (model, color, driver_id)
values ('Лада-седан', 'Белая', 1);

insert into car (model, color, driver_id)
values ('Приора', 'Сочи', 2);
insert into car (model, color, driver_id)
values ('Кадилак', 'Черный', 2);
insert into car (model, color)
values ('Volkswagen Toureg', 'Черный');

select *
from driver
where id in (
    (select distinct driver_id
     from car
     where driver_id notnull));

select *
from driver d
         left join car c on d.id = c.driver_id;

select *
from driver d
         right join car c on d.id = c.driver_id;

select *
from driver d
         inner join car c on d.id = c.driver_id;

select *
from driver d
         full join car c on d.id = c.driver_id;

create table manufacture (
    id serial primary key,
    name varchar(10),
    place varchar(10)
);