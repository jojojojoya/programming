--RDB (relational)


-- 테이블간의 관계를 중심으로 뭔가.. //

-- 강남 홍콩반점 1호점 짜장면 5000원         승완 이름, 나이, 생일
-- 종로 인생설렁탕  설렁탕 8000원          동혁 이름, 나이, 생일
-- 역삼동 빽다방  카페라떼 4000원          수진 이름, 나이, 생일
-- 대학로 스타벅스 6000원 돌체라떼          혜윤 이름, 나이, 생일


create table menu(
                     m_no number(3) primary key,
                     m_name varchar2(20 char) not null,
                     m_price number(6) not null,
                     m_place number(3) not null
);

create table restaurant(
                           r_no number(3) primary key,
                           r_name varchar2( 30 char) not null,
                           r_place varchar2( 20 char) not null
);
create table human(
                      h_no number(3) primary key,
                      h_name varchar2(30 char) not null,
                      h_birth date not null
);
create table owner(
                      o_no number(3) primary key,
                      o_ceo number(3) not null,
                      o_restaurant number(3) not null
);






create sequence menu_seq increment by 1 start with 1;
create sequence restaurant_seq increment by 1 start with 1;
create sequence human_seq increment by 1 start with 1;
create sequence owner_seq increment by 1 start with 1;

insert into menu values (menu_seq.nextval, '돼지곱창', 10000, 1);
insert into menu values (menu_seq.nextval, '소곱창', 15000, 1);
insert into menu values (menu_seq.nextval, '야채곱창', 9000, 2);
insert into menu values (menu_seq.nextval, '카페라떼', 6000, 3);
insert into menu values (menu_seq.nextval, '아메리카노', 4000, 3);
insert into menu values (menu_seq.nextval, '돌체라떼', 7000, 4);

insert into RESTAURANT values (RESTAURANT_seq.nextval, '황소곱창', '종로');
insert into RESTAURANT values (RESTAURANT_seq.nextval, '동대문곱창', '동대문');
insert into RESTAURANT values (RESTAURANT_seq.nextval, '황소곱창', '동대문');
insert into RESTAURANT values (RESTAURANT_seq.nextval, '스타벅스', '종로');
insert into RESTAURANT values (RESTAURANT_seq.nextval, '스타벅스', '강남');
insert into RESTAURANT values (500, '스타벅스', '광화문');


select * from MENU;
select * from RESTAURANT;


-- 메뉴 추가
-- 스벅 광화문점에 체리블로썸 추가
insert into menu values (menu_seq.nextval,'체리블로썸',5000,500);

-- 딸기 스무디 1000원 2번 레스토랑에 추가
insert into menu values (menu_seq.nextval,'딸기 스무디', 1000,2);


-- 딸기 스무디 곱창집에 잘못 넣은거 스벅 종로점에 넣어야함
update menu set m_place = 4
where m_no = 8;


-- 체리블러썸 메뉴 삭제
delete menu where m_no = 7;


insert into HUMAN values (human_seq.nextval, '김', to_date('1980-05-05', 'YYYY-MM-DD'));
insert into HUMAN values (human_seq.nextval, '나', to_date('1985-06-05', 'YYYY-MM-DD'));
insert into HUMAN values (human_seq.nextval, '박', to_date('1980-07-05', 'YYYY-MM-DD'));
insert into HUMAN values (human_seq.nextval, '이', to_date('1985-08-05', 'YYYY-MM-DD'));
insert into HUMAN values (200, '최', to_date('1985-08-05', 'YYYY-MM-DD'));

select * from HUMAN;
select * from restaurant;
select * from owner;

insert into owner values(OWNER_seq.nextval, 1,1);
insert into owner values(OWNER_seq.nextval, 2,2);
insert into owner values(OWNER_seq.nextval, 3,3);
insert into owner values(OWNER_seq.nextval, 4,4);
insert into owner values(OWNER_seq.nextval, 200,4);


-- 이게 무슨 의미? -- 스벅 광화문점의 주인 : 이씨
insert into owner values (owner_seq.nextval, 4, 500);


-- 내가 알고 있는 맛집 이름, 위치(매장 조회) - 이름 가나다순
select r_name, r_place from restaurant order by r_name;

-- 제일 비싼 메뉴 정보

select * from menu
         where m_price = (select max(m_price) from menu);

-- 최연장자 정보

select * from human
where h_birth = (select min(h_birth) from human);



-- 곱창시리즈 평균가격

select m_price from menu
where m_name like '%곱창%';



-- 종로 가게들 정보
select * from restaurant
where r_place like '%종로%';

-- 제일 싼 메뉴를 파는 식당의 이름과 위치

select r_name, r_place from restaurant
where r_no =
      (select m_place
      from menu
      where m_price = (select min(m_price) from menu));



-- 동대문에서 먹을 수 있는 음식 이름, 가격;
select m_name, m_price from menu
    where m_place in   -- 2개 이상일때 like가 아닌 in
          (select r_no from restaurant where r_place = '동대문');

