package com.koyoi.main.controller;

import com.koyoi.main.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginC {

    private final LoginService loginService;

    public LoginC(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";  // login.jsp 띄워줌
    }

    // 기본 로그인 페이지
    @PostMapping("/login")
    public String login(@RequestParam("user_id") String userId,
                        @RequestParam("user_pw") String userPw,
                        HttpSession session,
                        Model model) {

        boolean isLogin = loginService.loginCheck(userId, userPw);

        if (isLogin) {
            session.setAttribute("userId", userId);  // 세션에 userId 저장
            session.setMaxInactiveInterval(30 * 60); // 세션 유지 시간 (30분)
            return "redirect:/main";
        } else {
            model.addAttribute("loginFailed", true);
            return "login/login";
        }
    }
}
