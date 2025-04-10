-- DB 쿼리문 확인할 때 여기서 테스트 하시면 됩니다
-- test tbl 삭제하려면 db.sql 에서
-- trigger > seq > tbl 순서로 삭제
-- table 생성시 꼭 test table 로 생성!!!!!!!!!!!!!
-- db는 같이 쓰는 db도 있으니 삭제 하거나 변동 주기 전에 서로 공유 꼭 한번씩 해주세요!!

-- ==========================================
-- ========== TEST DATA INSERT ==========
-- ==========================================

-- TEST_USER 테이블 데이터 삽입

INSERT INTO TEST_USER (user_id, user_type, user_name, user_email, user_password, user_img, created_at)
VALUES ('user1', 1, '홍길동', 'user1@example.com', 'password1', '/images/user1.jpg', SYSDATE);

INSERT INTO TEST_USER (user_id, user_type, user_name, user_email, user_password, user_img, created_at)
VALUES ('user2', 2, '김철수', 'user2@example.com', 'password2', '/images/user2.jpg', SYSDATE);

INSERT INTO TEST_USER (user_id, user_type, user_name, user_email, user_password, user_img, created_at)
VALUES ('user3', 1, '이영희', 'user3@example.com', 'password3', '/images/user3.jpg', SYSDATE);


-- TEST_DIARY 테이블 데이터 삽입
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


-- TEST_LIVE_CHAT 테이블 데이터 삽입
INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user1', 'counselor1', SYSTIMESTAMP, NULL, '진행중');

INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user2', 'counselor2', SYSTIMESTAMP, NULL, '대기');

INSERT INTO TEST_LIVE_CHAT (session_id, user_id, counselor_id, start_time, end_time, status)
VALUES (TEST_LIVE_CHAT_SEQ.NEXTVAL, 'user3', 'counselor3', SYSTIMESTAMP, SYSTIMESTAMP, '완료');


-- TEST_COUNSELING_RESERVATION 테이블 데이터 삽입
INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time,
                                         category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user1', 'counselor1', SYSDATE + 1, 14, '건강', '대기', SYSDATE);

INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time,
                                         category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user2', 'counselor2', SYSDATE + 2, 16, '미래', '진행중', SYSDATE);

INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time,
                                         category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user3', 'counselor3', SYSDATE + 3, 18, '인간관계', '완료', SYSDATE);


-- TEST_COUNSELING_CONTENT 테이블 데이터 삽입
INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (1, '건강 관련 상담을 진행하였습니다.');

INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (2, '미래에 대한 고민을 상담하였습니다.');

INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (3, '인간관계에 대한 조언을 받았습니다.');



select * from MAIN_USER;
select * from KOYOI.TEST_USER;
select * from TEST_HABIT_TRACKING;
select * from TEST_HABIT_TRACKING;


select *
from TEST_LIVE_CHAT;
select  * from TEST_COUNSELING_SUMMARY;
select *
from main_LIVE_CHAT;
SELECT table_name
FROM all_tables
WHERE table_name = 'TEST_USER';
SELECT column_name, data_type, data_length
FROM all_tab_columns
WHERE table_name = 'TEST_USER'
  AND column_name = 'USER_ID';

SELECT column_name, constraint_type
FROM all_cons_columns
         JOIN all_constraints
              ON all_cons_columns.constraint_name = all_constraints.constraint_name
WHERE all_constraints.table_name = 'TEST_USER';

ALTER TABLE TEST_USER
    ADD CONSTRAINT pk_test_user PRIMARY KEY (user_id);

INSERT INTO TEST_USER (user_id, user_type, user_name, user_email, user_password, user_img, created_at)
VALUES ('user5', 1, '조조님', 'user5@example.com', 'password5', '/imgsource/usermypage_profiletest.jpg', SYSDATE);


SELECT *
FROM TEST_USER;



