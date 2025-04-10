
-- select
-- 전체 과자 정보

select  *
from SNACK;

-- 모든 과자의 이름, 제조사, 가격


select s_name, S_MAKER, S_PRICE from SNACK;


-- 필드명이 마음에 안들면 as로 변경 가능
select s_name, s_maker as s_company, S_PRICE from snack;


-- 다시 과자 이름, 가격
select S_NAME,S_PRICE from  snack;
-- 이 상태에서 부가세 추가하고 싶음
select S_NAME,S_PRICE, s_price * 0.1 as S_VAT from  snack;


-- TEST

-- 전체 과자의 이름, 제조사, 가격, g당 얼마인지 (s_gram)

select s_name,S_MAKER,S_PRICE,round(s_price/S_WEIGHT, 2) as s_gram from SNACK;


-- vat, gram 레코드(행)계산 이었는데
-- 열 계산이 필요한 상황
-- 모든 과자들의 평균가격
-- 함수 : avg, max, min, sum, count
select avg(S_PRICE) from SNACK;
-- 제일 싼 과자 가격?
select min(S_PRICE) from SNACK;

select *from SNACK;

select max(S_EXP) from SNACK;

--과자의 개수
select count(s_no) from SNACK;
select count(S_NAME) from SNACK;
select count(*) from SNACK;

-- 전체 과자의 이름, 가격, 회사명
-- 과자의 이름이 '양파링' 인거 (조건)

select S_NAME, S_PRICE, S_MAKER
from SNACK
where s_name = '양파링';


-- 해태 과자의 평균가격
select avg(s_price)
from snack
where s_maker = '해태';

-- 먹으면 안되는 과자들의 이름, 가격
select s_name, S_PRICE
from SNACK
where S_EXP < sysdate;

--------------------------------------
    --'%ㅋ' : ㅋ으로 끝나는 것
    --'ㅋ%' : ㅋ으로 시작하는 것
    --'%ㅋ%': ㅋ이 포함된 것

-- 깡으로 끝나는 과자들의 이름, 가격
    select S_NAME, S_PRICE
    from SNACK
    where s_name like '%깡';


-- 홈런볼 시리즈의 모든 정보가 궁금
select  * from SNACK
where s_name like '홈런볼%';





-- 쉐이크(딸기맛), 쉐이크(초코맛), 초코쉐이크, 딸기쉐이크
select * from SNACK
where s_name like '%쉐이크%';


-- Test

-- 뺴뺴로 시리즈의 과자 이름, 가격
select S_NAME,S_PRICE from SNACK
where s_name like '%빼빼로%';

-- 깡 시리즈 과자 중 최고 가격
select max(S_PRICE) from SNACK
where s_name like '%깡%';


-- 제일 비싼 과자의 모든 정보
select * from SNACK
where S_PRICE =
      (select max(S_PRICE) from snack);

-------------------------
-- subQuery(쿼리속의 쿼리)

-- 제일 비싼 과자의 모든 정보를 뽑는다

select * from SNACK
where s_price = (select max(S_PRICE) from SNACK);



-- 평균가보다 비싼 과자(이름, 가격)
select s_name, S_PRICE
from SNACK
    where S_PRICE > (select avg(S_PRICE) from SNACK);



-- 제일 무거운 과자의 이름과 가격

select S_NAME, S_PRICe
from SNACK
where S_WEIGHT =
     (select max(S_WEIGHT) from SNACK);

-- 농심과자의 평균가보다 비싼 과자의 전체정보
select * from SNACK
    where S_PRICE >

          (select avg(S_PRICE) from SNACK where S_MAKER like '%농심%') ;


-- 해태 과자 중 제일 비싼 과자보다 싼 / 나머지 과자들의 전체정보

select * from SNACK
where S_PRICE <
    (select max(S_PRICE) from SNACK
    where S_MAKER like '%해태%');


-- 농심, 해태 과자 정보 전체
select * from SNACK
where S_MAKER = '농심' or S_MAKER = '해태';


-- 가격이 1000,1300인 과자들의 전체정보

select * from SNACK
where 1000 <= S_PRICE and S_PRICE <= 1300;

select * from SNACK
where S_PRICE between 1000 and 1300;

-- 정렬 order by 컬럼 desc (역순)
-- 해태 과자 전체를 이름순으로 정렬

select  * from SNACK where S_MAKER ='해태'
order by S_NAME desc;


-- 과자 전체 정보를 가격순으로 (오름차순)
select * from  SNACK order by S_PRICE asc, S_NAME desc ;

-- 1500원 전재산
-- 내가 사먹을 수 있는 과자 리스트(전체정보)
-- 유통기한이 긴걸 먼저

select * from SNACK
where S_PRICE <= 1500 order by S_EXP desc;











