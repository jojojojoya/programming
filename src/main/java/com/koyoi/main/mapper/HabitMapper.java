package com.koyoi.main.mapper;

import com.koyoi.main.vo.HabitVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HabitMapper {


    // 수정된 getUserHabits 쿼리
    @Select("SELECT habit_id, habit_name FROM test_habit WHERE user_id = #{userId}")
    List<HabitVO> getUserHabits(String userId);

    @Insert("INSERT INTO test_habit (user_id, habit_name, created_at) VALUES (#{userId}, #{habitName}, NOW())")
    void insertHabit(HabitVO habitVO);  // habitVO를 사용하여 SQL 쿼리 실행

    @Delete("DELETE FROM TEST_HABIT WHERE user_id = 'user1' AND habit_id = #{habitId}")
    int deleteHabit(String userId, @Param("habitId") int habitId);



}

