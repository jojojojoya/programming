package com.koyoi.main.mapper;

import com.koyoi.main.vo.LiveChatVO;
import com.koyoi.main.vo.UserMyPageVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface LiveChatMapper {



    // 나가기 누르면 특정 상담 완료 처리로 상태 변경
    @Update("""
        UPDATE TEST_COUNSELING_RESERVATION 
        SET status = '完了'
        WHERE counseling_id = #{counseling_id}
    """)
    int completeCounseling(@Param("counseling_id") Integer counselingId);

    //대기가 아니면 상태를 변경
    @Update("""
    UPDATE TEST_COUNSELING_RESERVATION 
    SET status = #{status}
    WHERE counseling_id = #{counseling_id}
""")
    int updateReservationStatus(@Param("counseling_id") int counselingId, @Param("status") String status);


    @Update("""
    UPDATE TEST_COUNSELING_RESERVATION
    SET status = '待機中'
    WHERE status != '完了'
    AND counseling_date = CURRENT_DATE
    AND counseling_time BETWEEN EXTRACT(HOUR FROM CURRENT_TIMESTAMP) AND EXTRACT(HOUR FROM CURRENT_TIMESTAMP) + 1
""")
    int updateToWaitingStatus();

    @Insert("""
    INSERT INTO TEST_COUNSELING_RESERVATION 
    (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at) 
    VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, #{user_id}, #{counselor_id}, #{counseling_date}, #{counseling_time}, #{category}, '待機中', SYSDATE)
""")
    @SelectKey(statement = "SELECT TEST_COUNSELING_RES_SEQ.CURRVAL FROM dual",
            keyProperty = "counseling_id",
            before = false,
            resultType = Integer.class)
    int reserveCounseling(LiveChatVO reservation);


    // 예약된 상담 조회
    @Select("""
        SELECT * 
        FROM TEST_COUNSELING_RESERVATION 
        WHERE status = '待機中' 
        AND counseling_date = CURRENT_DATE 
        AND counseling_time BETWEEN EXTRACT(HOUR FROM CURRENT_TIMESTAMP) - 1 AND EXTRACT(HOUR FROM CURRENT_TIMESTAMP) + 1
        ORDER BY counseling_date ASC, counseling_time ASC
    """)
    List<LiveChatVO> findAvailableReservations();

    //  완료된 상담 조회
    @Select("""
        SELECT * 
        FROM TEST_COUNSELING_RESERVATION 
        WHERE status = '完了' 
        ORDER BY counseling_date DESC, counseling_time DESC
    """)
    List<LiveChatVO> findCompletedReservations();

    // 특정 상담 ID로 상담 내역 조회
    @Select("""
                    SELECT cr.*, lc.session_id FROM TEST_COUNSELING_RESERVATION cr, TEST_LIVE_CHAT lc
                                                          WHERE cr.COUNSELING_ID = lc.counseling_id and cr.COUNSELING_ID = #{counseling_id}
                """)
    LiveChatVO findReservationById(@Param("counseling_id") int counselingId);

    // 특정 상담의 상태를 '대기'로 변경
    @Update("""
        UPDATE TEST_COUNSELING_RESERVATION
        SET status = '待機中'
        WHERE counseling_id = #{counseling_id}
    """)
    int updateSingleReservationToWaiting(@Param("counseling_id") int counselingId);

    //  상담 ID로 기존 채팅 내역 가져오기 (세션 기반)
    @Select("""
        SELECT sender, message, timestamp, user_type 
        FROM TEST_LIVE_CHAT_LOG 
        WHERE session_id = #{session_id} 
        ORDER BY timestamp ASC
    """)
    List<LiveChatVO> getChatLogs(@Param("session_id") int sessionId);

    // 상담사 조회 (랜덤)
    @Select("""
    SELECT user_id 
    FROM TEST_USER
    where user_type = '2'
    ORDER BY DBMS_RANDOM.VALUE 
    FETCH FIRST 1 ROWS ONLY
""")
    String findRandomCounselor();

    @Insert("""
    INSERT INTO TEST_LIVE_CHAT_LOG (log_id, session_id, sender, user_type, message, timestamp) 
    VALUES (TEST_LIVE_CHAT_LOG_SEQ.NEXTVAL, #{session_id}, #{sender}, #{user_type}, 
        COALESCE(#{message}, '내용 없음'), CURRENT_TIMESTAMP)
""")
    int insertChatMessage(LiveChatVO message);


@Insert("""
    INSERT INTO TEST_live_chat (session_id, user_id, counseling_id, counselor_id, start_time, end_time, status)
    VALUES (#{session_id}, #{user_id}, #{counseling_id}, #{counselor_id}, #{start_time}, 0, '待機中')
""")
@SelectKey(
        statement = "SELECT TEST_live_chat_seq.NEXTVAL FROM dual",
        keyProperty = "session_id",
        before = true,
        resultType = Integer.class
)
Integer createChatRoom(LiveChatVO reservation);


    @Select("""
    SELECT counseling_id FROM TEST_LIVE_CHAT WHERE session_id = #{session_id}
""")
    Integer findCounselingIdBySession(@Param("session_id") int sessionId);


    // 상담 종료 시 상태 변경
    @Update("""
        UPDATE TEST_live_chat 
        SET status = '完了' 
        WHERE session_id = #{sessionId}
    """)
    int completeChat(@Param("sessionId") int sessionId);

}