select * from menu;

select * from owner;

-- TEST
-- 곱창시리즈는 어느 지역에 가면 먹을 수 있나? (어느가게? 가게 이름과 장소가 궁금)
select r_name, r_place from restaurant
where r_no in
      (select m_place from menu where m_name like '%곱창%');




-- 제일 싼 커피를 파는 매장의 이름 지역
-- 카테고리 컬럼을 넣었다면 쉬웠을 것 1. 일반

select r_name, r_place from restaurant
    where r_no = (select m_place from menu
where m_price = (select min(m_price) from menu where m_name like '%커피%'));


----------------------------------
-- join
-- 서로 다른 테이블을 연결 [관계를 명시할것]
-- 메뉴이름, 메뉴가격, 레스토랑 이름, 장소
select  m_name, m_price, r_name, r_place
from menu, restaurant
where r_no = m_place;

-- 스타벅스 종로지점의 메뉴 이름과 가격, 레스토랑 정보
-- 관계명시 안한 케이스
select  m_name, m_price, r_name, r_place
from menu,restaurant
where r_place = '종로' and r_name = '스타벅스';


-- 관계명시 한 케이스
select  m_name, m_price, r_name, r_place
from menu,restaurant
where m_place = r_no and r_place = '종로' and r_name = '스타벅스';

-- 관계명시 x
select m_name, m_price, r_name, r_place
from menu, restaurant
where m_place = (
    select r_no
    from restaurant
    where r_name = '스타벅스' and r_place = '종로'
);

-- 관계명시 o
select m_name, m_price, r_name, r_place
from menu, restaurant
where m_place = r_no and
    m_place = (
        select r_no
        from restaurant
        where r_name = '스타벅스' and r_place = '종로'
    );


-- 제일 젊은 사장님네 가게의 매장명, 위치, 사장님 이름, 생일, 메뉴명. 가격
select r_name, r_place, h_name, h_birth, m_name, m_price from restaurant, human, menu,owner
where r_no = m_place and h_no = o_ceo  and o_restaurant = r_no and h_birth= (select max(h_birth) from human);


-- 최 연장자 아저씨네 메뉴명, 메뉴가격
-- 틀린쿼리
select m_name, m_price from menu, human,restaurant, owner
where m_place = r_no and o_restaurant =  r_no and o_ceo = h_no
                                               and h_birth = (select min(h_birth) from human);



select m_name, m_price
from menu, human,owner
where m_place = o_restaurant  and o_ceo = h_no
  and h_birth = (select min(h_birth) from human);



select m_name, m_price
from menu
where m_place = (
    select r_no
    from restaurant
    where r_no = (
        select o_restaurant
        from owner
        where o_ceo = (
            select h_no
            from human
            where h_birth = (
                select min(h_birth)
                from human
            )
        )
    )
);


select m_name, m_price
from menu
where m_place = (
    select o_restaurant
    from owner
    where o_ceo = (
        select h_no
        from human
        where h_birth = (
            select min(h_birth)
            from human
        )
    )
);

-- 가능한 안보고.
-- 황소곱창 가게의 전체 메뉴명, 가격, 매장위치

    select m_name,m_price, m_place from menu, restaurant
    where m_place = r_no and
        m_place in (
            select r_no
            from restaurant
            where r_name = '황소곱창'
        );

-- 제일 싼거 파는 매장명, 위치, 메뉴명, 가격

 select r_name, r_place, m_name, m_price from restaurant, menu
 where r_no = m_place and
       m_price = (select min(m_price) from menu);



----------------------------------------------------
-- C / R / U / D

-- 돼지곱창 가격 인상 (13000원으로)
update menu
set m_price = 13000
where m_name = '돼지곱창';

select * from menu;

-- 제일 싼 메뉴 무료이벤트
update menu
set m_price = 0
where m_price = (select min(m_price) from menu);

select * from menu;


--메뉴 전체의 평균보다 비싼 메뉴를 10% 할인


update menu
set m_price = m_price*0.9
where m_price > (select avg(m_price) from menu);

select * from menu;
 -- 동대문 지역 메뉴들 1000원 인상

 update menu
 set m_price = m_price + 1000
 where m_place in (select r_no from restaurant where r_place = '동대문');
select * from menu;

 -- 커피중 '라떼' 종류 가격을 500원 인상
update menu
set m_price = m_price + 500
where m_name like '%라떼%';
select * from menu;


--- 라떼 종류 메뉴 다 삭제
delete menu
where m_name like '%라떼%';

-- 강남 스벅의 메뉴 다 삭제
delete menu, restaurant
where r_no  = m_place and
    r_name = '스타벅스' and r_place = '강남';

 -- 강남 스벅 폐점
delete restaurant
where r_name = '스타벅스' and r_place = '강남';

-- 가게 이름에 곱창 들어간 매장의 메뉴를 다삭제
delete menu
where m_name
    m_place = r_no and
    m_place in (
        select r_no
        from restaurant
        where r_name = '황소곱창'
    );