-- TEST_HABIT 테이블 데이터 삽입
INSERT INTO TEST_HABIT (habit_id, user_id, habit_name, created_at)
VALUES (1, 'user1', '운동', SYSDATE);
select *from TEST_HABIT;

INSERT INTO TEST_HABIT_TRACKING (tracking_id, habit_id, user_id, completed, weekly_feedback, tracking_date, created_at)
VALUES (1, 2, 'user1', 0, null, TO_DATE('2025-03-20', 'YYYY-MM-DD'), SYSTIMESTAMP);

INSERT INTO TEST_HABIT_TRACKING (tracking_id, habit_id, user_id, completed, weekly_feedback, tracking_date, created_at)
VALUES (2, 3, 'user1', 0, null, TO_DATE('2025-03-20', 'YYYY-MM-DD'), SYSTIMESTAMP);
select * from TEST_HABIT_TRACKING;


INSERT INTO TEST_USER (user_id, user_type, user_name, USER_NICKNAME, user_email, user_password, user_img, created_at)
VALUES ('admin001', 3, 'admin', 'admin', 'admin001@example.com', 'admin001', '/imgsource/admin001.jpg', SYSDATE);

INSERT INTO TEST_USER (user_id, user_type, user_name, user_email, user_password, user_img, created_at)
VALUES ('user7', 2, '에밀리', 'user7@example.com', 'password7', '/imgsource/user7.jpg', SYSDATE);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"Happiness comes to those who are prepared." – Thomas Edison', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001',
        '"Success is not final, failure is not fatal: It is the courage to continue that counts." – Winston Churchill',
        sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"The biggest risk is not taking any risk." – Mark Zuckerberg', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"Make today better than yesterday." – Unknown', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"Small changes make a big difference." – Unknown', sysdate);

select *
from TEST_QUOTE;

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin001', '서비스 점검 안내', '시스템 유지보수를 위해 3월 15일 오전 2시부터 4시까지 서비스가 중단됩니다.', SYSDATE);

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin001', '신규 기능 업데이트', '새로운 AI 감정 분석 기능이 추가되었습니다. 많은 이용 부탁드립니다.', SYSDATE);

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin001', '이벤트 안내', '사용자 감사 이벤트가 4월 1일부터 시작됩니다. 다양한 혜택을 확인하세요!', SYSDATE);

select *
from TEST_ANNOUNCEMENT;
select *
from TEST_DIARY;


CREATE TABLE TEST_LIVE_CHAT_LOG
(
    log_id     NUMBER PRIMARY KEY,                                      -- 메시지 고유 ID
    session_id NUMBER       NOT NULL,                                   -- 채팅 세션 ID
    sender     VARCHAR2(50) NOT NULL,                                   -- 메시지 보낸 사용자
    user_type  VARCHAR2(10) CHECK (user_type IN ('USER', 'COUNSELOR')), -- 발신자 유형
    message    CLOB         NOT NULL,                                   -- 메시지 내용
    timestamp  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                     -- 메시지 전송 시간
    CONSTRAINT fk_chat_log_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT (session_id),
    CONSTRAINT fk_chat_log_sender FOREIGN KEY (sender) REFERENCES TEST_USER (user_id)
);

