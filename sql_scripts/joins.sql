create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments (name) values ('first'), ('second'), ('third');
insert into employees (name, department_id) values ('Man1', 1), ('Man2', 1), ('Woman1', 2), ('Woman2', 2);

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d left join employees e on d.id = e.department_id;

select * from departments d right join employees e on d.id = e.department_id;
select * from employees e right join departments d on e.department_id = d.id;

select * from departments d full join employees e on d.id = e.department_id;
select * from employees e full join departments d on d.id = e.department_id;

select * from employees cross join departments d;

select * from departments d left join employees e on d.id = e.department_id where e.department_id is null;

select * from employees e left join departments d on d.id = e.department_id;
select e.id, e.name, e.department_id, d.id, d.name from departments d right join employees e on d.id = e.department_id;

create table teens (
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens (name, gender) values
('Mname1', 'M'), ('Wname1', 'W'), ('Mname2', 'M'), ('Wname2', 'W'), ('Mname3', 'M');

select * from teens;

select * from teens t1
    cross join teens t2
where t1.gender != t2.gender;
