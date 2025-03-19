-- ==========================================
-- ========== DROP OBJECTS ==========
-- ==========================================

-- Drop Triggers
DROP TRIGGER MAIN_DIARY_TRG;
DROP TRIGGER MAIN_EMOTION_TRG;
DROP TRIGGER MAIN_CHAT_TRG;
DROP TRIGGER MAIN_LIVE_CHAT_TRG;
DROP TRIGGER MAIN_COUNSELING_RES_TRG;
DROP TRIGGER MAIN_ANNOUNCEMENT_TRG;
DROP TRIGGER MAIN_QUOTE_TRG;
DROP TRIGGER MAIN_HABIT_TRG;
DROP TRIGGER MAIN_HABIT_TRACKING_TRG;

DROP TRIGGER TEST_DIARY_TRG;
DROP TRIGGER TEST_EMOTION_TRG;
DROP TRIGGER TEST_CHAT_TRG;
DROP TRIGGER TEST_LIVE_CHAT_TRG;
DROP TRIGGER TEST_COUNSELING_RES_TRG;
DROP TRIGGER TEST_ANNOUNCEMENT_TRG;
DROP TRIGGER TEST_QUOTE_TRG;
DROP TRIGGER TEST_HABIT_TRG;
DROP TRIGGER TEST_HABIT_TRACKING_TRG;

-- Drop Sequences
DROP SEQUENCE MAIN_DIARY_SEQ;
DROP SEQUENCE MAIN_EMOTION_SEQ;
DROP SEQUENCE MAIN_CHAT_SEQ;
DROP SEQUENCE MAIN_LIVE_CHAT_SEQ;
DROP SEQUENCE MAIN_COUNSELING_RES_SEQ;
DROP SEQUENCE MAIN_ANNOUNCEMENT_SEQ;
DROP SEQUENCE MAIN_QUOTE_SEQ;
DROP SEQUENCE MAIN_HABIT_SEQ;
DROP SEQUENCE MAIN_HABIT_TRACKING_SEQ;

DROP SEQUENCE TEST_DIARY_SEQ;
DROP SEQUENCE TEST_EMOTION_SEQ;
DROP SEQUENCE TEST_CHAT_SEQ;
DROP SEQUENCE TEST_LIVE_CHAT_SEQ;
DROP SEQUENCE TEST_COUNSELING_RES_SEQ;
DROP SEQUENCE TEST_ANNOUNCEMENT_SEQ;
DROP SEQUENCE TEST_QUOTE_SEQ;
DROP SEQUENCE TEST_HABIT_SEQ;
DROP SEQUENCE TEST_HABIT_TRACKING_SEQ;

-- Drop Tables (Main)
DROP TABLE MAIN_COUNSELING_CONTENT CASCADE CONSTRAINTS;
DROP TABLE MAIN_COUNSELING_RESERVATION CASCADE CONSTRAINTS;
DROP TABLE MAIN_LIVE_CHAT CASCADE CONSTRAINTS;
DROP TABLE MAIN_CHAT CASCADE CONSTRAINTS;
DROP TABLE MAIN_EMOTION CASCADE CONSTRAINTS;
DROP TABLE MAIN_DIARY CASCADE CONSTRAINTS;
DROP TABLE MAIN_ANNOUNCEMENT CASCADE CONSTRAINTS;
DROP TABLE MAIN_QUOTE CASCADE CONSTRAINTS;
DROP TABLE MAIN_HABIT_TRACKING CASCADE CONSTRAINTS;
DROP TABLE MAIN_HABIT CASCADE CONSTRAINTS;
DROP TABLE MAIN_USER CASCADE CONSTRAINTS;

-- Drop Tables (Test)
DROP TABLE TEST_COUNSELING_CONTENT CASCADE CONSTRAINTS;
DROP TABLE TEST_COUNSELING_RESERVATION CASCADE CONSTRAINTS;
DROP TABLE TEST_LIVE_CHAT CASCADE CONSTRAINTS;
DROP TABLE TEST_CHAT CASCADE CONSTRAINTS;
DROP TABLE TEST_EMOTION CASCADE CONSTRAINTS;
DROP TABLE TEST_DIARY CASCADE CONSTRAINTS;
DROP TABLE TEST_ANNOUNCEMENT CASCADE CONSTRAINTS;
DROP TABLE TEST_QUOTE CASCADE CONSTRAINTS;
DROP TABLE TEST_HABIT_TRACKING CASCADE CONSTRAINTS;
DROP TABLE TEST_HABIT CASCADE CONSTRAINTS;
DROP TABLE TEST_USER CASCADE CONSTRAINTS;


