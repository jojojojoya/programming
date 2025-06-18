package com.jojonezip.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jojonezip.demo.service.LoginService;
import com.jojonezip.demo.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public String login(@RequestParam("userId") String userId,
	                    @RequestParam("userPassword") String userPassword,
	                    HttpSession session, Model model) {

	    boolean isValidUser = loginService.loginCheck(userId, userPassword);
	    UserVO user = loginService.getUserInfo(userId);

	    if (isValidUser) {
	        session.setAttribute("loginUser", user);

	        if (user.getUser_type() == 2) {
	            // 관리자
	        	System.out.println("관리자입니다. ID: " + user.getUser_id() + ", Type:" + user.getUser_type());
	            return "redirect:/adminmypage";
	        } else {
	            // 일반 유저
	        	System.out.println("일반유저입니다. ID: " + user.getUser_id() + ", Type:" + user.getUser_type());
	            return "redirect:/";
	        }
	    } else {
	    	 model.addAttribute("errorMessage", "로그인에 실패했습니다. 아이디 또는 비밀번호를 확인하세요.");
	        return "login/loginpage";
	    }
	}
}