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
    @Select("SELECT COUNT(*) FROM TEST_HABIT WHERE user_id = #{userId} AND habit_name = #{habitName}")
    int countHabitByName(@Param("userId") String userId, @Param("habitName") String habitName);


    @Select("SELECT habit_id, habit_name FROM TEST_HABIT WHERE user_id = #{userId}")
    List<HabitVO> getUserHabits(String userId);

    @Insert("INSERT INTO TEST_HABIT (habit_id, user_id, habit_name, created_at) " +
            "VALUES (#{habit_id}, #{user_id}, #{habit_name}, SYSDATE)")
    void insertHabitForTracking(HabitVO habit);

    @Delete("DELETE FROM TEST_HABIT WHERE user_id = #{userId} AND habit_id = #{habitId}")
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



//    // ✅ 회고 메모용 - 유저의 첫 번째(가장 오래된) 습관 ID 조회
//    @Select("SELECT habit_id FROM TEST_HABIT WHERE user_id = #{userId} ORDER BY created_at FETCH FIRST 1 ROWS ONLY")
//    Integer getFirstHabitId(String userId);
//
//    // ✅ 회고 메모 insert
//    @Insert("""
//    INSERT INTO TEST_HABIT_TRACKING (
//        tracking_id, habit_id, user_id, completed, tracking_date, weekly_feedback, created_at
//    ) VALUES (
//        TEST_HABIT_TRACKING_SEQ.NEXTVAL, #{habit_id}, #{user_id}, 0, #{tracking_date}, #{weekly_feedback}, SYSDATE
//    )
//""")
//    void insertWeeklyFeedback(@Param("habit_id") int habitId,
//                              @Param("user_id") String userId,
//                              @Param("tracking_date") Date trackingDate,
//                              @Param("weekly_feedback") String feedback);
//
//    // ✅ 회고 메모 update
//    @Update("""
//    UPDATE TEST_HABIT_TRACKING
//    SET weekly_feedback = #{weekly_feedback}
//    WHERE user_id = #{user_id}
//    AND TRUNC(tracking_date) = TRUNC(#{tracking_date})
//""")
//    void updateWeeklyFeedback(@Param("user_id") String userId,
//                              @Param("tracking_date") Date trackingDate,
//                              @Param("weekly_feedback") String feedback);
//
//    // ✅ 회고 메모 조회
//    @Select("""
//    SELECT weekly_feedback
//    FROM TEST_HABIT_TRACKING
//    WHERE user_id = #{user_id}
//    AND TRUNC(tracking_date) = TRUNC(#{tracking_date})
//    AND weekly_feedback IS NOT NULL
//    FETCH FIRST 1 ROWS ONLY
//""")
//    String getWeeklyFeedback(@Param("user_id") String userId,
//                             @Param("tracking_date") Date trackingDate);
//

    // ✅ 유저의 가장 오래된 습관 ID 조회 (habit_id 형식용으로만 사용)
    @Select("""
    SELECT habit_id 
    FROM TEST_HABIT 
    WHERE user_id = #{userId} 
    ORDER BY created_at 
    FETCH FIRST 1 ROWS ONLY
""")
    Integer getFirstHabitId(String userId);

    // ✅ 회고 메모 insert (habit_id는 대표로 아무거나 하나 사용)
    @Insert("""
    INSERT INTO TEST_HABIT_TRACKING (
        tracking_id, habit_id, user_id, completed, tracking_date, weekly_feedback, created_at
    ) VALUES (
        TEST_HABIT_TRACKING_SEQ.NEXTVAL, #{habit_id}, #{user_id}, 0, #{tracking_date}, #{weekly_feedback}, SYSDATE
    )
""")
    void insertWeeklyFeedback(@Param("habit_id") int habitId,
                              @Param("user_id") String userId,
                              @Param("tracking_date") Date trackingDate,
                              @Param("weekly_feedback") String feedback);

    // ✅ 회고 메모 update (user_id + tracking_date 기준)
    @Update("""
    UPDATE TEST_HABIT_TRACKING
    SET weekly_feedback = #{weekly_feedback}
    WHERE user_id = #{user_id}
    AND TRUNC(tracking_date) = TRUNC(#{tracking_date})
""")
    void updateWeeklyFeedback(@Param("user_id") String userId,
                              @Param("tracking_date") Date trackingDate,
                              @Param("weekly_feedback") String feedback);

    // ✅ 회고 메모 조회 (해당 날짜에 피드백 존재 여부 확인용)
    @Select("""
    SELECT weekly_feedback
    FROM TEST_HABIT_TRACKING
    WHERE user_id = #{user_id}
    AND TRUNC(tracking_date) = TRUNC(#{tracking_date})
    AND weekly_feedback IS NOT NULL
    FETCH FIRST 1 ROWS ONLY
""")
    String getWeeklyFeedback(@Param("user_id") String userId,
                             @Param("tracking_date") Date trackingDate);

}
