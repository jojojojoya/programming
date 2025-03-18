package com.koyoi.main.mapper;

import com.koyoi.main.vo.UserMyPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Mapper
public interface UserMyPageMapper {

    // 유저 관련 DB를 리스팅
    @Select("SELECT * FROM test_user WHERE user_id = #{user_id}")
    List<UserMyPageVO> getUserById(@Param("user_id") String user_id);

    // 패스워드를 확인하기 위해 불러오기 (대조)
    @Select("SELECT user_password FROM test_user WHERE user_id = #{user_id}")
    String getPasswordByUserId(@Param("user_id") String user_id);

    // 프로필 업데이트
    @Update("UPDATE test_user SET user_password = #{user_password}, user_nickname = #{user_nickname} WHERE user_id = #{user_id}")
    int updateProfile(UserMyPageVO user);

    // 챗봇 대화 요약내역 조회
    @Select("SELECT chat_summary FROM test_chat WHERE user_id = #{user_id}")
    List<UserMyPageVO> getUserChatBotDetail(@Param("user_id") String user_id);


    @Select("""
    SELECT * FROM test_counseling_reservation 
    WHERE user_id = #{user_id} 
    ORDER BY counseling_date DESC, counseling_time DESC
""")
    List<UserMyPageVO> getUserReservations(@Param("user_id") String user_id);

    @Update("""
    UPDATE test_counseling_reservation 
    SET status = #{status} 
    WHERE counseling_id = #{counseling_id}
""")
    int updateCounselingStatus(@Param("counseling_id") int counselingId, @Param("status") String status);

}