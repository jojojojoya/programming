package com.koyoi.main.mapper;

import com.koyoi.main.vo.AdminMypageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMypageMapper {

    @Select("select * from test_user where user_type = 1 order by created_at DESC")
    List<AdminMypageVO> getAllUsers();

    @Select("select * from test_user where user_type = 2 order by created_at DESC")
    List<AdminMypageVO> getAllCounselors();

    @Select("select * from test_user where user_id = #{userId}")
    AdminMypageVO getUserById(@Param("userId") String userId);

    @Delete("delete from test_user where user_id = #{userId}")
    int deleteUserById(String userId);

    @Update("update test_user set user_password = #{user_password}, user_nickname = #{user_nickname}, user_email = #{user_email} where user_id = #{user_id}")
    int updateUser(AdminMypageVO adminMypageVO);

/*    @Select("SELECT COUNT(*) FROM TEST_USER WHERE user_type = 1")
    int selectUserTotalCount();

    @Select("SELECT * FROM TEST_USER WHERE user_type = 1 ORDER BY created_at DESC LIMIT #{size} OFFSET #{offset}")
    List<AdminMypageVO> selectUserPage(int offset, int size);*/
}
