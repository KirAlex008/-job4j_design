create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price int
);

insert into type(name)
values ('СЫР'), ('МОЛОКО'), ('КОЛБАСА'), ('ШОКОЛАД');

insert into product(name, type_id, expired_date, price)
values ('Российский', 1, date '2022-08-10', 650);
insert into product(name, type_id, expired_date, price)
values ('Гауда', 1, date '2022-08-24', 856);
insert into product(name, type_id, expired_date, price)
values ('Камамбер', 1, date '2022-08-14', 932);
insert into product(name, type_id, expired_date, price)
values ('Стародубский', 1, date '2022-08-07', 558);
insert into product(name, type_id, expired_date, price)
values ('Мороженое', 2, date '2022-08-13', 77);
insert into product(name, type_id, expired_date, price)
values ('Сметана', 2, date '2022-08-07', 97);
insert into product(name, type_id, expired_date, price)
values ('Кефир', 2, date '2022-08-12', 67);
insert into product(name, type_id, expired_date, price)
values ('Столичная', 3, date '2022-08-02', 487);
insert into product(name, type_id, expired_date, price)
values ('Докторская', 3, date '2022-08-12', 312);
insert into product(name, type_id, expired_date, price)
values ('Охотничья', 3, date '2022-08-21', 397);
insert into product(name, type_id, expired_date, price)
values ('Аленка', 4, date '2022-08-14', 75);
insert into product(name, type_id, expired_date, price)
values ('Dove', 4, date '2022-08-03', 98);
insert into product(name, type_id, expired_date, price)
values ('Milka', 4, date '2022-08-16', 82);
insert into product(name, type_id, expired_date, price)
values ('Мамамбер', 1, date '2022-09-14', 932);

select * from product;

select * from product join type on product.type_id = type.id where type.name like '%СЫР%';

select * from product as p join type as t on p.type_id= t.id where t.name like '%СЫР%';

select * from product where name = 'Мороженое';

select * from product where expired_date < current_date;

select product.name, max(product.price)
from product
group by product.name
having max(product.price) = (select max(price) from product);

select t.name, count(p.name)
from product p join type t on p.type_id = t.id
group by t.name
order by count(p.name) desc;

select * from product p join type t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name, count(p.name) from product p join type
    t on p.type_id = t.id
where t.name = 'СЫР'
group by t.name
having count(p.name) < 10;

select * from product p join type t
    on p.type_id = t.id;
