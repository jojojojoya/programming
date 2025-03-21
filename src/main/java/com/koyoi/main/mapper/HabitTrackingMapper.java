package com.koyoi.main.mapper;

import com.koyoi.main.vo.HabitTrackingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HabitTrackingMapper {

    @Select("SELECT ht.tracking_id, ht.habit_id, ht.user_id, ht.completed, ht.weekly_feedback, " +
            "ht.tracking_date, ht.created_at, h.habit_name " +
            "FROM TEST_HABIT_TRACKING ht JOIN TEST_HABIT h ON ht.habit_id = h.habit_id " +
            "WHERE ht.user_id = #{userId} ORDER BY ht.tracking_date DESC")
    List<HabitTrackingVO> getHabitTrackingByUser(String userId);

}
