package com.jojonezip.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jojonezip.demo.vo.UserVO;

@Mapper
public interface JoinMapper {

	//기존 회원 정보 조회
	@Select("SELECT * FROM \"user\" WHERE user_id= #{userId}")
	UserVO joinCheck(String userId);
	
	//인서트
	@Insert
	("INSERT INTO \"user\" (user_id, user_nickname, user_password) VALUES (#{userId}, #{userNickname},#{userPassword})")
	void joinComplete(@Param("userId") String userId, @Param("userNickname") String userNickname, @Param("userPassword") String userPassword);
}
