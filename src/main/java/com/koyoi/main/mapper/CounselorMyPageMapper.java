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


    // 유저 관련 DB를 리스팅
    @Select("SELECT * FROM test_user WHERE user_id = #{user_id}")
    List<CounselorMyPageVO> getUserById(@Param("user_id") String user_id);

    // 패스워드를 확인하기 위해 불러오기 (대조)
    @Select("SELECT user_password FROM test_user WHERE user_id = #{user_id}")
    String getPasswordByUserId(@Param("user_id") String user_id);

    // 프로필 업데이트
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


    @Select("""
    SELECT cr.*, lc.session_id FROM test_counseling_reservation cr, test_live_chat lc 
    WHERE cr.COUNSELING_ID = lc.COUNSELING_ID and cr.user_id =  #{user_id}  
    ORDER BY counseling_date DESC, counseling_time DESC
""")
    List<CounselorMyPageVO> getUserReservations(@Param("user_id") String user_id);

    @Update("""
    UPDATE test_counseling_reservation 
    SET status = #{status} 
    WHERE counseling_id = #{counseling_id}
""")
    int updateCounselingStatus(@Param("counseling_id") int counselingId, @Param("status") String status);

    @Select("SELECT * FROM test_counseling_reservation")
    List<CounselorMyPageVO> getAllReservations();


    @Select("""
    SELECT COUNT(*) FROM test_user
    WHERE user_nickname = #{nickname}
      AND user_id != #{user_id}
""")
    int countByNicknameExcludeCurrentUser(@Param("nickname") String nickname, @Param("user_id") String userId);

}