-- ==========================================
-- ========== MAIN TABLES ==========
-- ==========================================

-- 사용자 테이블 (MAIN_USER)
CREATE TABLE MAIN_USER (
                           user_id VARCHAR2(50) PRIMARY KEY,                              -- 사용자 고유 ID
                           user_type NUMBER(1) NOT NULL CHECK (user_type IN (1, 2, 3)),   -- 사용자 유형 (1=상담사 / 2=유저 / 3=관리자)
                           user_name VARCHAR2(100) NOT NULL,                              -- 사용자 이름
                           user_nickname VARCHAR2(100) NOT NULL UNIQUE,                   -- 사용자 닉네임 (중복 불가)
                           user_email VARCHAR2(100) UNIQUE NOT NULL,                      -- 이메일 주소 (중복 불가)
                           user_password VARCHAR2(255) NOT NULL,                          -- 사용자 비밀번호 (암호화 저장 권장)
                           user_img VARCHAR2(255),                                        -- 프로필 이미지 경로 (선택 사항)
                           created_at TIMESTAMP DEFAULT SYSDATE NOT NULL                  -- 계정 생성일시 (기본값: 현재 시간)
);

-- 일기 테이블 (MAIN_DIARY)
CREATE TABLE MAIN_DIARY (
                            diary_id NUMBER PRIMARY KEY,                                   -- 일기 고유 ID
                            user_id VARCHAR2(50) NOT NULL,                                 -- 작성자 사용자 ID
                            title VARCHAR2(200) NOT NULL,                                  -- 일기 제목
                            diary_content CLOB NOT NULL,                                   -- 일기 본문 내용
                            created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,                 -- 일기 작성일시 (기본값: 현재 시간)
                            CONSTRAINT fk_diary_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id) -- 사용자와 연결되는 외래키
);

-- 감정 기록 테이블 (MAIN_EMOTION)
CREATE TABLE MAIN_EMOTION (
                              emotion_id NUMBER PRIMARY KEY,                                 -- 감정 기록 고유 ID
                              user_id VARCHAR2(50) NOT NULL,                                 -- 감정 작성 사용자 ID
                              diary_id NUMBER UNIQUE NOT NULL,                               -- 연결된 일기 ID (1:1 관계, 중복 불가)
                              emotion_score NUMBER NOT NULL,                                 -- 감정 점수 (예: 0~100)
                              emotion_emoji VARCHAR2(200) NOT NULL,                          -- 감정을 나타내는 이모지
                              recorded_at TIMESTAMP DEFAULT SYSDATE NOT NULL,                -- 감정 기록일시 (기본값: 현재 시간)
                              CONSTRAINT fk_emotion_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id), -- 사용자 외래키
                              CONSTRAINT fk_emotion_diary FOREIGN KEY (diary_id) REFERENCES MAIN_DIARY(diary_id) -- 일기 외래키
);

-- 챗봇 대화 테이블 (MAIN_CHAT)
CREATE TABLE MAIN_CHAT (
                           chat_id NUMBER PRIMARY KEY,                                    -- 챗봇 대화 고유 ID
                           user_id VARCHAR2(50) NOT NULL,                                 -- 대화한 사용자 ID
                           chat_summary CLOB NOT NULL,                                    -- 대화 요약 내용 (전체 내용 저장 가능)
                           CONSTRAINT fk_chat_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id) -- 사용자 외래키
);

-- 실시간 채팅 테이블 (MAIN_LIVE_CHAT)
CREATE TABLE MAIN_LIVE_CHAT (
                                session_id NUMBER PRIMARY KEY,                                 -- 실시간 채팅 세션 고유 ID
                                user_id VARCHAR2(50) NOT NULL,                                 -- 채팅 요청한 사용자 ID
                                counselor_id VARCHAR2(50),                                     -- 응답한 상담사 사용자 ID
                                start_time TIMESTAMP NOT NULL,                                 -- 채팅 시작 시간
                                end_time TIMESTAMP,                                            -- 채팅 종료 시간 (종료 시점 기록)
                                status VARCHAR2(50) NOT NULL,                                  -- 채팅 상태 (예: 진행중, 종료 등)
                                CONSTRAINT fk_live_chat_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id), -- 사용자 외래키
                                CONSTRAINT fk_live_chat_counselor FOREIGN KEY (counselor_id) REFERENCES MAIN_USER(user_id) -- 상담사 외래키
);

