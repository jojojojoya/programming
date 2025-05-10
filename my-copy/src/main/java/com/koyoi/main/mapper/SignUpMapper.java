package com.koyoi.main.mapper;

import com.koyoi.main.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SignUpMapper {

    // 회원가입
    @Insert("INSERT INTO TEST_USER (user_id, user_type, user_name, user_nickname, user_email, user_password, user_img, created_at) " +
            "VALUES (#{userId}, #{userType}, #{userName}, #{userNickname}, #{userEmail}, #{userPassword}, #{userImg}, SYSDATE)")
    Integer insertUser(UserVO user); // 회원가입 처리

    // XML or Annotation
    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_id = #{userId}")
    int existsById(String userId);

    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_email = #{userEmail}")
    int existsByName(String userEmail);

    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_nickname = #{userNickname}")
    int existsByNickname(String userNickname);
}
