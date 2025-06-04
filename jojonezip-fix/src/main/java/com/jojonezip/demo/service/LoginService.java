package com.jojonezip.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojonezip.demo.mapper.LoginPageMapper;
import com.jojonezip.demo.vo.UserVO;

@Service
public class LoginService {
	
	@Autowired
	private LoginPageMapper loginPageMapper;

	
	public boolean loginCheck(String userId, String userPassword) {
	    UserVO user = loginPageMapper.loginCheck(userId);
	    return user != null && userPassword.equals(user.getUser_password());
	}

	public UserVO getUserInfo(String userId) {
	    return loginPageMapper.loginCheck(userId);
	}
}