-- 상담 예약 테이블 (MAIN_COUNSELING_RESERVATION)
CREATE TABLE MAIN_COUNSELING_RESERVATION (
                                             counseling_id NUMBER PRIMARY KEY,                              -- 상담 예약 고유 ID
                                             user_id VARCHAR2(50) NOT NULL,                                 -- 상담을 신청한 사용자 ID
                                             counselor_id VARCHAR2(50) NOT NULL,                            -- 예약된 상담사 사용자 ID
                                             counseling_date DATE NOT NULL,                                 -- 상담 날짜
                                             counseling_time NUMBER(2) CHECK (counseling_time BETWEEN 10 AND 22), -- 상담 시간대 (10시~22시)
                                             category VARCHAR2(20) CHECK (category IN ('건강','미래','인간관계','기타고민')), -- 상담 카테고리
                                             status VARCHAR2(20) DEFAULT '대기' CHECK (status IN ('대기','진행중','완료','취소')), -- 상담 진행 상태
                                             created_at DATE DEFAULT SYSDATE NOT NULL,                      -- 예약 생성일시
                                             CONSTRAINT fk_cr_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id), -- 사용자 외래키
                                             CONSTRAINT fk_cr_counselor FOREIGN KEY (counselor_id) REFERENCES MAIN_USER(user_id) -- 상담사 외래키
);

-- 상담 내용 기록 테이블 (MAIN_COUNSELING_CONTENT)
CREATE TABLE MAIN_COUNSELING_CONTENT (
                                         counseling_id NUMBER PRIMARY KEY,                              -- 상담 예약 ID (기본키 및 외래키)
                                         counseling_content CLOB,                                       -- 상담 내용 전체 기록
                                         CONSTRAINT fk_cc_reservation FOREIGN KEY (counseling_id) REFERENCES MAIN_COUNSELING_RESERVATION(counseling_id) -- 상담 예약 외래키
);

-- 공지사항 테이블 (MAIN_ANNOUNCEMENT)
CREATE TABLE MAIN_ANNOUNCEMENT (
                                   announcement_id NUMBER PRIMARY KEY,                            -- 공지사항 고유 ID
                                   admin_id VARCHAR2(50) NOT NULL,                                -- 작성한 관리자 ID
                                   title VARCHAR2(255) NOT NULL,                                  -- 공지사항 제목
                                   content CLOB NOT NULL,                                         -- 공지사항 본문
                                   created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,                 -- 공지사항 작성일시
                                   CONSTRAINT fk_announcement_admin FOREIGN KEY (admin_id) REFERENCES MAIN_USER(user_id) -- 관리자 외래키
);

-- 명언 테이블 (MAIN_QUOTE)
CREATE TABLE MAIN_QUOTE (
                            quote_id NUMBER PRIMARY KEY,                                   -- 명언 고유 ID
                            admin_id VARCHAR2(50) NOT NULL,                                -- 등록한 관리자 ID
                            content CLOB NOT NULL,                                         -- 명언 내용
                            created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,                 -- 명언 등록일시
                            CONSTRAINT fk_quote_admin FOREIGN KEY (admin_id) REFERENCES MAIN_USER(user_id) -- 관리자 외래키
);

-- 습관 관리 테이블 (MAIN_HABIT)
CREATE TABLE MAIN_HABIT (
                            habit_id NUMBER PRIMARY KEY,                                   -- 습관 고유 ID
                            user_id VARCHAR2(50) NOT NULL,                                 -- 습관을 등록한 사용자 ID
                            habit_name VARCHAR2(100) NOT NULL,                             -- 습관 이름 (예: 아침 운동)
                            created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,                 -- 습관 생성일시
                            CONSTRAINT fk_habit_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id) -- 사용자 외래키
);

-- 습관 추적 기록 테이블 (MAIN_HABIT_TRACKING)
CREATE TABLE MAIN_HABIT_TRACKING (
                                     tracking_id NUMBER PRIMARY KEY,                                -- 습관 추적 고유 ID
                                     habit_id NUMBER NOT NULL,                                      -- 추적 대상 습관 ID
                                     completion_rate NUMBER NOT NULL,                               -- 완료율 (예: 100%)
                                     status VARCHAR2(5) NOT NULL CHECK (status IN ('true', 'false')), -- 완료 여부 (true/false)
                                     daily_feedback VARCHAR2(200),                                  -- 일일 피드백 (간단한 메모)
                                     weekly_feedback CLOB,                                          -- 주간 피드백 (긴 내용 가능)
                                     created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,                 -- 기록일시
                                     CONSTRAINT fk_tracking_habit FOREIGN KEY (habit_id) REFERENCES MAIN_HABIT(habit_id) -- 습관 외래키
);

