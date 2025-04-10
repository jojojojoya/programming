--
-- 자료형
--
-- 글자(string)
--
--     글자수 자리에
--     - 3 char => 3글자  << 이 방식 ('쓴다')
--     - 3 => 3bytes << 이 방식 안쓴다
--
--
--     한글 => 1글자 3bytes
--     영어 => 1글자 1byte
--
--
--     - 불변
--     char(5)
--     char(5 char) >> 絶対5文字  무조건 5글자
--     char is character
--
--     - 가변
--     varchar2(5)
--     varchar2(5 char) - 5글자까지 자유롭게, 남은 공간은 반환. 고정된 값 제외하고 무조건 가변 쓰면됨
--     varchar is variety character
--
--
--
-- --------------------------
--
-- 숫자
--
--     number(자리수)
--     number(3): 최대 3자리 정수를 넣을 수 있음 : 999
--
--     number(자리수, 자리수)
--     number(5,2) : 전체 숫자 5자리 소수점 이하 2자리 123.45
--
-- --------------------------

날짜
--------------------------
1) 테이블 생성 - 문구류
create table product_test(
    p_name varchar2(20 char),
    p_price number(6)
);

2) 값 넣기
insert into product_test values('우왕펜',2000);

3) 결과 조회
select *from product_test;

-- > 이후 커밋을 하면 영구적으로 데이터 저장


