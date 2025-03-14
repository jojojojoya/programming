package com.koyoi.main.service;

import com.koyoi.main.mapper.HabitMapper;
import com.koyoi.main.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    @Autowired
    private HabitMapper habitMapper;

    public List<HabitVO> getUserHabits(String userId) {
        return habitMapper.getUserHabits(userId);  // HabitMapper에서 DB 조회
    }

    // 습관 추가 메소드
    public void addHabit(HabitVO habitVO) {
        habitMapper.insertHabit(habitVO);  // HabitMapper에서 DB에 습관 삽입
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
