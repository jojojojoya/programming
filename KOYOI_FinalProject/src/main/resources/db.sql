create table product_test2(
    p_no number(3) primary key,
    p_name varchar2(30 char) not null,
    p_price number(5) not null
);

create sequence product_test2_seq;

insert into product_test2 values (product_test2_seq, 'test1', 1000);
insert into product_test2 values (product_test2_seq, 'test2', 2000);
insert into product_test2 values (product_test2_seq, 'test3', 3000);

select * from product_test2;

create table review_test(
                            r_no number(3) primary key,
                            r_title varchar2(20 char) not null,
                            r_txt varchar2(200 char) not null,
                            r_date date not null
);

create sequence review_test_seq;


insert into review_test values (review_test_seq.nextval, '제목1', 'ㅋㅋㅋ', sysdate);
insert into review_test values (review_test_seq.nextval, '제목2', 'ㅎ3ㅎ', sysdate);
insert into review_test values (review_test_seq.nextval, '제목3', 'ㅋ 3ㅋ', sysdate);

select * from review_test;