-- DB 쿼리문 확인할 때 여기서 테스트 하시면 됩니다
-- test tbl 삭제하려면 db.sql 에서
-- trigger > seq > tbl 순서로 삭제
-- table 생성시 꼭 test table 로 생성!!!!!!!!!!!!!
-- db는 같이 쓰는 db도 있으니 삭제 하거나 변동 주기 전에 서로 공유 꼭 한번씩 해주세요!!


-- TEST_USER 테이블 데이터 삽입


INSERT INTO TEST_USER (user_id, user_type, user_name,user_nickname,  user_email, user_password, user_img, created_at)
VALUES ('user1', 1, '홍길동','홍홍이', 'user1@example.com', 'password1', '/images/user1.jpg', SYSDATE);
INSERT INTO TEST_USER (user_id, user_type, user_name,user_nickname,  user_email, user_password, user_img, created_at)
VALUES ('user5', 1, '조조님', '조조사마','user5@example.com', 'user5', '/imgsource/usermypage_profiletest.jpg', SYSDATE);
INSERT INTO TEST_USER (user_id, user_type, user_name,user_nickname,  user_email, user_password, user_img, created_at)
VALUES ('counselor2', 2, '카운2', '카운쨩','coun2@example.com', 'coun2', '/imgsource/usermypage_profiletest.jpg', SYSDATE);


INSERT INTO TEST_USER (user_id, user_type, user_name,user_nickname,  user_email, user_password, user_img, created_at)
VALUES ('counselor001', 2, '카운1', '카운셀러님','coun1@example.com', 'coun001', '/imgsource/usermypage_profiletest.jpg', SYSDATE);





INSERT INTO TEST_DIARY (diary_id, user_id, diary_content, created_at)
VALUES (TEST_DIARY_SEQ.NEXTVAL, 'user1', '오늘은 기분이 좋다!', SYSDATE);

INSERT INTO TEST_DIARY (diary_id, user_id, diary_content, created_at)
VALUES (TEST_DIARY_SEQ.NEXTVAL, 'user2', '비가 와서 조금 우울했다.', SYSDATE);


INSERT INTO TEST_DIARY (diary_id, user_id, diary_content, created_at)
VALUES (TEST_DIARY_SEQ.NEXTVAL, 'user3', '주말에 친구랑 여행을 다녀왔다.', SYSDATE);


-- TEST_EMOTION 테이블 데이터 삽입
INSERT INTO TEST_EMOTION (emotion_id, user_id, diary_id, emotion_score, emotion_emoji, recorded_at)
VALUES (TEST_EMOTION_SEQ.NEXTVAL, 'user1', 1, 8, '😊', SYSDATE);

INSERT INTO TEST_EMOTION (emotion_id, user_id, diary_id, emotion_score, emotion_emoji, recorded_at)
VALUES (TEST_EMOTION_SEQ.NEXTVAL, 'user2', 2, 4, '😞', SYSDATE);

INSERT INTO TEST_EMOTION (emotion_id, user_id, diary_id, emotion_score, emotion_emoji, recorded_at)
VALUES (TEST_EMOTION_SEQ.NEXTVAL, 'user3', 3, 7, '😁', SYSDATE);


-- TEST_CHAT 테이블 데이터 삽입
INSERT INTO TEST_CHAT (chat_id, user_id, chat_summary)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'user1', '오늘 하루는 평온했다.');

INSERT INTO TEST_CHAT (chat_id, user_id, chat_summary)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'user2', '스트레스가 많았던 하루였다.');

INSERT INTO TEST_CHAT (chat_id, user_id, chat_summary)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'user3', '새로운 프로젝트를 시작했다.');


INSERT INTO TEST_CHAT (chat_id, user_id, chat_summary)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'user5', '나는 10년 후에 뭘하고 있을까');

INSERT INTO TEST_CHAT (chat_id, user_id, chat_summary)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'user5', '나를 괴롭히는 것들');


INSERT INTO TEST_CHAT (chat_id, user_id, chat_summary)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'user5', '최고의 최악의 기억');

select * from TEST_CHAT;
select * from TEST_COUNSELING_CONTENT;
select * from TEST_live_CHAT;

select * from TEST_COUNSELING_RESERVATION;
select * from TEST_COUNSELING_CONTENT;

delete test_chat where CHAT_ID = '2' and USER_ID = 'user4';

-- TEST_LIVE_CHAT 테이블 데이터 삽입
INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user1', 'counselor1', SYSTIMESTAMP, NULL, '진행중');

INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user2', 'counselor2', SYSTIMESTAMP, NULL, '대기');

INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user3', 'counselor3', SYSTIMESTAMP, SYSTIMESTAMP, '완료');


INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user5', 'counselor5', SYSTIMESTAMP, SYSTIMESTAMP, '완료');


INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user5', 'counselor001', SYSTIMESTAMP, SYSTIMESTAMP, '대기');


select  * from TEST_COUNSELING_RESERVATION;


-- TEST_COUNSELING_RESERVATION 테이블 데이터 삽입
INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user5', 'counselor1', SYSDATE + 1, 14, '건강', '대기', SYSDATE);

INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user2', 'counselor2', SYSDATE + 2, 16, '미래', '진행중', SYSDATE);

INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user3', 'counselor3', SYSDATE + 3, 18, '인간관계', '완료', SYSDATE);


select  * from TEST_COUNSELING_CONTENT;
-- TEST_COUNSELING_CONTENT 테이블 데이터 삽입
INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (1, '건강 관련 상담을 진행하였습니다.');

INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (2, '미래에 대한 고민을 상담하였습니다.');

INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (3, '인간관계에 대한 조언을 받았습니다.');

select * from TEST_USER;