-- CREATE TABLE TEST_LIVE_CHAT_PARTICIPANTS
-- (
--     session_id NUMBER       NOT NULL,               -- 채팅방 ID
--     user_id    VARCHAR2(50) NOT NULL,               -- 참여자 ID
--     joined_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 채팅방 입장 시간
--     left_at    TIMESTAMP,                           -- 퇴장 시간 (NULL이면 아직 참여 중)
--     PRIMARY KEY (session_id, user_id),
--     CONSTRAINT fk_chat_participant_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT (session_id),
--     CONSTRAINT fk_chat_participant_user FOREIGN KEY (user_id) REFERENCES TEST_USER (user_id)
-- );
--
-- CREATE TABLE TEST_LIVE_CHAT_NOTIFICATIONS
-- (
--     notification_id NUMBER PRIMARY KEY,                            -- 알림 고유 ID
--     session_id      NUMBER        NOT NULL,                        -- 채팅방 ID
--     user_id         VARCHAR2(50)  NOT NULL,                        -- 알림을 받는 사용자 ID
--     message         VARCHAR2(500) NOT NULL,                        -- 알림 내용
--     created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,           -- 알림 생성 시간
--     is_read         NUMBER(1) DEFAULT 0 CHECK (is_read IN (0, 1)), -- 읽음 여부 (0: 미확인, 1: 확인)
--     CONSTRAINT fk_chat_notification_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT (session_id),
--     CONSTRAINT fk_chat_notification_user FOREIGN KEY (user_id) REFERENCES TEST_USER (user_id)
-- );
-- CREATE TABLE TEST_COUNSELING_SUMMARY
-- (
--     summary_id         NUMBER PRIMARY KEY,                  -- 상담 요약 ID
--     session_id         NUMBER NOT NULL,                     -- 상담 세션 ID
--     counseling_summary CLOB,                                -- 상담 요약 내용
--     feedback           VARCHAR2(500),                       -- 상담사 피드백
--     created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 저장 시간
--     CONSTRAINT fk_counseling_summary_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT (session_id)
-- );
--

SELECT column_name, constraint_type
FROM all_cons_columns
         JOIN all_constraints
              ON all_cons_columns.constraint_name = all_constraints.constraint_name
WHERE all_constraints.table_name = 'TEST_LIVE_CHAT'
  AND column_name = 'SESSION_ID';


--라이브챗 >> 메인 테이블에서 오류나면 아래처럼 alter 후 다시 생성
ALTER TABLE TEST_LIVE_CHAT
    ADD CONSTRAINT pk_test_live_chat PRIMARY KEY (session_id);
SELECT column_name, constraint_type
FROM all_cons_columns
         JOIN all_constraints
              ON all_cons_columns.constraint_name = all_constraints.constraint_name
WHERE all_constraints.table_name = 'TEST_USER'
  AND column_name = 'USER_ID';

--라이브챗 >> 메인 테이블에서 오류나면 아래처럼 alter 후 다시 생성
ALTER TABLE TEST_USER
    ADD CONSTRAINT pk_test_user PRIMARY KEY (user_id);

SELECT *
FROM all_tables
WHERE table_name IN ('TEST_LIVE_CHAT', 'TEST_USER');

CREATE TABLE TEST_LIVE_CHAT_LOG
(
    log_id     NUMBER PRIMARY KEY,                                      -- 메시지 고유 ID
    session_id NUMBER       NOT NULL,                                   -- 채팅 세션 ID
    sender     VARCHAR2(50) NOT NULL,                                   -- 메시지 보낸 사용자
    user_type  VARCHAR2(10) CHECK (user_type IN ('USER', 'COUNSELOR')), -- 발신자 유형
    message    CLOB         NOT NULL,                                   -- 메시지 내용
    timestamp  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                     -- 메시지 전송 시간
    CONSTRAINT fk_chat_log_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT (session_id),
    CONSTRAINT fk_chat_log_sender FOREIGN KEY (sender) REFERENCES TEST_USER (user_id)
);



select *
from TEST_LIVE_CHAT_LOG;
SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_NAME = 'TEST_LIVE_CHAT';
select *
from TEST_LIVE_CHAT;
select *
from TEST_COUNSELING_RESERVATION;

DELETE FROM TEST_COUNSELING_RESERVATION;
COMMIT;
DELETE FROM TEST_LIVE_CHAT WHERE counseling_id IN (SELECT counseling_id FROM TEST_COUNSELING_RESERVATION);
COMMIT;

select  * from TEST_COUNSELING_RESERVATION;
select *
from TEST_USER;
select *
from TEST_LIVE_CHAT_LOG;
select *
from TEST_CHAT;



COMMIT;

