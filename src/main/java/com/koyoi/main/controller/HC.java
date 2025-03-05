package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HC {

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/index2")
    public String home2() {
        return "index2";
    }



    @GetMapping("/detail-sidebar")
    public String sidebar() {
        return "sidebar";
    }




    @GetMapping("/detail-sidebar2")
    public String sidebar2() {
        return "sidebar2";
    }



    @GetMapping("/finalindex")
    public String finalindex() {
        return "finalindex";
    }




}
