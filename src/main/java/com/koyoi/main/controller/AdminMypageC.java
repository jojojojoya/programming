package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMypageC {

    @GetMapping("/admin")
    public String admin() {
        return "adminmypage/adminmypage";
    }

    // 회원 목록 및 상담사 목록 DB 가져오기

}
