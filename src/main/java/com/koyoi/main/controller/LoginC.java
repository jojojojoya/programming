package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginC {

    // 기본 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "Login/Login";  // => /WEB-INF/views/login.jsp로 매핑됨
    }

}
