package com.koyoi.main.service;

import com.koyoi.main.mapper.HabitMapper;
import com.koyoi.main.vo.HabitTrackingVO;
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
//        habitMapper.insertHabit(habitVO);

        // 추가된 습관 정보를 반환 (습관이 DB에 저장됨)
        return habitVO;  // 추가된 습관을 반환
    }

//    //습관추가(유저입력)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void addNewHabit(String userId, String habitName) {
//        if (habitMapper == null) {
//            throw new IllegalStateException("habitMapper가 null입니다! @MapperScan이 설정되었는지 확인하세요.");
//        }
//
//        // ✅ habit_id와 tracking_id 먼저 생성
//        int newHabitId = habitMapper.getNewHabitId();       // ← 이 줄이 없어서 빨간줄 떴던 거야!
//        int newTrackingId = habitMapper.getNewTrackingId();
//
//        // 1. test_habit insert
//        HabitVO habit = new HabitVO();
//        habit.setHabit_id((long) newHabitId); // ✅ 반드시 넣기!
//        habit.setUser_id(userId);
//        habit.setHabit_name(habitName);
//
//        habitMapper.insertHabitForTracking(habit); // habit_id가 포함된 insert 사용
//
//        // 2. tracking insert
//        habitMapper.insertHabitTracking(newTrackingId, newHabitId, userId, trackingDate);
//    }


    // 습관 삭제
    public boolean deleteHabit(String userId, int habitId) {
        // userId를 "user1"로 고정
        userId = "user1";

        // HabitMapper에서 삭제 처리
        int result = habitMapper.deleteHabit(userId, habitId);

        // 삭제가 성공하면 1을 반환, 아니면 0을 반환
        return result > 0;
    }

//    public void addNewHabit(HabitVO habit) {
//        habitMapper.insertHabitTWO(habit);  // VO 전체를 넘김
//    }





    @Transactional
    public void addHabitWithTracking(HabitTrackingVO vo) {
        try {
            // 1. habit_id 생성 (시퀀스)
            int newHabitId = habitMapper.getNewHabitId();
            vo.setHabit_id(newHabitId); // ✅ tracking insert용 VO에 넣기

            // 2. 습관 등록 (test_habit insert용 VO 만들기)
            HabitVO habit = new HabitVO();
            habit.setHabit_id(newHabitId);
            habit.setUser_id(vo.getUser_id());
            habit.setHabit_name(vo.getHabit_name());

            System.out.println("✅ [HABIT] 생성된 habit_id: " + newHabitId);
            System.out.println("📥 [HABIT] habit_name: " + habit.getHabit_name());

            habitMapper.insertHabitForTracking(habit);
            System.out.println("🔥 insertHabitForTracking 실행 완료");

            // 3. tracking insert (tracking_id는 트리거에서 자동 생성)
            System.out.println("📤 [DEBUG] 트래킹 insert 요청: " +
                    "user=" + vo.getUser_id() +
                    ", habit=" + vo.getHabit_name() +
                    ", date=" + vo.getTracking_date());

            habitMapper.insertHabitTrackingWithoutId(vo);
            System.out.println("🔥 insertHabitTrackingWithoutId 실행 완료");

        } catch (Exception e) {
            System.out.println("❌ [ERROR] addHabitWithTracking 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }






}
