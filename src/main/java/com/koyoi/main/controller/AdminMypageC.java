package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMypageC {

    @GetMapping("/admin")
    public String admin() {
        return "adminmypage/adminmypage";
    }



}
