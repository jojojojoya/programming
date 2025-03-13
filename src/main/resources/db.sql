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

CREATE TABLE MAIN_USER (
                           user_id VARCHAR2(50) PRIMARY KEY,
                           user_type NUMBER(1) NOT NULL CHECK (user_type IN (1, 2, 3)),
                           user_name VARCHAR2(100) NOT NULL,
                           user_email VARCHAR2(100) UNIQUE NOT NULL,
                           user_password VARCHAR2(255) NOT NULL,
                           user_img VARCHAR2(255),
                           created_at TIMESTAMP DEFAULT SYSDATE NOT NULL
);

CREATE TABLE MAIN_DIARY (
                            diary_id NUMBER PRIMARY KEY,
                            user_id VARCHAR2(50) NOT NULL,
                            diary_content CLOB NOT NULL,
                            created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,
                            CONSTRAINT fk_diary_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id)
);

CREATE TABLE MAIN_EMOTION (
                              emotion_id NUMBER PRIMARY KEY,
                              user_id VARCHAR2(50) NOT NULL,
                              diary_id NUMBER UNIQUE NOT NULL,
                              emotion_score NUMBER NOT NULL,
                              emotion_emoji VARCHAR2(200) NOT NULL,
                              recorded_at TIMESTAMP DEFAULT SYSDATE NOT NULL,
                              CONSTRAINT fk_emotion_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id),
                              CONSTRAINT fk_emotion_diary FOREIGN KEY (diary_id) REFERENCES MAIN_DIARY(diary_id)
);

CREATE TABLE MAIN_CHAT (
                           chat_id NUMBER PRIMARY KEY,
                           user_id VARCHAR2(50) NOT NULL,
                           chat_summary CLOB NOT NULL,
                           CONSTRAINT fk_chat_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id)
);

CREATE TABLE MAIN_LIVE_CHAT (
                                session_id NUMBER PRIMARY KEY,
                                user_id VARCHAR2(50) NOT NULL,
                                counselor_id VARCHAR2(50),
                                start_time TIMESTAMP NOT NULL,
                                end_time TIMESTAMP,
                                status VARCHAR2(50) NOT NULL,
                                CONSTRAINT fk_live_chat_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id),
                                CONSTRAINT fk_live_chat_counselor FOREIGN KEY (counselor_id) REFERENCES MAIN_USER(user_id)
);

CREATE TABLE MAIN_COUNSELING_RESERVATION (
                                             counseling_id NUMBER PRIMARY KEY,
                                             user_id VARCHAR2(50) NOT NULL,
                                             counselor_id VARCHAR2(50) NOT NULL,
                                             counseling_date DATE NOT NULL,
                                             counseling_time NUMBER(2) CHECK (counseling_time BETWEEN 10 AND 22),
                                             category VARCHAR2(20) CHECK (category IN ('건강','미래','인간관계','기타고민')),
                                             status VARCHAR2(20) DEFAULT '대기' CHECK (status IN ('대기','진행중','완료','취소')),
                                             created_at DATE DEFAULT SYSDATE NOT NULL,
                                             CONSTRAINT fk_cr_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id),
                                             CONSTRAINT fk_cr_counselor FOREIGN KEY (counselor_id) REFERENCES MAIN_USER(user_id)
);

CREATE TABLE MAIN_COUNSELING_CONTENT (
                                         counseling_id NUMBER PRIMARY KEY,
                                         counseling_content CLOB,
                                         CONSTRAINT fk_cc_reservation FOREIGN KEY (counseling_id) REFERENCES MAIN_COUNSELING_RESERVATION(counseling_id)
);

CREATE TABLE MAIN_ANNOUNCEMENT (
                                   announcement_id NUMBER PRIMARY KEY,
                                   admin_id VARCHAR2(50) NOT NULL,
                                   title VARCHAR2(255) NOT NULL,
                                   content CLOB NOT NULL,
                                   created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,
                                   CONSTRAINT fk_announcement_admin FOREIGN KEY (admin_id) REFERENCES MAIN_USER(user_id)
);

CREATE TABLE MAIN_QUOTE (
                            quote_id NUMBER PRIMARY KEY,
                            admin_id VARCHAR2(50) NOT NULL,
                            content CLOB NOT NULL,
                            created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,
                            CONSTRAINT fk_quote_admin FOREIGN KEY (admin_id) REFERENCES MAIN_USER(user_id)
);

CREATE TABLE MAIN_HABIT (
                            habit_id NUMBER PRIMARY KEY,
                            user_id VARCHAR2(50) NOT NULL,
                            habit_name VARCHAR2(100) NOT NULL,
                            created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,
                            CONSTRAINT fk_habit_user FOREIGN KEY (user_id) REFERENCES MAIN_USER(user_id)
);

CREATE TABLE MAIN_HABIT_TRACKING (
                                     tracking_id NUMBER PRIMARY KEY,
                                     habit_id NUMBER NOT NULL,
                                     completion_rate NUMBER NOT NULL,
                                     status VARCHAR2(5) NOT NULL CHECK (status IN ('true', 'false')),
                                     daily_feedback VARCHAR2(200),
                                     weekly_feedback CLOB,
                                     created_at TIMESTAMP DEFAULT SYSDATE NOT NULL,
                                     CONSTRAINT fk_tracking_habit FOREIGN KEY (habit_id) REFERENCES MAIN_HABIT(habit_id)
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
