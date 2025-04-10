package com.koyoi.main.mapper;

import com.koyoi.main.vo.HabitTrackingVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HabitTrackingMapper {

    @Select("SELECT ht.tracking_id, ht.habit_id, ht.user_id, ht.completed, ht.weekly_feedback, " +
            "ht.tracking_date, ht.created_at, h.habit_name " +
            "FROM TEST_HABIT_TRACKING ht JOIN TEST_HABIT h ON ht.habit_id = h.habit_id " +
            "WHERE ht.user_id = #{userId} ORDER BY ht.tracking_date DESC")
    List<HabitTrackingVO> getHabitTrackingByUser(String userId);

    @Select("SELECT ht.tracking_id, ht.habit_id, ht.user_id, ht.completed, ht.weekly_feedback, " +
            "ht.tracking_date, ht.created_at, h.habit_name " +
            "FROM TEST_HABIT_TRACKING ht JOIN TEST_HABIT h ON ht.habit_id = h.habit_id " +
            "WHERE ht.user_id = #{userId} AND TRUNC(ht.tracking_date) = TRUNC(sysdate) " +
            "ORDER BY ht.tracking_date DESC")
    List<HabitTrackingVO> getTodayHabitTrackingByUser(String userId);

    @Select("SELECT COUNT(*) FROM TEST_HABIT_TRACKING WHERE user_id = #{userId}")
    int countHabitsByUser(String userId);

    @Update("UPDATE TEST_HABIT_TRACKING SET completed = #{completed} WHERE tracking_id = #{trackingId}")
    void updateHabitCompletion(int trackingId, Integer completed);

    @Select("SELECT ht.tracking_id, ht.habit_id, ht.user_id, ht.completed, ht.weekly_feedback, " +
            "ht.tracking_date, ht.created_at, h.habit_name " +
            "FROM TEST_HABIT_TRACKING ht JOIN TEST_HABIT h ON ht.habit_id = h.habit_id " +
            "WHERE ht.user_id = #{userId} AND TRUNC(ht.tracking_date) = #{date} " +
            "ORDER BY ht.tracking_id")
    List<HabitTrackingVO> getHabitTrackingByUserAndDate(String userId, LocalDate date);

    @Select("SELECT h.habit_id, h.user_id, h.habit_name, " +
            "ht.tracking_id, ht.completed, ht.weekly_feedback, ht.tracking_date, ht.created_at " +
            "FROM TEST_HABIT h " +
            "LEFT JOIN TEST_HABIT_TRACKING ht " +
            "ON h.habit_id = ht.habit_id " +
            "AND TRUNC(ht.tracking_date) = #{date} " +
            "WHERE h.user_id = #{userId} " +
            "ORDER BY h.habit_id")
    List<HabitTrackingVO> getHabitsWithTrackingByDate(String userId, LocalDate date);

    // 오늘 해당 Habit에 대한 기록 존재하는지 확인
    @Select("SELECT COUNT(*) FROM TEST_HABIT_TRACKING WHERE user_id = #{userId} AND habit_id = #{habitId} AND TRUNC(tracking_date) = #{date}")
    Integer checkTrackingExists(String userId, int habitId, LocalDate date);

    // 기록 있으면 completed 업데이트
    @Update("UPDATE TEST_HABIT_TRACKING SET completed = #{completed} " +
            "WHERE user_id = #{userId} AND habit_id = #{habitId} AND TRUNC(tracking_date) = #{date}")
    void updateTracking(String userId, int habitId, LocalDate date, int completed);

    // 기록 없으면 새로 insert
    @Insert("INSERT INTO TEST_HABIT_TRACKING (user_id, habit_id, tracking_date, completed, created_at) " +
            "VALUES (#{userId}, #{habitId}, #{date}, #{completed}, SYSDATE)")
    void insertTracking(String userId, int habitId, LocalDateTime date, int completed);
}