CREATE TABLE Test_LIVE_CHAT
(
    session_id   NUMBER PRIMARY KEY,                                                            -- 실시간 채팅 세션 고유 ID
    user_id      VARCHAR2(50) NOT NULL,
    counseling_id number(5),
    counselor_id VARCHAR2(50),                                                                  -- 응답한 상담사 사용자 ID
    start_time   number(2)    NOT NULL,                                                         -- 채팅 시작 시간
    end_time     number(2),                                                                     -- 채팅 종료 시간 (종료 시점 기록)
    status       VARCHAR2(50) NOT NULL,                                                         -- 채팅 상태 (예: 진행중, 종료 등)
    CONSTRAINT fk_live_chat_user5 FOREIGN KEY (user_id) REFERENCES test_USER (user_id),          -- 사용자 외래키
    CONSTRAINT fk_live_chat_counselor5 FOREIGN KEY (counselor_id) REFERENCES test_USER (user_id), -- 상담사 외래키
    CONSTRAINT fk_live_chat_counseling5 FOREIGN KEY (counseling_id) REFERENCES TEST_COUNSELING_RESERVATION (COUNSELING_ID) -- 상담사 외래키
);

drop table TEST_LIVE_CHAT cascade constraint purge;

CREATE OR REPLACE TRIGGER trg_sync_live_chat_status
    AFTER UPDATE OF status ON TEST_COUNSELING_RESERVATION
    FOR EACH ROW
BEGIN
    UPDATE TEST_LIVE_CHAT
    SET status = :NEW.status
    WHERE counseling_id = :NEW.counseling_id;
END;

CREATE OR REPLACE TRIGGER trg_sync_counseling_status
    AFTER UPDATE OF status ON TEST_LIVE_CHAT
    FOR EACH ROW
BEGIN
    UPDATE TEST_COUNSELING_RESERVATION
    SET status = :NEW.status
    WHERE counseling_id = :NEW.counseling_id;
END;

CREATE OR REPLACE TRIGGER TRG_SYNC_COUNSELING_STATUS
    BEFORE UPDATE ON TEST_COUNSELING_RESERVATION
    FOR EACH ROW
BEGIN
    UPDATE TEST_LIVE_CHAT
    SET STATUS = :NEW.STATUS
    WHERE COUNSELING_ID = :NEW.COUNSELING_ID;
END;

SELECT cr.*, lc.session_id FROM TEST_COUNSELING_RESERVATION cr, TEST_LIVE_CHAT lc
WHERE cr.COUNSELING_ID = lc.counseling_id and cr.COUNSELING_ID = 398;

SELECT * FROM test_user WHERE user_id = 'user5';

select  * from TEST_LIVE_CHAT_LOG;
select  * from TEST_LIVE_CHAT;
create sequence TEST_LIVE_CHAT_LOG_SEQ;
UPDATE test_counseling_reservation  SET status = '완료' WHERE counseling_id = 444;
CREATE SEQUENCE TEST_LIVE_CHAT_SEQ;

SELECT test_live_chat_seq.CURRVAL FROM dual;
SELECT test_live_chat_seq.NEXTVAL FROM dual;

SELECT SEQUENCE_NAME
FROM USER_SEQUENCES
WHERE SEQUENCE_NAME = 'TEST_LIVE_CHAT_SEQ';

select * from TEST_HABIT_TRACKING;

select * from MAIN_USER;
select * from test_USER;

INSERT INTO MAIN_USER (
    user_id,
    user_type,
    user_name,
    user_nickname,
    user_email,
    user_password,
    user_img,
    created_at
) VALUES (
             'user5',                            -- user_id
             2,                                 -- user_type (2 = 일반 유저)
             '조조양',                            -- user_name
             '조조테스트걸',                    -- user_nickname
             'jojo@example.com',               -- user_email
             'jojo',                            -- user_password (실제로는 암호화 필요!)
             '/imgsource/usermypage_profiletest.jpg', -- user_img
             SYSDATE                            -- created_at
         );



SELECT * from MAIN_USER;