-- ==========================================
-- ========== TEST TABLES ==========
-- ==========================================

CREATE TABLE TEST_USER AS SELECT * FROM MAIN_USER WHERE 1=2;
CREATE TABLE TEST_DIARY AS SELECT * FROM MAIN_DIARY WHERE 1=2;
CREATE TABLE TEST_EMOTION AS SELECT * FROM MAIN_EMOTION WHERE 1=2;
CREATE TABLE TEST_CHAT AS SELECT * FROM MAIN_CHAT WHERE 1=2;
CREATE TABLE TEST_LIVE_CHAT AS SELECT * FROM MAIN_LIVE_CHAT WHERE 1=2;
CREATE TABLE TEST_COUNSELING_RESERVATION AS SELECT * FROM MAIN_COUNSELING_RESERVATION WHERE 1=2;
CREATE TABLE TEST_COUNSELING_CONTENT AS SELECT * FROM MAIN_COUNSELING_CONTENT WHERE 1=2;
CREATE TABLE TEST_ANNOUNCEMENT AS SELECT * FROM MAIN_ANNOUNCEMENT WHERE 1=2;
CREATE TABLE TEST_QUOTE AS SELECT * FROM MAIN_QUOTE WHERE 1=2;
CREATE TABLE TEST_HABIT AS SELECT * FROM MAIN_HABIT WHERE 1=2;
CREATE TABLE TEST_HABIT_TRACKING AS SELECT * FROM MAIN_HABIT_TRACKING WHERE 1=2;


-- ==========================================
-- ========== MAIN SEQUENCE & TRIGGER ==========
-- ==========================================

CREATE SEQUENCE MAIN_DIARY_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_DIARY_TRG
    BEFORE INSERT ON MAIN_DIARY
    FOR EACH ROW
BEGIN
    SELECT MAIN_DIARY_SEQ.NEXTVAL INTO :NEW.diary_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_EMOTION_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_EMOTION_TRG
    BEFORE INSERT ON MAIN_EMOTION
    FOR EACH ROW
BEGIN
    SELECT MAIN_EMOTION_SEQ.NEXTVAL INTO :NEW.emotion_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_CHAT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_CHAT_TRG
    BEFORE INSERT ON MAIN_CHAT
    FOR EACH ROW
BEGIN
    SELECT MAIN_CHAT_SEQ.NEXTVAL INTO :NEW.chat_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_LIVE_CHAT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_LIVE_CHAT_TRG
    BEFORE INSERT ON MAIN_LIVE_CHAT
    FOR EACH ROW
BEGIN
    SELECT MAIN_LIVE_CHAT_SEQ.NEXTVAL INTO :NEW.session_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_COUNSELING_RES_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_COUNSELING_RES_TRG
    BEFORE INSERT ON MAIN_COUNSELING_RESERVATION
    FOR EACH ROW
BEGIN
    SELECT MAIN_COUNSELING_RES_SEQ.NEXTVAL INTO :NEW.counseling_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_ANNOUNCEMENT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_ANNOUNCEMENT_TRG
    BEFORE INSERT ON MAIN_ANNOUNCEMENT
    FOR EACH ROW
BEGIN
    SELECT MAIN_ANNOUNCEMENT_SEQ.NEXTVAL INTO :NEW.announcement_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_QUOTE_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_QUOTE_TRG
    BEFORE INSERT ON MAIN_QUOTE
    FOR EACH ROW
BEGIN
    SELECT MAIN_QUOTE_SEQ.NEXTVAL INTO :NEW.quote_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_HABIT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_HABIT_TRG
    BEFORE INSERT ON MAIN_HABIT
    FOR EACH ROW
BEGIN
    SELECT MAIN_HABIT_SEQ.NEXTVAL INTO :NEW.habit_id FROM DUAL;
END;
/

CREATE SEQUENCE MAIN_HABIT_TRACKING_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER MAIN_HABIT_TRACKING_TRG
    BEFORE INSERT ON MAIN_HABIT_TRACKING
    FOR EACH ROW
BEGIN
    SELECT MAIN_HABIT_TRACKING_SEQ.NEXTVAL INTO :NEW.tracking_id FROM DUAL;
END;
/

-- 기존 SEQUENCE 및 TRIGGER는 이전과 동일하게 유지 --
-- (생략된 부분은 기존 내용 참고)

