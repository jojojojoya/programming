-- DB 쿼리문 확인할 때 여기서 테스트 하시면 됩니다
-- test tbl 삭제하려면 db.sql 에서
-- trigger > seq > tbl 순서로 삭제
-- table 생성시 꼭 test table 로 생성!!!!!!!!!!!!!
-- db는 같이 쓰는 db도 있으니 삭제 하거나 변동 주기 전에 서로 공유 꼭 한번씩 해주세요!!

<<<<<<< HEAD
=======

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
INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user1', 'counselor1', SYSDATE + 1, 14, '건강', '대기', SYSDATE);

INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user2', 'counselor2', SYSDATE + 2, 16, '미래', '진행중', SYSDATE);

INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at)
VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, 'user3', 'counselor3', SYSDATE + 3, 18, '인간관계', '완료', SYSDATE);


-- TEST_COUNSELING_CONTENT 테이블 데이터 삽입
INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (1, '건강 관련 상담을 진행하였습니다.');

INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (2, '미래에 대한 고민을 상담하였습니다.');

INSERT INTO TEST_COUNSELING_CONTENT (counseling_id, counseling_content)
VALUES (3, '인간관계에 대한 조언을 받았습니다.');

select * from TEST_USER;
select * from TEST_LIVE_CHAT;

SELECT table_name FROM all_tables WHERE table_name = 'TEST_USER';
SELECT column_name, data_type, data_length
FROM all_tab_columns
WHERE table_name = 'TEST_USER'
  AND column_name = 'USER_ID';

SELECT column_name, constraint_type
FROM all_cons_columns
         JOIN all_constraints
              ON all_cons_columns.constraint_name = all_constraints.constraint_name
WHERE all_constraints.table_name = 'TEST_USER';

ALTER TABLE TEST_USER ADD CONSTRAINT pk_test_user PRIMARY KEY (user_id);

INSERT INTO TEST_USER (user_id, user_type, user_name, user_email, user_password, user_img, created_at)
VALUES ('user5', 1, '조조님', 'user5@example.com', 'password5', '/imgsource/usermypage_profiletest.jpg', SYSDATE);
<<<<<<< HEAD
-- >>>>>>> 9ce381165894c55d2627bf310e0348c139fa15e2
SELECT * FROM TEST_USER;


-- TEST_HABIT 테이블 데이터 삽입
INSERT INTO TEST_HABIT (habit_id, user_id, habit_name, created_at)
VALUES (5, 'user5', '식사', SYSDATE);
select *from TEST_HABIT;
=======

INSERT INTO TEST_USER (user_id, user_type, user_name, USER_NICKNAME, user_email, user_password, user_img, created_at)
VALUES ('admin001', 3, 'admin', 'admin', 'admin001@example.com', 'admin001', '/imgsource/admin001.jpg', SYSDATE);

INSERT INTO TEST_USER (user_id, user_type, user_name, user_email, user_password, user_img, created_at)
VALUES ('user7', 2, '에밀리', 'user7@example.com', 'password7', '/imgsource/user7.jpg', SYSDATE);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"Happiness comes to those who are prepared." – Thomas Edison', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"Success is not final, failure is not fatal: It is the courage to continue that counts." – Winston Churchill', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"The biggest risk is not taking any risk." – Mark Zuckerberg', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"Make today better than yesterday." – Unknown', sysdate);

INSERT INTO TEST_QUOTE (admin_id, content, created_at)
VALUES ('admin001', '"Small changes make a big difference." – Unknown', sysdate);

select * from TEST_QUOTE;

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ( 'admin001', '서비스 점검 안내', '시스템 유지보수를 위해 3월 15일 오전 2시부터 4시까지 서비스가 중단됩니다.', SYSDATE);

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin001', '신규 기능 업데이트', '새로운 AI 감정 분석 기능이 추가되었습니다. 많은 이용 부탁드립니다.', SYSDATE);

INSERT INTO TEST_ANNOUNCEMENT (admin_id, title, content, created_at)
VALUES ('admin001', '이벤트 안내', '사용자 감사 이벤트가 4월 1일부터 시작됩니다. 다양한 혜택을 확인하세요!', SYSDATE);

select * from TEST_ANNOUNCEMENT;
select * from TEST_DIARY;



CREATE TABLE TEST_LIVE_CHAT_LOG (
                                    log_id NUMBER PRIMARY KEY, -- 메시지 고유 ID
                                    session_id NUMBER NOT NULL, -- 채팅 세션 ID
                                    sender VARCHAR2(50) NOT NULL, -- 메시지 보낸 사용자
                                    user_type VARCHAR2(10) CHECK (user_type IN ('USER', 'COUNSELOR')), -- 발신자 유형
                                    message CLOB NOT NULL, -- 메시지 내용
                                    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 메시지 전송 시간
                                    CONSTRAINT fk_chat_log_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id),
                                    CONSTRAINT fk_chat_log_sender FOREIGN KEY (sender) REFERENCES TEST_USER(user_id)
);
CREATE TABLE TEST_LIVE_CHAT_PARTICIPANTS (
                                             session_id NUMBER NOT NULL, -- 채팅방 ID
                                             user_id VARCHAR2(50) NOT NULL, -- 참여자 ID
                                             joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 채팅방 입장 시간
                                             left_at TIMESTAMP, -- 퇴장 시간 (NULL이면 아직 참여 중)
                                             PRIMARY KEY (session_id, user_id),
                                             CONSTRAINT fk_chat_participant_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id),
                                             CONSTRAINT fk_chat_participant_user FOREIGN KEY (user_id) REFERENCES TEST_USER(user_id)
);