INSERT INTO MAIN_CHAT (
    chat_id,
    user_id,
    chat_summary
) VALUES (
             MAIN_CHAT_SEQ.NEXTVAL,   -- 자동 증가 시퀀스
             'jojo',
             '오늘은 스트레스를 많이 받았어요. 일에 대한 고민이 많습니다.'
         );

INSERT INTO MAIN_USER (
    user_id,
    user_type,
    user_name,
    user_nickname,
    user_email,
    user_password,
    user_img,
    created_at
) VALUES (
             'user5',                            -- 사용자 ID
             2,                                  -- 사용자 유형 (2 = 유저)
             '유저오',                            -- 사용자 이름 (원하는 이름으로 수정 가능)
             '유저오',                      -- 닉네임 (jojo와 동일하게 테스트용)
             'user5@example.com',                -- 이메일
             'user5',                            -- 비밀번호 (암호화 전 예시)
             '/imgsource/usermypage_profiletest.jpg', -- 이미지 경로
             SYSDATE                             -- 생성일시
         );


INSERT INTO MAIN_CHAT (
    chat_id,
    user_id,
    chat_summary
) VALUES (
             MAIN_CHAT_SEQ.NEXTVAL,         -- chat_id 자동 증가
             'user5',                       -- user_id
             '오늘은 기분이 좋아서 산책을 다녀왔어요. 챗봇과의 대화도 즐거웠어요.' -- 요약 내용
         );

INSERT INTO MAIN_USER (user_id, user_type, user_name, user_nickname, user_email, user_password, user_img)
VALUES ('counselor001', 1, '상담사', '상담선생님', 'counselor001@example.com', 'test1234', '/imgsource/usermypage_profiletest.jpg');
SELECT * FROM MAIN_USER WHERE user_id = 'user5';
SELECT * FROM MAIN_USER WHERE user_id = 'counselor001';
SELECT LENGTH(user_id), user_id FROM MAIN_USER WHERE user_id = 'user5';
COMMIT;

SELECT * FROM MAIN_COUNSELING_RESERVATION WHERE user_id NOT IN (SELECT user_id FROM MAIN_USER);
UPDATE MAIN_USER
SET user_id = TRIM(user_id);
COMMIT;
SELECT * FROM USER_CONSTRAINTS
WHERE TABLE_NAME = 'MAIN_COUNSELING_RESERVATION'
  AND CONSTRAINT_NAME = 'FK_CR_USER';

SELECT user_id, LENGTH(user_id) FROM MAIN_USER WHERE user_id = 'counselor001';
SELECT user_id, LENGTH(user_id) FROM MAIN_USER WHERE user_id = 'user5';
-- 지금 상태 확인
SELECT user_id FROM MAIN_USER;

-- 없다면 삽입
INSERT INTO MAIN_USER (
    user_id, user_type, user_name, user_nickname, user_email, user_password
) VALUES (
             'user5', 2, '테스트유저', '유저5', 'user5@example.com', '1234'
         );
COMMIT;
SELECT user_id, LENGTH(user_id) FROM MAIN_USER WHERE user_id LIKE '%user%';

SELECT column_name, data_type, data_length
FROM all_tab_columns
WHERE table_name = 'MAIN_USER' AND column_name = 'USER_ID';
SELECT user_id, LENGTH(user_id), DUMP(user_id)
FROM MAIN_USER
WHERE user_id = 'user5';
SELECT * FROM MAIN_USER WHERE user_id IN ('user5', 'counselor001');


-- 혹시 'user5' 비슷한 게 있으면 먼저 삭제
DELETE FROM MAIN_USER WHERE TRIM(user_id) = 'user5';
COMMIT;

-- 다시 정확히 삽입
INSERT INTO MAIN_USER (
    user_id,
    user_type,
    user_name,
    user_nickname,
    user_email,
    user_password,
    user_img,
    created_at
) VALUES (
             'user5',
             2,
             '테스트유저',
             '테스트닉네임',
             'user5@example.com',
             'user5',
             '/imgsource/usermypage_profiletest.jpg',
             SYSDATE
         );
