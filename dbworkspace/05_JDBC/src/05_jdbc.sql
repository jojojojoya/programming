create table coffee_test
(
    c_no number(3) primary key,
   c_name varchar2(20 char) not null,
    c_price number(4) not null,
    c_origin varchar2(20 char) not null
    );

create sequence coffee_test_seq;

insert into coffee_test values (coffee_test_seq.nextval,'카페라떼',1000,'브라질');
insert into coffee_test values (coffee_test_seq.nextval,'아메리카노',2000,'과테말라');
insert into coffee_test values (coffee_test_seq.nextval,'모카라떼',3000,'케냐');

select * from coffee_test;