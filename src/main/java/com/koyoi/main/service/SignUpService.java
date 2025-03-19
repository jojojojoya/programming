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

    // 회원가입 처리
    public boolean registerUser(UserDTO userDTO) {
        int result = signupMapper.insertUser(userDTO);
        return result > 0;
    }

    // id 중복 체크
    public boolean isUserIdDuplicate(String userId) {
        return signupMapper.existsById(userId) > 0;
    }

    // name 중복 체크
    public boolean isUserNameDuplicate(String userName) {
        return signupMapper.existsByName(userName) > 0;
    }

    // nickname 중복 체크
    public boolean isUserNicknameDuplicate(String userNickname) {
        return signupMapper.existsByNickname(userNickname) > 0;
    }

}