COMMIT;

-- 삽입 후 다시 확인
SELECT user_id, LENGTH(user_id), DUMP(user_id)
FROM MAIN_USER
WHERE user_id = 'user5';

select * from main_user;
select * from test_user;

-- main유저 디비지우기
select * from test_user;

select * from MAIN_CHAT;
select * from test_CHAT;

SELECT user_id, user_img FROM test_user WHERE user_img LIKE '%default%';

insert into test_chat values (11,'jojot','하이하이','코코코코코코ㅗ코코',SYSDATE);
insert into test_chat values (12,'jojot','마이마이','기분이 너무좋아', SYSDATE);

select * from TEST_COUNSELING_RESERVATION;


    CREATE TABLE TEST_COUNSELING_RESERVATION
(
    counseling_id   NUMBER PRIMARY KEY,                                                    -- 상담 예약 고유 ID
    user_id         VARCHAR2(50)                 NOT NULL,                                 -- 상담을 신청한 사용자 ID
    counselor_id    VARCHAR2(50)                 NOT NULL,                                 -- 예약된 상담사 사용자 ID
    counseling_date DATE                         NOT NULL,                                 -- 상담 날짜
    counseling_time NUMBER(2) CHECK (counseling_time BETWEEN 10 AND 22),                   -- 상담 시간대 (10시~22시)
    category        VARCHAR2(20) CHECK (category IN ('健康', '将来', '人間関係', 'その他のお悩み')),         -- 상담 카테고리
    status          VARCHAR2(20) DEFAULT '待機中' CHECK (status IN ('待機中',  '完了')), -- 상담 진행 상태
    created_at      DATE         DEFAULT SYSDATE NOT NULL,                                 -- 예약 생성일시
    CONSTRAINT fk_cr_user FOREIGN KEY (user_id) REFERENCES test_USER (user_id),            -- 사용자 외래키
    CONSTRAINT fk_cr_counselor FOREIGN KEY (counselor_id) REFERENCES test_USER (user_id)   -- 상담사 외래키
);

ALTER TABLE TEST_COUNSELING_RESERVATION
    DROP CONSTRAINT SYS_C0035078; -- 실제 제약조건 이름 확인 필요

ALTER TABLE TEST_COUNSELING_RESERVATION
    ADD CONSTRAINT chk_category
        CHECK (category IN ('健康', '将来', '人間関係', 'その他のお悩み'));

DROP TABLE TEST_COUNSELING_RESERVATION CASCADE CONSTRAINTS;

SELECT constraint_name
FROM user_constraints
WHERE table_name = 'TEST_COUNSELING_RESERVATION'
  AND constraint_type = 'C';

SELECT constraint_name, search_condition
FROM user_constraints
WHERE table_name = 'TEST_COUNSELING_RESERVATION'
  AND constraint_type = 'C';

ALTER TABLE TEST_COUNSELING_RESERVATION
    MODIFY category VARCHAR2(30);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"勝負においては、自分が苦しいときは相手も苦しいのである。" – 羽生善治', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"暗くならなければ、星は見えない。" – チャールズ・A・ビアード', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"お前の道を進め。人には勝手なことを言わせておけ。" – ダンテ・アリギエーリ', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"挑戦しなければ、定められた人生を歩くしかない。" – ビヴァリー・シルズ', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001',     '"人生の意味とは、自分がやりたいと思うことをすること。" - パウロ・コエーリョ', sysdate);

select * from TEST_QUOTE;
SELECT chat_title, DBMS_LOB.SUBSTR(chat_summary, 4000, 1) FROM test_chat WHERE user_id = 'jojot';


INSERT INTO TEST_CHAT (chat_id, user_id, chat_title, chat_summary, create_at)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'test02', '오늘 하루 어땠어?', '오늘은 기분이 좋았고, 친구랑 산책을 했어!', SYSDATE);


