package com.koyoi.main.service;

import com.koyoi.main.dto.UserDTO;
import com.koyoi.main.mapper.SignUpMapper;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final SignUpMapper signupMapper;

    public SignUpService(SignUpMapper signupMapper) {
        this.signupMapper = signupMapper;
    }

    // 아이디 중복 체크
    public boolean isUserIdDuplicate(String userId) {
        return signupMapper.countUserId(userId) > 0;
    }

    // 회원가입 처리
    public boolean registerUser(UserDTO userDTO) {
        int result = signupMapper.insertUser(userDTO);
        return result > 0;
    }
}
