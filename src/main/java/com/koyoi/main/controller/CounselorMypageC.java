package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CounselorMypageC {

    @GetMapping("/Counselormypage")
    public String Counselormypage() {return "/counselormypage/counselormypage";}
}