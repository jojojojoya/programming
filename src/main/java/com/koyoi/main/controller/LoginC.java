package com.koyoi.main.controller;

import com.koyoi.main.service.LoginService;
import com.koyoi.main.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Controller
public class LoginC {

    private final LoginService loginService;
    private final ResourceUrlProvider resourceUrlProvider;

    public LoginC(LoginService loginService, ResourceUrlProvider resourceUrlProvider) {
        this.loginService = loginService;
        this.resourceUrlProvider = resourceUrlProvider;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";  // login.jsp 띄워줌
    }

    // 기본 로그인 페이지
    @PostMapping("/login")
    public String login(@RequestParam("user_id") String userId,
                        @RequestParam("user_pw") String userPw,
                        HttpSession session, Model model) {

        boolean isLogin = loginService.loginCheck(userId, userPw);

        if (isLogin) {
            // 로그인 성공한 유저 정보 가져오기
            UserVO user = loginService.getUserInfo(userId);

            // 세션 저장
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userNickName", user.getUserNickname());
            session.setAttribute("userType", user.getUserType());
            session.setAttribute("userImg", user.getUserImg());

            session.setMaxInactiveInterval(30 * 60);

            // debug
            System.out.println("userId = " + user.getUserId());
            System.out.println("usernickname = " + user.getUserNickname());
            System.out.println("userType = " + user.getUserType());
            System.out.println("userImg = " + user.getUserImg());

            if(user.getUserType() == 3 ){
                return "redirect:/admin";
            } else {
                return "redirect:/main";
            }

        } else {
            model.addAttribute("loginFailed", true);
            return "login/login";
        }
    }
}
