package com.koyoi.main.mapper;

import com.koyoi.main.vo.AdminMypageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMypageMapper {

    @Select("select * from test_user where user_type = 2 order by created_at DESC")
    List<AdminMypageVO> getAllUsers();

    @Select("select * from test_user where user_type =1 order by created_at DESC")
    List<AdminMypageVO> getAllCounselors();



}
