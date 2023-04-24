create or replace procedure delete_data(d_count integer, d_id integer)
    language 'plpgsql'
as $$
BEGIN
    if d_id > 0 and d_count > 0 THEN
        delete from products where id = d_id and count = d_count;
    end if;
END;
$$;

create or replace function f_delete_data(d_count integer, d_id integer)
    returns boolean
    language 'plpgsql'
as
$$
declare
    result boolean;
begin
    if d_count >= 0 THEN
        delete from products where id = d_id and count = d_count;
        if (select id from products where id = d_id) is null then result = true;
        else result = false;
        end if;
    end if;
    return result;
end;
$$;
