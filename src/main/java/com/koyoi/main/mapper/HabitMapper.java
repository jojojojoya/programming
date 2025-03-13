package com.koyoi.main.mapper;

import com.koyoi.main.vo.HabitVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HabitMapper {
    // test_habit 테이블에서 user_name을 기준으로 습관을 조회하는 쿼리
    @Select("SELECT habit_id, habit_name FROM test_habit WHERE user_id = 'user1'")
    List<HabitVO> getUserHabits(String userId);
}

