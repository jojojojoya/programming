package com.koyoi.main.controller;

import com.koyoi.main.mapper.AdminMypageMapper;
import com.koyoi.main.service.AdminMypageService;
import com.koyoi.main.vo.AdminMypageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminMypageC {

    @Autowired
    private AdminMypageService adminMypageService;

    @GetMapping("/admin")
    public String admin(Model model) {

        model.addAttribute("users", adminMypageService.getAllUsers());
        model.addAttribute("counselors", adminMypageService.getAllCounselors());
        return "adminmypage/adminmypage";

    }


}
