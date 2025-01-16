


select *from SNACK;


delete SNACK;
drop table
    snack cascade constraints purge;



create table snack (
                       s_no number(2) primary key ,
                       s_name varchar2(20 char) not null ,
                       s_maker varchar2(20 char) not null ,
                       s_weight number(4,1) not null ,
                       s_price number(5) not null,
                       s_exp date
);


-- 숫자를 자동으로 올리기 : sequence (테이블과는 완전히 무관, 별도의 존재) - 테이블과 엮어 씀
-- create sequence 시퀀스명;

insert into snack values (snack_seq.nextval,'베베','해태',90.1,1200, to_date('2024-11-30'));
insert into snack values (snack_seq.nextval,'초코송이','오리온',40.1,1500, to_date('2024-11-21'));
insert into snack values (snack_seq.nextval,'새우깡','농심',50.1,1000, to_date('2024-12-31'));
insert into snack values (snack_seq.nextval,'고구마깡','크라운',60.1,2200, to_date('2024-12-25'));
insert into snack values (snack_seq.nextval,'감자깡','크라운',60.1,2200, to_date('2024-12-25'));
insert into snack values (snack_seq.nextval,'나쵸','해태',70.1,1500,to_date('2024-12-05'));
insert into snack values (snack_seq.nextval,'홈런볼','해태',70.1,1500,to_date('2024-12-05'));
create sequence snack_seq;


-- 시퀀스명.nextval 로 사용

-- 시퀀스 설정
create sequence snack_seq increment by 1 start with 1;