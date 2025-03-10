package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryC {

    @GetMapping("/diary")
    public String diary() {return "/diary/diary";}
}
