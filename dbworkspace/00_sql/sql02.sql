-- 주석

-- 실행 : ctrl + enter (커서 자체가 실행할문장 옆에 있으면 됨)
--------------------------------------------------------
-- 행 , row, record, 행 하나는 데이터 1개. data object라고 함.
-- 열 , column, field, 속성
--------------------------------------------------------
-- DBA : 서버의 전원관리, 백업/복구, 계정 관리
-- DBP : CRUD (create,read,update,delete) <= 우리가 해야 함. 목표다
-- DBU : db 사용 user
--------------------------------------------------------
-- SQL (Structured query language) 구조화된 질의언어 : DB 제어하는 언어
-- DDL, DML, DCL
----------------------------------------------------------
-- 1) 테이블 만들기
-- 학생    이름, 나이, 국,영,일
create table
    student (
    s_name varchar2(20 char), --string의 역할 / 20글자
    s_age number(3),            -- 3자리까지 가능
    s_kor number(3),
    s_eng number(3),
    s_jap number(3)
    );

-- 2) 조회
select *from student;
------------ 안쓴건 null이 되고,
-- 필드 순서를 왜 바꾸는지,
-- null을 그대로 둬서 뭘 하고 싶은지?
-- 0점, 0  >> null을 계산하기 싫으니까 쓴다
-- 테이블에 옵션 쓸 수 있음



create table
    student2 (
                s_name varchar2(20 char) primary key, --string의 역할 / 20글자
                s_age number(3) not null,            -- 3자리까지 가능
                s_kor number(3) not null,
                s_eng number(3) not null,
                s_jap number(3) not null
);

insert into student2 values ('동혁',25,100,90,80);
insert into student2 values ('동혁2',25,100,90,80);
select * from student2;


-- 옵션
    -- not null : null을 불허 / 사실상 기본, 필수
    -- primary key : 중복 불허 + not null (null을 허용하지 않음)
    -- 그 테이블을 대표하는 값 => 테이블 하나에는 pk 하나 있는게 좋음.
    -- id => 나



-- 3) 데이터 삽입

insert into student values('gg',23,13,33,66);
insert into student(s_name,s_eng) values('동혁',100);
insert into student(s_name,s_eng,s_jap,s_kor) values('동혁2',10,20,30);

-- // 데이터의 순서가 달라도 삽입 가능



--- 데이터 수정 -- update
-- st1 tbl 에서 동혁 영어 점수를 50점으로 변경하고 싶을때
--

update student
set s_eng = 50
where s_name = '동혁';


select * from student2;


--데이터 삭제 -- delete
-- st1 동혁삭제

    delete student
    where s_name = '동혁';

-- st2 동혁2 삭제

    select  * from student2;
    delete student2
    where s_name = '동혁2';