INSERT INTO TEST_CHAT (chat_id, user_id, chat_title, chat_summary, create_at)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'test02', '스트레스 해소법', '스트레스가 쌓여서 음악 들으면서 마음을 진정시켰어.', SYSDATE);

INSERT INTO TEST_CHAT (chat_id, user_id, chat_title, chat_summary, create_at)
VALUES (TEST_CHAT_SEQ.NEXTVAL, 'test02', '내일 계획', '내일은 아침에 운동하고 책을 읽을 계획이야.', SYSDATE);


SELECT chat_id, chat_title, DBMS_LOB.SUBSTR(chat_summary, 4000, 1)
FROM test_chat
WHERE user_id = 'jojot';

select  * from test_user;

INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「他人や過去は変えられないが、未来と自分は変えられる」 - エリック・バーン', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「自分のことを、この世の誰とも比べてはいけない」 - ビル･ゲイツ', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「自分の姿をありのまま直視する、それは強さだ」 - 岡本太郎', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「世界には、君以外には誰も歩むことのできない唯一の道がある」 - フリードリヒ・ニーチェ', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「暗くならなければ、星は見えない」 - チャールズ・A・ビアード', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「希望は強い勇気であり、あらたな意志である」 - マルティン・ルター', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「お前の道を進め。人には勝手なことを言わせておけ」 - ダンテ・アリギエーリ', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「挑戦しなければ、定められた人生を歩くしかない」 - ビヴァリー・シルズ', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「成功とはただ一つしかない。自分の人生を自分の流儀で過ごせることだ」 - クリストファー・モーリー', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「人生の意味とは、自分がやりたいと思うことをすること」 - パウロ・コエーリョ', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「楽しい顔で食べれば、皿一つでも宴会だ」 - ブルデンチウス', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「夢を見るから、人生は輝く」 - ヴォルフガング・アマデウス・モーツァルト', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「人を信じよ、しかし、その百倍も自らを信じよ」 - 手塚治虫', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「自分にもっとやさしくなってもいいのよ」 - アドリエンヌ・リッチ', SYSDATE);
INSERT INTO MAIN_QUOTE (admin_id, content, created_at) VALUES ('admin1', '「前を向いて、できるだけ幸せでいましょう」 - ドロシー・パーカー', SYSDATE);

SELECT * FROM MAIN_QUOTE;

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【システムメンテナンスのお知らせ】', '安定したサービス提供のため、下記のスケジュールで定期メンテナンスを実施いたします。

■日時：2025年4月5日（土）午前2時〜4時
■影響：上記時間帯、サービスをご利用いただけません。

ご不便をおかけしますが、ご理解のほどよろしくお願いいたします。', TO_DATE('2025-04-03'));

INSERT INTO MAIN_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【AI感情分析の新機能リリース】', 'あなたの感情データをもとに、毎日おすすめの名言やルーティンをAIが提案します。

一日の始まりを、心に響く言葉と共に。
マイページ ＞ 感情分析 でご確認ください。', TO_DATE('2025-04-02'));

INSERT INTO MAIN_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【4月継続チャレンジイベント開催】', '4月中、毎日日記を記録し、チェックリストを完了すると抽選でプレゼントが当たります！

■参加方法：1日1回日記＋習慣チェック
■期間：4月1日～4月30日
■発表：5月2日　お知らせ欄にて

たくさんのご参加をお待ちしております！', TO_DATE('2025-04-01'));

-- 以下、過去のお知らせ
INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【マイページのUIを改善しました】', 'ユーザーの皆様のご意見を反映し、マイページのUIをより使いやすく改善しました。

・プロフィール画像の変更機能を追加
・相談予約履歴を可視化
・チャットボットの要約表示機能追加', TO_DATE('2025-03-31'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【プライバシーポリシー改定のお知らせ】', '2025年4月1日より、新しいプライバシーポリシーが適用されます。

