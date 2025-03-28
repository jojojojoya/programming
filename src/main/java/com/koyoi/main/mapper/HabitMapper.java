package com.koyoi.main.mapper;

import com.koyoi.main.vo.HabitTrackingVO;
import com.koyoi.main.vo.HabitVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Date;

@Mapper
public interface HabitMapper {

    // ✅ [1] 기본 습관 관리

    @Select("SELECT TEST_HABIT_SEQ.NEXTVAL FROM DUAL")
    int getNewHabitId();

    @Select("SELECT habit_id, habit_name FROM TEST_HABIT WHERE user_id = #{userId}")
    List<HabitVO> getUserHabits(String userId);

    @Insert("INSERT INTO TEST_HABIT (habit_id, user_id, habit_name, created_at) " +
            "VALUES (#{habit_id}, #{user_id}, #{habit_name}, SYSDATE)")
    void insertHabitForTracking(HabitVO habit);

    @Delete("DELETE FROM TEST_HABIT WHERE user_id = 'user1' AND habit_id = #{habitId}")
    int deleteHabit(String userId, @Param("habitId") int habitId);


    // ✅ [2] 트래킹 관련

    @Select("SELECT habit_id FROM TEST_HABIT_TRACKING " +
            "WHERE user_id = #{user_id} " +
            "AND TRUNC(tracking_date) = TRUNC(#{tracking_date}) " +
            "AND completed = 1")
    List<Integer> getCompletedHabitIdsByDate(
            @Param("user_id") String userId,
            @Param("tracking_date") Date trackingDate);

    @Select("SELECT * FROM TEST_HABIT_TRACKING " +
            "WHERE habit_id = #{habit_id} " +
            "AND user_id = #{user_id} " +
            "AND TRUNC(tracking_date) = TRUNC(#{tracking_date})")
    HabitTrackingVO findTrackingByHabitAndDate(@Param("habit_id") int habitId,
                                               @Param("user_id") String userId,
                                               @Param("tracking_date") Date trackingDate);

    @Insert("INSERT INTO TEST_HABIT_TRACKING (" +
            "tracking_id, habit_id, user_id, completed, tracking_date, created_at) " +
            "VALUES (TEST_HABIT_TRACKING_SEQ.NEXTVAL, #{habit_id}, #{user_id}, #{completed}, #{tracking_date}, SYSDATE)")
    void insertTracking(HabitTrackingVO vo);

    @Update("UPDATE TEST_HABIT_TRACKING " +
            "SET completed = #{completed} " +
            "WHERE habit_id = #{habit_id} " +
            "AND user_id = #{user_id} " +
            "AND TRUNC(tracking_date) = TRUNC(#{tracking_date})")
    int updateTracking(HabitTrackingVO vo);


    @Select("SELECT h.habit_id, h.habit_name, t.tracking_date, t.completed " +
            "FROM TEST_HABIT h " +
            "LEFT JOIN TEST_HABIT_TRACKING t " +
            "ON h.habit_id = t.habit_id AND t.user_id = #{user_id} " +
            "AND t.tracking_date BETWEEN #{start_date} AND #{end_date} " +
            "WHERE h.user_id = #{user_id}")
    List<HabitTrackingVO> getWeeklyTrackingStatus(
            @Param("user_id") String userId,
            @Param("start_date") Date startDate,
            @Param("end_date") Date endDate);



}
