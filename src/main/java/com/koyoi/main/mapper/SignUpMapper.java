package com.koyoi.main.mapper;

import com.koyoi.main.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignUpMapper {
    int countUserId(String userId);  // 아이디 중복 체크
    int insertUser(UserDTO userDTO); // 회원가입
}
