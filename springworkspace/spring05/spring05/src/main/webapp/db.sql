create table menu_test
(
    m_no    number(3) primary key,
    m_name  varchar2(20 char) not null,
    m_price number(4) not null
);

create sequence menu_test_seq;

insert into menu_test
values (menu_test_seq.nextval, '메뉴1', 1000);
insert into menu_test
values (menu_test_seq.nextval, '메뉴2', 2000);
insert into menu_test
values (menu_test_seq.nextval, '메뉴3', 3000);
insert into menu_test
values (menu_test_seq.nextval, '메뉴4', 4000);

select *
from menu_test;