-- ==========================================
-- ========== TEST SEQUENCE & TRIGGER ==========
-- ==========================================

CREATE SEQUENCE TEST_DIARY_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_DIARY_TRG
    BEFORE INSERT ON TEST_DIARY
    FOR EACH ROW
BEGIN
    SELECT TEST_DIARY_SEQ.NEXTVAL INTO :NEW.diary_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_EMOTION_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_EMOTION_TRG
    BEFORE INSERT ON TEST_EMOTION
    FOR EACH ROW
BEGIN
    SELECT TEST_EMOTION_SEQ.NEXTVAL INTO :NEW.emotion_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_CHAT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_CHAT_TRG
    BEFORE INSERT ON TEST_CHAT
    FOR EACH ROW
BEGIN
    SELECT TEST_CHAT_SEQ.NEXTVAL INTO :NEW.chat_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_LIVE_CHAT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_LIVE_CHAT_TRG
    BEFORE INSERT ON TEST_LIVE_CHAT
    FOR EACH ROW
BEGIN
    SELECT TEST_LIVE_CHAT_SEQ.NEXTVAL INTO :NEW.session_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_COUNSELING_RES_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_COUNSELING_RES_TRG
    BEFORE INSERT ON TEST_COUNSELING_RESERVATION
    FOR EACH ROW
BEGIN
    SELECT TEST_COUNSELING_RES_SEQ.NEXTVAL INTO :NEW.counseling_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_ANNOUNCEMENT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_ANNOUNCEMENT_TRG
    BEFORE INSERT ON TEST_ANNOUNCEMENT
    FOR EACH ROW
BEGIN
    SELECT TEST_ANNOUNCEMENT_SEQ.NEXTVAL INTO :NEW.announcement_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_QUOTE_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_QUOTE_TRG
    BEFORE INSERT ON TEST_QUOTE
    FOR EACH ROW
BEGIN
    SELECT TEST_QUOTE_SEQ.NEXTVAL INTO :NEW.quote_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_HABIT_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_HABIT_TRG
    BEFORE INSERT ON TEST_HABIT
    FOR EACH ROW
BEGIN
    SELECT TEST_HABIT_SEQ.NEXTVAL INTO :NEW.habit_id FROM DUAL;
END;
/

CREATE SEQUENCE TEST_HABIT_TRACKING_SEQ START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TEST_HABIT_TRACKING_TRG
    BEFORE INSERT ON TEST_HABIT_TRACKING
    FOR EACH ROW
BEGIN
    SELECT TEST_HABIT_TRACKING_SEQ.NEXTVAL INTO :NEW.tracking_id FROM DUAL;
END;
/

-- 기존 SEQUENCE 및 TRIGGER는 이전과 동일하게 유지 --
-- (생략된 부분은 기존 내용 참고)

-- ==========================================
-- ========== SELECT TEST ==========
-- ==========================================

SELECT * FROM MAIN_USER;
SELECT * FROM MAIN_DIARY;
SELECT * FROM MAIN_EMOTION;
SELECT * FROM MAIN_CHAT;
SELECT * FROM MAIN_LIVE_CHAT;
SELECT * FROM MAIN_COUNSELING_RESERVATION;
SELECT * FROM MAIN_COUNSELING_CONTENT;
SELECT * FROM MAIN_ANNOUNCEMENT;
SELECT * FROM MAIN_QUOTE;
SELECT * FROM MAIN_HABIT;
SELECT * FROM MAIN_HABIT_TRACKING;

SELECT * FROM TEST_USER;
SELECT * FROM TEST_DIARY;
SELECT * FROM TEST_EMOTION;
SELECT * FROM TEST_CHAT;
SELECT * FROM TEST_LIVE_CHAT;
SELECT * FROM TEST_COUNSELING_RESERVATION;
SELECT * FROM TEST_COUNSELING_CONTENT;
SELECT * FROM TEST_ANNOUNCEMENT;
SELECT * FROM TEST_QUOTE;
SELECT * FROM TEST_HABIT;
SELECT * FROM TEST_HABIT_TRACKING;


-- ==========================================
-- ========== ADD COLUMN ==========
-- ==========================================
ALTER TABLE MAIN_USER
    ADD user_nickname VARCHAR2(10);
ALTER TABLE MAIN_USER
    ADD CONSTRAINT uk_user_nickname UNIQUE (user_nickname);

ALTER TABLE MAIN_DIARY
    ADD title VARCHAR2(200) NOT NULL;

ALTER TABLE TEST_DIARY
    ADD title VARCHAR2(200) NOT NULL;

