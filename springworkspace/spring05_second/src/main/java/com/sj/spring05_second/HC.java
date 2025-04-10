package com.sj.spring05_second;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HC {

    @GetMapping("/")
    public String home(){
        System.out.println("Home Page");
        return "index";
    }


}
