package com.koyoi.main.service;

import com.koyoi.main.mapper.HabitMapper;
import com.koyoi.main.vo.HabitTrackingVO;
import com.koyoi.main.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
        return habitMapper.getUserHabits(userId);
    }

    public HabitVO addHabit(HabitVO habitVO) {
        int newHabitId = habitMapper.getNewHabitId();
        habitVO.setHabit_id(newHabitId);
        habitVO.setUser_id("user1");

        habitMapper.insertHabitForTracking(habitVO);
        return habitVO;
    }

    public boolean deleteHabit(String userId, int habitId) {
        userId = "user1";
        int result = habitMapper.deleteHabit(userId, habitId);
        return result > 0;
    }

    // ✅ 완료된 habit_id 리스트 조회 (문자열 → Date 변환)
//    public List<Integer> getCompletedHabitIds(String userId, String trackingDate) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date parsedDate = sdf.parse(trackingDate);
//            return habitMapper.getCompletedHabitIdsByDate(userId, parsedDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of();
//        }
//    }
    public List<Integer> getCompletedHabitIds(String userId, String trackingDate) {
        if (trackingDate == null || trackingDate.trim().isEmpty()) {
            System.out.println("⛔ [getCompletedHabitIds] 유효하지 않은 날짜 입력: " + trackingDate);
            return Collections.emptyList(); // 빈 리스트 반환
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(trackingDate);
            return habitMapper.getCompletedHabitIdsByDate(userId, parsedDate);
        } catch (Exception e) {
            System.out.println("❌ [getCompletedHabitIds] 날짜 파싱 실패: " + trackingDate);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    // ✅ 체크 여부 저장
    public void saveOrUpdateTracking(HabitTrackingVO vo) {
        try {
            System.out.println("📥 [saveOrUpdateTracking] 받은 VO:");
            System.out.println("    habit_id: " + vo.getHabit_id());
            System.out.println("    user_id: " + vo.getUser_id());
            System.out.println("    tracking_date: " + vo.getTracking_date());
            System.out.println("    completed: " + vo.getCompleted());

            // 기존 기록 확인 (VO → 개별 파라미터로 수정)
            HabitTrackingVO existing = habitMapper.findTrackingByHabitAndDate(
                    vo.getHabit_id(),
                    vo.getUser_id(),
                    vo.getTracking_date()
            );

            if (existing != null) {
                System.out.println("✅ 기존 tracking 기록 존재!");
                habitMapper.updateTracking(vo);
                System.out.println("🔁 updateTracking 실행 완료 (completed = " + vo.getCompleted() + ")");
            } else {
                System.out.println("🆕 기존 기록 없음 → insertTracking 수행");
                habitMapper.insertTracking(vo);
                System.out.println("✅ insertTracking 실행 완료");
            }

        } catch (Exception e) {
            System.out.println("❌ [ERROR] saveOrUpdateTracking 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public List<Map<String, Object>> getWeeklySummary(String userId, Date selectedDate) {
        // 주간 날짜 범위 계산
        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startOfWeek = localDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        Date startDate = Date.from(startOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // 주간 트래킹 데이터 가져오기
        List<HabitTrackingVO> trackingData = habitMapper.getWeeklyTrackingStatus(userId, startDate, endDate);

        // 습관별 정리
        Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
        for (HabitTrackingVO vo : trackingData) {
            int habitId = vo.getHabit_id();
            String habitName = vo.getHabit_name();
            LocalDate trackDate = vo.getTracking_date() != null
                    ? vo.getTracking_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    : null;

            resultMap.putIfAbsent(habitId, new HashMap<>());
            Map<String, Object> habitInfo = resultMap.get(habitId);
            habitInfo.putIfAbsent("habit_name", habitName);
            habitInfo.putIfAbsent("tracking", new boolean[7]);

            if (trackDate != null && vo.getCompleted() != null && vo.getCompleted() == 1) {
                int dayIndex = (int) ChronoUnit.DAYS.between(startOfWeek, trackDate); // 0~6
                if (dayIndex >= 0 && dayIndex < 7) {
                    boolean[] tracking = (boolean[]) habitInfo.get("tracking");
                    tracking[dayIndex] = true;
                }
            }
        }

        // 달성률 + 격려 문구 생성
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map<String, Object> habitInfo : resultMap.values()) {
            boolean[] tracking = (boolean[]) habitInfo.get("tracking");
            int completedCount = 0;
            for (boolean b : tracking) if (b) completedCount++;

            String encouragement = completedCount >= 6 ? "よくできました！"
                    : completedCount >= 4 ? "頑張りましたね！" : "あと少し！";

            habitInfo.put("completed_count", completedCount);
            habitInfo.put("encouragement", encouragement);
            resultList.add(habitInfo);
        }

        return resultList;
    }


}
