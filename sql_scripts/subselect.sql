CREATE TABLE customers(
                          id serial primary key,
                          first_name text,
                          last_name text,
                          age int,
                          country text
);

insert into customers values (1, 'fn1', 'ln1', 20, 'country1'),
                             (2, 'fn2', 'ln2', 25, 'country2'),
                             (3, 'fn3', 'ln3', 30, 'country3'),
                             (4, 'fn4', 'ln4', 40, 'country4'),
                             (5, 'fn5', 'ln5', 20, 'country4');

select * from customers as c where c.age = (select min(age) from customers);

CREATE TABLE orders(
                       id serial primary key,
                       amount int,
                       customer_id int references customers(id)
);

insert into orders values (1, 2, 1),
                          (2, 4, 2),
                          (3, 1, 1),
                          (4, 1, 2);

select * from customers as c where c.id not in (select o.customer_id from orders as o);