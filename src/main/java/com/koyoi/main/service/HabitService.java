package com.koyoi.main.service;

import com.koyoi.main.mapper.HabitMapper;
import com.koyoi.main.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class HabitService {

    private final DataSource dataSource;

    @Autowired
    public HabitService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private HabitMapper habitMapper;

    public List<HabitVO> getUserHabits(String userId) {
        return habitMapper.getUserHabits(userId);  // HabitMapper에서 DB 조회
    }

    public HabitVO addHabit(HabitVO habitVO) {
        // HabitMapper를 사용하여 습관을 DB에 추가
        habitMapper.insertHabit(habitVO);

        // 추가된 습관 정보를 반환 (습관이 DB에 저장됨)
        return habitVO;  // 추가된 습관을 반환
    }

    // 습관 삭제
    public boolean deleteHabit(String userId, int habitId) {
        // userId를 "user1"로 고정
        userId = "user1";

        // HabitMapper에서 삭제 처리
        int result = habitMapper.deleteHabit(userId, habitId);

        // 삭제가 성공하면 1을 반환, 아니면 0을 반환
        return result > 0;
    }
}
