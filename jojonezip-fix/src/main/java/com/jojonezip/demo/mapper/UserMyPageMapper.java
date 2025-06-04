package com.jojonezip.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jojonezip.demo.vo.UserVO;

@Mapper
public interface UserMyPageMapper {
	
	@Select("SELECT * FROM \"user\" WHERE user_id = #{userId}")
	UserVO showMypage(String userId);
	
	@Update("UPDATE \"user\" SET user_nickname = #{nickname} WHERE user_id = #{userId}")
	void updateNickname(@Param("userId") String userId, @Param("nickname") String nickname);

}
