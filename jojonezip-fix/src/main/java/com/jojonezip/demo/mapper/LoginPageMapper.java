package com.jojonezip.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jojonezip.demo.vo.UserVO;

@Mapper
public interface LoginPageMapper {

	@Select("SELECT * FROM \"user\" WHERE user_id = #{userId}")
	UserVO loginCheck(@Param("userId") String userId);
}
