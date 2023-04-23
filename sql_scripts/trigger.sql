create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--1
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
	on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

--2
create or replace function add_tax()
    returns trigger as $$
begin
    new.price = new.price +  new.price * 0.2;
    return new;
end;
$$ language 'plpgsql';

create trigger add_tax_trigger
    before insert on products
    for each row
execute function add_tax();

--3
create table history_of_price (
     id serial primary key,
     name varchar(50),
     price integer,
     date timestamp
);

create or replace function products_insert_trigger_fnc()
    returns trigger as
$$
begin
    insert into history_of_price (name, price, date)
    values(new.name, new.price, now());
    return new;
end;
$$
    language 'plpgsql';

create trigger products_insert_trigger
    after insert
    on products
    for each row
execute procedure products_insert_trigger_fnc();