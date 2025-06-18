package com.jojonezip.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojonezip.demo.mapper.JoinMapper;
import com.jojonezip.demo.vo.UserVO;

@Service
public class JoinService {

	@Autowired
	private JoinMapper joinMapper;

	public boolean joinCheck(String userId)
	{ UserVO user = joinMapper.joinCheck(userId);
	return user != null;
	
}
	public void joinComplete(String userId, String userPassword, String userNickname)
	{ joinMapper.joinComplete(userId, userNickname, userPassword);
}}
