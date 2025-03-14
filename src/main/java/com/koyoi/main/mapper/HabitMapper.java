package com.koyoi.main.mapper;

import com.koyoi.main.vo.HabitVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HabitMapper {

    // 수정된 getUserHabits 쿼리
    @Select("SELECT habit_id, habit_name FROM test_habit WHERE user_id = #{userId}")
    List<HabitVO> getUserHabits(String userId);

    // 습관을 test_habit 테이블에 추가하는 쿼리
    @Insert("INSERT INTO TEST_HABIT (habit_id, user_id, habit_name, created_at) " +
            "VALUES (TEST_HABIT_SEQ.NEXTVAL, #{user_id}, #{habit_name}, SYSDATE)")  // 변수명 수정
    void insertHabit(HabitVO habitVO);  // 습관 추가
}
