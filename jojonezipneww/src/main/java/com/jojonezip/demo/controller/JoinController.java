package com.jojonezip.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jojonezip.demo.service.JoinService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class JoinController {

	@Autowired
	private JoinService joinService;

	@PostMapping("/join")
	// 값 받고
	public String join(@RequestParam("userId") String userId, @RequestParam("userNickname") String userNickname,
			@RequestParam("userPassword") String userPassword, @RequestParam("userPasswordCheck") String userPasswordCheck,
			HttpServletRequest request) {
		
		request.setAttribute("prevUserId", userId);
		request.setAttribute("prevNickname", userNickname);
		
		if (userId == null || userId.trim().isEmpty()) {
			request.setAttribute("joinError", "아이디를 입력하세요.");
			return "join/joinpage";
		}
		
		if (userNickname == null || userNickname.trim().isEmpty()) {
			request.setAttribute("joinError", "닉네임을 입력하세요.");
			return "join/joinpage";
		}
		
		if (userPassword == null || userPassword.trim().isEmpty() ||
			userPasswordCheck == null || userPasswordCheck.trim().isEmpty()){
			request.setAttribute("joinError", "비밀번호를 입력하세요.");
			return "join/joinpage";
		}
		
		if (!userPassword.equals(userPasswordCheck)) {
			request.setAttribute("joinError", "비밀번호가 불일치합니다.");
			return "join/joinpage";
			
		}
		
		boolean isExist = joinService.joinCheck(userId);
		if (isExist) {
			request.setAttribute("joinError", "이미 사용중인 아이디입니다.");
			return "join/joinpage";
		}
		joinService.joinComplete(userId, userPassword, userNickname);
		request.setAttribute("joinSuccess", "회원 가입을 축하합니다!🎶");
		return "join/joinpage";

	}
}