■主な変更点
・感情分析およびAI推薦に関するデータの収集と利用目的の明記
・データ保存期間の詳細化

詳細はプライバシーポリシーをご覧ください。', TO_DATE('2025-03-30'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【専門カウンセラーとの連携サービス開始】', '感情記録やチャットボットで解消できない時は、
専門のカウンセラーとリアルタイムで相談できます。

・チャット機能付き相談
・カウンセラープロフィールの確認・予約可能', TO_DATE('2025-03-29'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【カレンダー機能の不具合修正】', '一部のユーザーで発生していた、
カレンダー日付クリック時にムードチャートが表示されない問題を修正しました。', TO_DATE('2025-03-28'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【習慣リマインダー通知機能追加】', '毎日のルーティンを忘れずに実行できるように、
リマインダー機能を導入しました。

マイページ ＞ 通知設定 からオン/オフ可能です。', TO_DATE('2025-03-27'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【感性モードのテーマ追加】', '目に優しいダークテーマ「感性モード」が新しく追加されました。
設定 ＞ テーマ からご利用いただけます。', TO_DATE('2025-03-26'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【チャットボットの精度向上】', 'AI感情分析アルゴリズムを改善し、
より繊細で的確なフィードバックが可能となりました。', TO_DATE('2025-03-25'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【100の心に響く名言を追加】', '「今日のひとこと」スライダーに新たに100個の名言を追加しました。
マイページやメイン画面からご覧いただけます。', TO_DATE('2025-03-24'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【出席チェックイベント開始】', '毎日ログインするだけでマインドコインをプレゼント！

・7日連続ログインで100コイン
・30日達成で追加特典あり', TO_DATE('2025-03-23'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【相談時間10分前に通知機能追加】', '予約された相談時間の10分前に、通知が自動で送信されます。
忘れずに相談をスタートしましょう！', TO_DATE('2025-03-22'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【ヘルスアプリとの連携機能追加】', 'あなたの活動量や睡眠データを基に、
感情パターンを分析できるようになりました。', TO_DATE('2025-03-21'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【相談記録の保存期間について】', '相談履歴は6ヶ月間保管され、その後自動的に削除されます。
必要な方は事前にバックアップをお願いします。', TO_DATE('2025-03-20'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【日記投稿は1日1回まで】', 'より充実した記録を残すために、
1日1回のみ日記を書けるように仕様変更しました。', TO_DATE('2025-03-19'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【ウェブアクセシビリティ向上】', 'キーボード操作や読み上げソフトにも対応し、
誰でも使いやすいUIへと改善を行いました。', TO_DATE('2025-03-18'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【モバイル表示の最適化】', 'モバイルユーザーの利便性を向上するため、
全体UIをレスポンシブデザインに改善しました。', TO_DATE('2025-03-17'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【サーバー移行作業のお知らせ】', 'より快適なサービス提供のため、
2025年4月10日にサーバー移行作業を予定しております。

作業時間中は一時的に接続が不安定になる可能性があります。', TO_DATE('2025-03-16'));

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin1', '【カウンセラー評価機能を追加】', '相談終了後、担当カウンセラーへのフィードバックを投稿できるようになりました。
皆様の声を今後のサービス改善に活かしてまいります。', TO_DATE('2025-03-15'));

SELECT * FROM TEST_LIVE_CHAT WHERE counseling_id = 657;
SELECT TEST_LIVE_CHAT_SEQ.CURRVAL FROM dual;

SELECT cr.*, lc.session_id
FROM TEST_COUNSELING_RESERVATION cr, TEST_LIVE_CHAT lc
WHERE cr.COUNSELING_ID = lc.counseling_id
  AND cr.COUNSELING_ID = 657;


SELECT *
FROM TEST_LIVE_CHAT
WHERE counseling_id = 661;  -- ← 방금 insert된 상담 ID

-- 중복되었다면 삭제
DELETE FROM TEST_LIVE_CHAT
WHERE counseling_id = 661 AND session_id != 229;
