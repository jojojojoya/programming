package com.koyoi.main.mapper;

import com.koyoi.main.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SignUpMapper {

    // 회원가입
    int insertUser(UserDTO userDTO);

    // XML or Annotation
    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_id = #{userId}")
    int existsById(String userId);

    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_name = #{userName}")
    int existsByName(String userName);

    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_nickname = #{userNickname}")
    int existsByNickname(String userNickname);
}
