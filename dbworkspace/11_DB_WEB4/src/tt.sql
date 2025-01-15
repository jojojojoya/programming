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


create table login_test(
    l_id varchar2(20 char) primary key,
    l_pw varchar2(20 char) not null
);

insert into login_test values('jj','jj');
insert into login_test values('kk','kk');
insert into login_test values('ss','ss');


select * from login_test;


create table login_test2(
                            l_id varchar2(20 char) primary key,
                            l_pw varchar2(20 char) not null,
                            l_name varchar2(20 char) not null
);

insert into login_test2 values('sj', 'sj', '수진');
insert into login_test2 values('hy', 'hy', '혜윤');
insert into login_test2 values('ce', 'ce', '채은');

select * from login_test2;

create table movie_test(
    m_no number(3) primary key,
    m_title varchar2(30 char) not null,
    m_actor varchar2(30 char) not null,
    m_img varchar2(200 char) not null,
    m_story varchar2(200 char) not null
);

create sequence movie_test_seq;

insert into movie_test values(movie_test_seq.nextval,'제목1','배우1','a1.jpg','내용1');
insert into movie_test values(movie_test_seq.nextval,'제목2','배우2','a2.jpg','내용2');
insert into movie_test values(movie_test_seq.nextval,'제목3','배우3','a3.jpg','내용3');

select * from movie_test;


create table review_test(
  r_no number(3) primary key ,
  r_title varchar2(20 char) not null,
  r_txt varchar2(200 char) not null,
    r_date date not null

);

create sequence review_test_seq;


insert into review_test values (review_test_seq.nextval, '제목1','내용1',sysdate);
insert into review_test values (review_test_seq.nextval, '제목2','내용2',sysdate);
insert into review_test values (review_test_seq.nextval, '제목3','내용3',sysdate);

select * from review_test;






