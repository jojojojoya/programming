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

    //패스워드를 확인하기 위해 불러오세요 (대조)
    @Select("SELECT user_password FROM test_user WHERE user_id = #{user_id}")
    String getPasswordByUserId(@Param("user_id") String user_id);

//    @Update("UPDATE test_user SET user_password = #{newPassword} WHERE user_id = #{userId}")
//    int updatePassword(@Param("userId") String userId, @Param("newPassword") String newPassword);

    @Update("UPDATE test_user SET user_password = #{user_password}, user_name = #{user_name} WHERE user_id = #{user_id}")
    int updateProfile(UserMyPageVO user);

}
