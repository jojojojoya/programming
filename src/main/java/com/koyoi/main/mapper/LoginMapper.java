package com.koyoi.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT user_password FROM TEST_USER WHERE user_id = #{userId}")
    String findPasswordByUserId(@Param("userId") String userId);
}