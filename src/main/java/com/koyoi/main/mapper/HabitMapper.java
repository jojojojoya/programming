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


    // 1. 새로운 habit_id 가져오기 (시퀀스 사용)
    @Select("SELECT test_habit_seq.NEXTVAL FROM DUAL")
    int getNewHabitId();

    // 2. `TEST_HABIT` 테이블에 새 습관 추가
    @Insert("INSERT INTO TEST_HABIT (user_id, habit_name, created_at) " +
            "VALUES (#{user_id}, #{habit_name}, SYSDATE)")
    void insertHabitTWO(HabitVO habit);

    // 3. 새로운 tracking_id 가져오기 (시퀀스 사용)
    @Select("SELECT test_habit_tracking_seq.NEXTVAL FROM DUAL")
    int getNewTrackingId();

    // 4. `TEST_HABIT_TRACKING` 테이블에 수행 데이터 추가 (사용자가 선택한 tracking_date 저장)
    @Insert("INSERT INTO TEST_HABIT_TRACKING (tracking_id, habit_id, user_id, completed, tracking_date, created_at) " +
            "VALUES (#{trackingId}, #{habitId}, #{userId}, 0, TO_DATE(#{trackingDate}, 'YYYY-MM-DD'), SYSDATE)")
            void insertHabitTracking(@Param("trackingId") int trackingId,
                             @Param("habitId") int habitId,
                             @Param("userId") String userId,
                             @Param("trackingDate") String trackingDate);

}

