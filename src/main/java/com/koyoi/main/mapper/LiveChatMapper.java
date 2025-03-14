package com.koyoi.main.mapper;

import com.koyoi.main.vo.LiveChatVO;
import com.koyoi.main.vo.UserMyPageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LiveChatMapper {
    // 상담 예약건 저장
    @Insert("insert into test_live_chat (session_id, user_id, counselor_id, start_time, status) " +
            "values (test_live_chat_seq.nextval, #{user_id}, #{counselor_id}, #{start_time}, '예약완료') ")
    int reserveLiveChat(LiveChatVO livechatreserve);

    // 대기 상담건
    @Insert("INSERT INTO TEST_COUNSELING_RESERVATION (counseling_id, user_id, counselor_id, counseling_date, counseling_time, category, status, created_at) " +
            "VALUES (TEST_COUNSELING_RES_SEQ.NEXTVAL, #{user_id}, #{counselor_id}, #{counseling_date}, #{counseling_time}, #{category}, '대기', SYSDATE)")
    int reserveCounseling(LiveChatVO reservation);

//    @Select("SELECT * FROM TEST_LIVE_CHAT WHERE user_id = #{user_id}")
//    List<LiveChatVO> getUserLiveChats(@Param("user_id") String userId);

    // 유저 상담 예약 내역 가져오기
    @Select("select * from test_counseling_reservation where user_id = #{user_id} order by counseling_date desc")
    List<LiveChatVO> getUserLiveChats(@Param("user_id") String userId);


    @Select("""
    SELECT * FROM TEST_COUNSELING_RESERVATION 
    WHERE status = '대기'
    AND counseling_date = CURRENT_DATE
    AND counseling_time BETWEEN EXTRACT(HOUR FROM CURRENT_TIMESTAMP) - 1 
                             AND EXTRACT(HOUR FROM CURRENT_TIMESTAMP)
""")
    List<LiveChatVO> findAvailableReservations();

    @Select("""
    SELECT * FROM TEST_COUNSELING_RESERVATION 
    WHERE counseling_date < CURRENT_DATE
    OR (counseling_date = CURRENT_DATE AND counseling_time < EXTRACT(HOUR FROM CURRENT_TIMESTAMP))
""")
    List<LiveChatVO> findCompletedReservations();

    @Update("""
    UPDATE TEST_COUNSELING_RESERVATION
    SET status = '완료'
    WHERE (counseling_date < CURRENT_DATE)
    OR (counseling_date = CURRENT_DATE AND counseling_time < EXTRACT(HOUR FROM CURRENT_TIMESTAMP))
    AND status != '완료'
""")
    int updateCompletedReservations();
}
