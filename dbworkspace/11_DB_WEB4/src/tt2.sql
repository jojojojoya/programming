

create table menu(
                     m_no number(3) primary key,
                     m_name varchar2(20 char) not null,
                     m_price number(6) not null,
                     m_place number(3) not null,
                     constraint menu_restaurant
                         foreign key(m_place)
                             references restaurant(r_no)
                                 on delete cascade
);


drop table menu cascade constraint purge;
drop table restaurant cascade constraint purge;
drop table human cascade constraint purge;
drop table owner cascade constraint purge;

drop sequence menu_seq;
drop sequence restaurant_seq;
drop sequence human_seq;
drop sequence owner_seq;


create table restaurant(
                           r_no number(3) primary key,
                           r_name varchar2( 30 char) not null,
                           r_place varchar2( 20 char) not null
);

insert into restaurant values(20,'res2','혜화');
insert into restaurant values(10,'res1','종각');

insert into restaurant values(30,'res1','종각');
insert into menu values(1,'menu1',1000,10 );
insert into menu values(2,'menu2',2000,20);
insert into human values(1,'수진쨩',to_date('1980-05-05','YYYY-MM-DD'));
insert into owner values(50,1,10);
insert into human values(3,'수쨩',to_date('1980-05-05','YYYY-MM-DD'));
insert into owner values(30,3,30);



delete restaurant where r_no=20;

create table human(
                      h_no number(3) primary key,
                      h_name varchar2(30 char) not null,
                      h_birth date not null
);
create table owner(
                      o_no number(3) primary key,
                      o_ceo number(3) not null,
                      o_restaurant number(3) not null,
                      constraint o_c_r
                          foreign key(o_ceo)
                              references human(h_no)
                                  on delete cascade,
                      foreign key(o_restaurant)
                          references restaurant(r_no)
                              on delete cascade
);
drop table owner cascade constraint purge;

select * from menu;
select * from restaurant;

select * from human;
select * from owner;
-- h_no 날아가는 경우 owner가 지워지는지?
delete human
where h_no = 1;
-- r_no 날아가는 경우 owner가 지워지는지?
delete restaurant
where r_no = 2;





create sequence menu_seq increment by 1 start with 1;
create sequence restaurant_seq increment by 1 start with 1;
create sequence human_seq increment by 1 start with 1;
create sequence owner_seq increment by 1 start with 1;




create table   people_test (
   p_no number(2) primary key ,
    p_name varchar2(20 char) not null,
    p_age number(2) not null
);

create sequence people_test_seq;

insert into people_test values (people_test_seq.nextval,'mz100',15);
insert into people_test values (people_test_seq.nextval,'mz200',25);
insert into people_test values (people_test_seq.nextval,'mz300',35);


select * from people_test;