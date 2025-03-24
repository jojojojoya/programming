package com.koyoi.main.mapper;

import com.koyoi.main.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SignUpMapper {

    // 회원가입
    int insertUser(UserVO user);            // 회원가입 처리 (XML 사용)

    // XML or Annotation
    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_id = #{userId}")
    int existsById(String userId);

    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_name = #{userName}")
    int existsByName(String userName);

    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_nickname = #{userNickname}")
    int existsByNickname(String userNickname);
}
