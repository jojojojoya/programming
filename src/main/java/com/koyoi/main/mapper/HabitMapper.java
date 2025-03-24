package com.koyoi.main.mapper;

import com.koyoi.main.vo.HabitTrackingVO;
import com.koyoi.main.vo.HabitVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HabitMapper {


    @Select("SELECT TEST_HABIT_SEQ.NEXTVAL FROM DUAL")
    int getNewHabitId();

    @Select("SELECT habit_id, habit_name FROM TEST_HABIT WHERE user_id = #{userId}")
    List<HabitVO> getUserHabits(String userId);

    @Delete("DELETE FROM TEST_HABIT WHERE user_id = 'user1' AND habit_id = #{habitId}")
    int deleteHabit(String userId, @Param("habitId") int habitId);

    @Insert("INSERT INTO TEST_HABIT (habit_id, user_id, habit_name, created_at) " +
            "VALUES (#{habit_id}, #{user_id}, #{habit_name}, SYSDATE)")
    void insertHabitForTracking(HabitVO habit);

    @Insert("INSERT INTO TEST_HABIT_TRACKING " +
            "(habit_id, user_id, completed, tracking_date, weekly_feedback, created_at) " +
            "VALUES (#{habit_id}, #{user_id}, 0, #{tracking_date}, NULL, SYSDATE)")
    void insertHabitTrackingWithoutId(HabitTrackingVO vo);


}

