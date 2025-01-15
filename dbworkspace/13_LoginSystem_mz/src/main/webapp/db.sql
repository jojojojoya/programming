create table login_test2(
	l_id varchar2(20 char) primary key,
	l_pw varchar2(20 char) not null,
	l_name varchar2(20 char) not null
);

insert into login_test2 values('tg', 'tg', '태곤');
insert into login_test2 values('ds', 'ds', '대산');
insert into login_test2 values('yr', 'yr', '유리');

select * from login_test2;