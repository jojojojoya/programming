package com.koyoi.main.mapper;

import com.koyoi.main.vo.CounselorMyPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CounselorMyPageMapper {

    // 상담사가 받은 예약 내역 조회
    @Select("""
        SELECT cr.*, lc.session_id 
        FROM test_counseling_reservation cr, test_live_chat lc 
        WHERE cr.COUNSELING_ID = lc.COUNSELING_ID 
          AND cr.counselor_id = #{counselor_id} 
        ORDER BY counseling_date DESC, counseling_time DESC
    """)
    List<CounselorMyPageVO> getReservationsByCounselorId(@Param("counselor_id") String counselor_id);

    // 상담사 정보 조회 (user_type = 2)
    @Select("SELECT * FROM test_user WHERE user_id = #{user_id} AND user_type = 2")
    List<CounselorMyPageVO> getCounselorById(@Param("user_id") String user_id);

    // 상담사 프로필 업데이트
    @Update("""
        UPDATE test_user 
        SET 
            user_password = COALESCE(NULLIF(#{user_password}, ''), user_password),
            user_nickname = #{user_nickname},
            user_img = COALESCE(#{user_img}, user_img)
        WHERE user_id = #{user_id}
    """)
    int updateProfile(CounselorMyPageVO user);

    // 챗봇 대화 요약내역 조회함
    @Select("SELECT chat_summary FROM test_chat WHERE user_id = #{user_id}")
    List<CounselorMyPageVO> getUserChatBotDetail(@Param("user_id") String user_id);

    // 상담사 비밀번호 조회
    @Select("SELECT user_password FROM test_user WHERE user_id = #{user_id}")
    String getPasswordByUserId(@Param("user_id") String user_id);
}
