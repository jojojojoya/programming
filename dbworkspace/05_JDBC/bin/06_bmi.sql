create table bmi_test (
    b_no number(2) primary key,
    b_name varchar2(20 char) not null,
    b_height number(5,1) not null,
    b_weight number(5,1) not null,
    b_bmi number(5,1) not null,
    b_status varchar2(20 char) not null
);

create sequence bmi_test_seq;

select *from bmi_test;