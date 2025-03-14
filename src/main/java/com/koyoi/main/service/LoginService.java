package com.koyoi.main.service;

import com.koyoi.main.mapper.LoginMapper;
import com.koyoi.main.mapper.SampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public boolean loginCheck(String userId, String userPw) {
        // DB에 해당 user_id가 존재하는지 확인하고 비밀번호 일치 여부 확인
        String dbPassword = loginMapper.findPasswordByUserId(userId);

        if (dbPassword != null && dbPassword.equals(userPw)) {
            return true;  // 로그인 성공
        } else {
            return false; // 로그인 실패
        }
    }
}
