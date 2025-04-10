insert into SHOP_INFO values (SHOP_NO_SEQ.nextval, 'jj@naver.com', '스즈키', '홋카이도', '03-1234-5678', 'ㅎ', '10:00-12:00', '테스트');
select *from SHOP_INFO;

insert into MENU_INFO values (MENU_NO_SEQ.nextval, '21', '일식','스시', '5000');
select * from MENU_INFO;
insert into MENU_INFO values (MENU_NO_SEQ.nextval, '21', '일식', '규동', '1000');
select * from MENU_INFO;

select * from menu_info;
select * from MENU_INFO where MENU_SHOP='21';


insert into REVIEW_INFO values (REVIEW_NO_SEQ.nextval, '3', '맛있어요', '2025-01-01', '조조', '5');
select * from menu_info where menu_shop='21';
