package com.koyoi.main.service;

import com.koyoi.main.mapper.HabitTrackingMapper;
import com.koyoi.main.vo.HabitTrackingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitTrackingService {

    @Autowired
    private HabitTrackingMapper habitTrackingMapper;

    public List<HabitTrackingVO> getHabitTrackingByUser(String userId) {
        return habitTrackingMapper.getHabitTrackingByUser(userId);
    }



}
