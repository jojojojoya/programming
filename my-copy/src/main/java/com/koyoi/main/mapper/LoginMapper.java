package com.koyoi.main.mapper;

import com.koyoi.main.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT user_password FROM TEST_USER WHERE user_id = #{userId}")
    String findPasswordByUserId(@Param("userId") String userId);

    //  select user table
    @Select("""
        SELECT 
            user_id AS userId,
            user_type AS userType,
            user_name AS userName,
            user_nickname AS userNickname,
            user_email AS userEmail,
            user_password AS userPassword,
            user_img AS userImg,
            created_at AS createdAt
        FROM TEST_USER
        WHERE user_id = #{userId}
    """)
    UserVO getUserInfoById(@Param("userId") String userId);

}