CREATE TABLE TEST_LIVE_CHAT_NOTIFICATIONS (
                                              notification_id NUMBER PRIMARY KEY, -- 알림 고유 ID
                                              session_id NUMBER NOT NULL, -- 채팅방 ID
                                              user_id VARCHAR2(50) NOT NULL, -- 알림을 받는 사용자 ID
                                              message VARCHAR2(500) NOT NULL, -- 알림 내용
                                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 알림 생성 시간
                                              is_read NUMBER(1) DEFAULT 0 CHECK (is_read IN (0,1)), -- 읽음 여부 (0: 미확인, 1: 확인)
                                              CONSTRAINT fk_chat_notification_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id),
                                              CONSTRAINT fk_chat_notification_user FOREIGN KEY (user_id) REFERENCES TEST_USER(user_id)
);
CREATE TABLE TEST_COUNSELING_SUMMARY (
                                         summary_id NUMBER PRIMARY KEY, -- 상담 요약 ID
                                         session_id NUMBER NOT NULL, -- 상담 세션 ID
                                         counseling_summary CLOB, -- 상담 요약 내용
                                         feedback VARCHAR2(500), -- 상담사 피드백
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 저장 시간
                                         CONSTRAINT fk_counseling_summary_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id)
);


SELECT column_name, constraint_type
FROM all_cons_columns
         JOIN all_constraints
              ON all_cons_columns.constraint_name = all_constraints.constraint_name
WHERE all_constraints.table_name = 'TEST_LIVE_CHAT'
  AND column_name = 'SESSION_ID';


--라이브챗 >> 메인 테이블에서 오류나면 아래처럼 alter 후 다시 생성
ALTER TABLE TEST_LIVE_CHAT ADD CONSTRAINT pk_test_live_chat PRIMARY KEY (session_id);
SELECT column_name, constraint_type
FROM all_cons_columns
         JOIN all_constraints
              ON all_cons_columns.constraint_name = all_constraints.constraint_name
WHERE all_constraints.table_name = 'TEST_USER'
  AND column_name = 'USER_ID';

--라이브챗 >> 메인 테이블에서 오류나면 아래처럼 alter 후 다시 생성
ALTER TABLE TEST_USER ADD CONSTRAINT pk_test_user PRIMARY KEY (user_id);

SELECT * FROM all_tables WHERE table_name IN ('TEST_LIVE_CHAT', 'TEST_USER');

CREATE TABLE TEST_LIVE_CHAT_LOG (
                                    log_id NUMBER PRIMARY KEY, -- 메시지 고유 ID
                                    session_id NUMBER NOT NULL, -- 채팅 세션 ID
                                    sender VARCHAR2(50) NOT NULL, -- 메시지 보낸 사용자
                                    user_type VARCHAR2(10) CHECK (user_type IN ('USER', 'COUNSELOR')), -- 발신자 유형
                                    message CLOB NOT NULL, -- 메시지 내용
                                    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 메시지 전송 시간
                                    CONSTRAINT fk_chat_log_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id),
                                    CONSTRAINT fk_chat_log_sender FOREIGN KEY (sender) REFERENCES TEST_USER(user_id)
);

CREATE TABLE TEST_LIVE_CHAT_PARTICIPANTS (
                                             session_id NUMBER NOT NULL, -- 채팅방 ID
                                             user_id VARCHAR2(50) NOT NULL, -- 참여자 ID
                                             joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 채팅방 입장 시간
                                             left_at TIMESTAMP, -- 퇴장 시간 (NULL이면 아직 참여 중)
                                             PRIMARY KEY (session_id, user_id),
                                             CONSTRAINT fk_chat_participant_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id),
                                             CONSTRAINT fk_chat_participant_user FOREIGN KEY (user_id) REFERENCES TEST_USER(user_id)
);


CREATE TABLE TEST_LIVE_CHAT_NOTIFICATIONS (
                                              notification_id NUMBER PRIMARY KEY, -- 알림 고유 ID
                                              session_id NUMBER NOT NULL, -- 채팅방 ID
                                              user_id VARCHAR2(50) NOT NULL, -- 알림을 받는 사용자 ID
                                              message VARCHAR2(500) NOT NULL, -- 알림 내용
                                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 알림 생성 시간
                                              is_read NUMBER(1) DEFAULT 0 CHECK (is_read IN (0,1)), -- 읽음 여부 (0: 미확인, 1: 확인)
                                              CONSTRAINT fk_chat_notification_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id),
                                              CONSTRAINT fk_chat_notification_user FOREIGN KEY (user_id) REFERENCES TEST_USER(user_id)
);

CREATE TABLE TEST_COUNSELING_SUMMARY (
                                         summary_id NUMBER PRIMARY KEY, -- 상담 요약 ID
                                         session_id NUMBER NOT NULL, -- 상담 세션 ID
                                         counseling_summary CLOB, -- 상담 요약 내용
                                         feedback VARCHAR2(500), -- 상담사 피드백
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 저장 시간
                                         CONSTRAINT fk_counseling_summary_session FOREIGN KEY (session_id) REFERENCES TEST_LIVE_CHAT(session_id)
);


select * from TEST_LIVE_CHAT;
select * from TEST_COUNSELING_RESERVATION;

select * from TEST_USER;

select * from TEST_CHAT;
