create table db_test
(   d_no number(3) primary key ,
    d_name varchar2(20 char) not null,
    d_age number (3) not null
);

-- drop table db_test cascade constraints purge;
create sequence db_test_seq;

-- 데이터 3개 이상 넣기
insert into db_test values (db_test_seq.nextval, '동혁','22')
insert into db_test values (db_test_seq.nextval, '정민','25')
insert into db_test values (db_test_seq.nextval, '승완','26')

select *from db_test;
