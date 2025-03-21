package com.koyoi.main.service;

import com.koyoi.main.mapper.HabitTrackingMapper;
import com.koyoi.main.vo.HabitTrackingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitTrackingService {

    @Autowired
    private HabitTrackingMapper habitTrackingMapper;

    public List<HabitTrackingVO> getHabitTrackingByUser(String userId) {
        return habitTrackingMapper.getHabitTrackingByUser(userId);
    }

    public List<HabitTrackingVO> getTodayHabitTrackingByUser(String userId) {
        return habitTrackingMapper.getTodayHabitTrackingByUser(userId);
    }

    public boolean countHabitTrackingByUser(String userId) {
        return habitTrackingMapper.countHabitsByUser(userId) > 0;
    }

    public void toggleHabitCompletion(int trackingId, Integer completed) {
        habitTrackingMapper.updateHabitCompletion(trackingId, completed);
    }

    public List<HabitTrackingVO> getHabitTrackingByUserAndDate(String userId, LocalDate date) {
        return habitTrackingMapper.getHabitTrackingByUserAndDate(userId, date);
    }
}
