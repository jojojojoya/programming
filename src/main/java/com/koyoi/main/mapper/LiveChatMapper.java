package com.koyoi.main.mapper;

import com.koyoi.main.vo.LiveChatVO;
import com.koyoi.main.vo.UserMyPageVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface LiveChatMapper {



        // 나가기 누르면 특정 상담 완료 처리로 상태 변경하고
        @Update("""
        UPDATE TEST_COUNSELING_RESERVATION 
        SET status = '완료'
        WHERE counseling_id = #{counseling_id}
    """)
        int completeCounseling(@Param("counseling_id") Integer counselingId);

    //대기가 아니면 상태변경
    @Update("""
    UPDATE TEST_COUNSELING_RESERVATION
    SET status = #{status}
    WHERE counseling_id = #{counseling_id}
    AND status != '대기'  -- 🔥 이미 대기인 경우 변경 안 함
""")
    int updateReservationStatus(@Param("counseling_id") int counselingId, @Param("status") String status);

        // ✅ 전체 상담 상태를 '대기'로 변경
        @Update("""
        UPDATE TEST_COUNSELING_RESERVATION
        SET status = '대기'
        WHERE status != '완료'
        AND counseling_date = CURRENT_DATE
        AND counseling_time BETWEEN EXTRACT(HOUR FROM CURRENT_TIMESTAMP) - 1 AND EXTRACT(HOUR FROM CURRENT_TIMESTAMP) + 1
    """)
        int updateToWaitingStatus();

    @Insert("""
    INSERT INTO TEST_COUNSELING_RESERVATION 
    (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at) 
    VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, #{user_id}, #{counselor_id}, #{counseling_date}, #{counseling_time}, #{category}, '대기', SYSDATE)
""")
    @Options(useGeneratedKeys = true, keyProperty = "counseling_id", keyColumn = "counseling_id")
    int reserveCounseling(LiveChatVO reservation);

    // ✅ 예약된 상담 조회
        @Select("""
        SELECT * 
        FROM TEST_COUNSELING_RESERVATION 
        WHERE status = '대기' 
        AND counseling_date = CURRENT_DATE 
        AND counseling_time BETWEEN EXTRACT(HOUR FROM CURRENT_TIMESTAMP) - 1 AND EXTRACT(HOUR FROM CURRENT_TIMESTAMP) + 1
        ORDER BY counseling_date ASC, counseling_time ASC
    """)
        List<LiveChatVO> findAvailableReservations();

        // ✅ 완료된 상담 조회
        @Select("""
        SELECT * 
        FROM TEST_COUNSELING_RESERVATION 
        WHERE status = '완료' 
        ORDER BY counseling_date DESC, counseling_time DESC
    """)
        List<LiveChatVO> findCompletedReservations();

        // ✅ 특정 상담 ID로 상담 내역 조회
        @Select("""
                    SELECT cr.*, lc.session_id FROM TEST_COUNSELING_RESERVATION cr, TEST_LIVE_CHAT lc
                                                          WHERE cr.COUNSELING_ID = lc.counseling_id and cr.COUNSELING_ID = #{counseling_id}
                """)
        LiveChatVO findReservationById(@Param("counseling_id") int counselingId);

        // ✅ 특정 상담의 상태를 '대기'로 변경
        @Update("""
        UPDATE TEST_COUNSELING_RESERVATION
        SET status = '대기'
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

        // 실시간 채팅 메시지 저장
        @Insert("""
        INSERT INTO TEST_LIVE_CHAT_LOG (log_id, session_id, sender, user_type, message, timestamp) 
        VALUES (TEST_LIVE_CHAT_LOG_SEQ.NEXTVAL, #{session_id}, #{sender}, #{type}, #{content}, CURRENT_TIMESTAMP)
    """)
        int insertChatMessage(LiveChatVO message);


    @Insert("""
    INSERT INTO test_live_chat (session_id, user_id,COUNSELING_ID, counselor_id, start_time, end_time, status)
    VALUES (#{session_id}, #{user_id},#{counseling_id}, #{counselor_id}, #{start_time}, 0, '대기')
""")
    @SelectKey(statement = "SELECT test_live_chat_seq.nextval FROM dual",
            keyProperty = "session_id",
            before = true,
            resultType = Integer.class)
    Integer createChatRoom(LiveChatVO reservation);


    @Insert("""
        INSERT INTO TEST_COUNSELING_SUMMARY (summary_id, session_id, counseling_summary, created_at)
        VALUES (TEST_COUNSELING_SUMMARY_SEQ.NEXTVAL, #{session_id}, #{counseling_summary}, SYSDATE)
    """)
    int saveCounselingSummary(@Param("session_id") int sessionId, @Param("counseling_summary") String summary);


    // 상담버튼 나가기 누를시 세션 아이디저장
    @Select("""
    SELECT counseling_id FROM TEST_LIVE_CHAT WHERE session_id = #{session_id}
""")
    Integer findCounselingIdBySession(@Param("session_id") int sessionId);

    @Insert("""
    INSERT INTO TEST_COUNSELING_SUMMARY (summary_id, session_id, counseling_summary, created_at)
    VALUES (TEST_COUNSELING_SUM_SEQ.NEXTVAL, #{session_id}, #{summary}, SYSDATE)
""")
    void insertChatSummary(@Param("session_id") int sessionId, @Param("summary") String summary);

}