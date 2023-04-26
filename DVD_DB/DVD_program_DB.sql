create table users(
    user_number number(5) primary key,
    user_name varchar(20) not null,
    phone_number varchar2(30) not null,
    total_paying number(10) default 0,
    grade varchar2(10) default 'BRONZE'
);

create sequence users_seq
    start with 1
    increment by 1
    maxvalue 100000
    nocycle
    nocache;
    
select * from users;
drop table users;
create table movie(
    serial_Number number(5) primary key,
    movie_name varchar2(50) not null,
    nation varchar2(20) not null,
    pub_year number(4),
    rental varchar2(1) not null,
    charge number(5) not null
);

create sequence movie_seq
    start with 1
    increment by 1
    maxvalue 1000000
    nocycle
    nocache;
    
select * from movie;
drop table movie;
create table order_history(
    order_no NUMBER(5) PRIMARY KEY,
    user_number NUMBER(5) NOT NULL,
    serial_number number(5) not null,
    order_date date default sysdate,
    return_date date default sysdate +3
);
drop table order_history;
select * from order_history;

create sequence order_seq
    start with 1
    increment by 1
    maxvalue 1000000
    nocycle
    nocache;
    
create or replace trigger trg_order_history
    after insert
    on order_history
    for each row
declare
    v_serial_number number(5);
begin
    select
        :new.serial_number
    into v_serial_number
    from dual;
    
    update movie set rental = 'false'
    where serial_number = v_serial_number;
end;