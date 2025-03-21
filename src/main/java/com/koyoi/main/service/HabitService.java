package com.koyoi.main.service;

import com.koyoi.main.mapper.HabitMapper;
import com.koyoi.main.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Service
public class HabitService {

    private final DataSource dataSource;
    private String trackingDate;

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

    //습관추가(유저입력)
    @Transactional(propagation = Propagation.REQUIRES_NEW)  // ✅ 새로운 트랜잭션으로 실행
    public void addNewHabit(String userId, String habitName) {
        if (habitMapper == null) {
            throw new IllegalStateException("habitMapper가 null입니다! @MapperScan이 설정되었는지 확인하세요.");
        }
        int newHabitId = habitMapper.getNewHabitId(); // 새로운 habit_id 생성
        int newTrackingId = habitMapper.getNewTrackingId(); // 새로운 tracking_id 생성

        // 1. test_habit 테이블에 추가
        HabitVO habit = new HabitVO();
        habit.setUser_id(userId);
        habit.setHabit_name(habitName);

        System.out.println("📌 [DEBUG] test_habit 추가 - habitId: " + newHabitId + ", userId: " + userId + ", habitName: " + habitName);
        habitMapper.insertHabitTWO(habit);
        System.out.println("📌 [DEBUG] 습관 추가 완료");


        // 2. test_habit_tracking 테이블에도 추가
        habitMapper.insertHabitTracking(newTrackingId, newHabitId, userId, trackingDate);
        System.out.println("📌 [DEBUG] test_habit_tracking 추가 - trackingId: " + newTrackingId + ", trackingDate: " + trackingDate);
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

    public void addNewHabit(HabitVO habit) {
        habitMapper.insertHabitTWO(habit);  // VO 전체를 넘김
    }
}
