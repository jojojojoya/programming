package com.jojonezip.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojonezip.demo.mapper.UserMyPageMapper;
import com.jojonezip.demo.vo.UserVO;

@Service
public class UserMyPageService {

	@Autowired
	private UserMyPageMapper userMyPageMapper;

	public UserVO showMypage(String userId) {
		return userMyPageMapper.showMypage(userId);
	}
	
	public void updateNickname(String userId,String nickname) {
		userMyPageMapper.updateNickname(userId, nickname);
	}
}
