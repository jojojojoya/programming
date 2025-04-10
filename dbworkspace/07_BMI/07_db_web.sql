create table name_age_test(
  n_name varchar2(20 char) not null,
  n_age number(3) not null
);

insert into name_age_test values ('원호',20);
insert into name_age_test values ('다훈',30);
insert into name_age_test values ('진현',25);

select * from name_age_test;


create table member_test(
    m_no number(3) primary key,
    m_name varchar2(20 char) not null,
    m_age number(3) not null
);

create sequence member_test_seq;


insert into member_test values (member_test_seq.nextval,'test',10);
insert into member_test values (member_test_seq.nextval,'test',20);


select * from member_test;


create table restaurant_test (
r_no number(3) primary key,
r_name varchar2(20 char) not null,
r_menu varchar2(20 char) not null,
r_place varchar2(20 char) not null
);

create sequence res_test_seq;

insert into restaurant_test values(res_test_seq.nextval,'가게1','대표메뉴1','종각');
insert into restaurant_test values(res_test_seq.nextval,'가게2','대표메뉴2','종각2');
insert into restaurant_test values(res_test_seq.nextval,'가게3','대표메뉴3','종각3');

select * from restaurant_test;


drop table member_test cascade constraints purge ;
drop sequence member_test_seq;

create table member_test
(
    m_no   number(3) primary key,
    m_name varchar2(20 char) not null,
    m_age  number(3)         not null
);
create sequence member_test_seq;

insert into member_test values (member_test_seq.nextval,'test',20);
insert into member_test values (member_test_seq.nextval,'test2',3);

select * from member_test;