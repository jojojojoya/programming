package com.koyoi.main.controller;

import com.koyoi.main.mapper.UserMyPageMapper;
import com.koyoi.main.service.UserMyPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageC {


    @GetMapping("/usermypage")
    public String usermypage() {
//            Object mode = session.getAttribute("mode"); // 유저가 로그인할때 세션값
//            model.addAttribute("page", "usermypage/usermypage.jsp");
            return "/usermypage/usermypage";

    }}