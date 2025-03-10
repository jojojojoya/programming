package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMypageC {
    @GetMapping("/usermypage")
    public String usermypage() {
//            Object mode = session.getAttribute("mode"); // 유저가 로그인할때 세션값
//            model.addAttribute("page", "usermypage/usermypage.jsp");
            return "/usermypage/usermypage";

    }


@GetMapping("/usermypagechatbot")
public String usermypagechatbot() {
//            Object mode = session.getAttribute("mode"); // 유저가 로그인할때 세션값
//            model.addAttribute("page", "usermypage/usermypage.jsp");
    return "/usermypage/usermypagechatbot";

}

@GetMapping("/livechat")
public String livechat() {
//            Object mode = session.getAttribute("mode"); // 유저가 로그인할때 세션값
//            model.addAttribute("page", "usermypage/usermypage.jsp");
    return "/livechat/livechat";

}}