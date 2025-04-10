
-- 과자를 저장하고 싶음
-- 하나의 테이블에 pk column을 하나 두자.
--

create table snack (
  s_no number(2) primary key ,
  s_name varchar2(20 char) not null ,
  s_maker varchar2(20 char) not null ,
  s_weight number(4,1) not null ,
  s_price number(5) not null,
  s_exp date
  );


-- insert  ( C )
insert into snack values (1,'베베','해태',90.1,1200, sysdate);
insert into snack values (2,'베베','해태',90.1,1200, sysdate);
insert into snack values (3,'망나뇽','해태',90.1,1200, sysdate);
insert into snack values (90,'초코송이','해태',90.1,1200, sysdate);
insert into snack values (60,'감자깡','해태',90.1,1200, sysdate);


-- select ( R )
select *from snack;

-- sysdate : 현재 날짜 시간
-- 특정 날짜 시간 :
-- to_date('값', '패턴')
    -- YYYY, MM, DD, HH:MI:SS
insert into snack values (80,'고구미깡','해태',90.1,1200, to_date('12-31-2024','MM-DD-YYYY'));
insert into snack values (9,'고구미깡','해태',90.1,1200, to_date('2024-12-01'));
