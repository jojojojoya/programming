package com.koyoi.main.mapper;

import com.koyoi.main.vo.UserMyPageProfileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMyPageProfileMapper {
    int updateProfile(UserMyPageProfileVO userMyPageProfileVO);
//    @Update
//            ("UPDATE users SET password = #{password}, nickname = #{nickname} WHERE id = #{id}")
//    int updateProfile(ProfileDTO profileDTO);
}