package com.koyoi.main.controller;

import com.koyoi.main.service.DiaryService;
import com.koyoi.main.vo.DiaryVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryC {

    private final DiaryService diaryService;

    // 세션에서 userId 갖고 오는 메소드
    private String getLoginUserId(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return userId;
    }

    // 메인페이지에서 넘어온 날짜를 세션에 저장
    @PostMapping("/setSelectedDate")
    @ResponseBody
    public ResponseEntity<?> setSelectedDate(@RequestBody Map<String, String> requestBody, HttpSession session) {
        String selectedDateStr = requestBody.get("date"); // "YYYY-MM-DD"

        if (selectedDateStr == null || selectedDateStr.isEmpty()) {
            System.out.println("❌ 넘어온 날짜 값 없음!");
            return ResponseEntity.badRequest().body("날짜 값이 없습니다.");
        }

        // String → LocalDateTime 변환 (00:00:00 시간 추가)
        LocalDateTime selectedDate = LocalDate.parse(selectedDateStr).atStartOfDay();

        // 세션에 저장
        session.setAttribute("selectedDate", selectedDate);

        System.out.println("✅ 세션에 저장된 selectedDate(LocalDateTime): " + selectedDate);

        return ResponseEntity.ok().build();
    }

    // 뷰 페이지 렌더링
    @GetMapping
    public String diaryPage(@RequestParam(value = "selectedDate", required = false) String selectedDateParam, HttpSession session, Model model) {
        String userId = getLoginUserId(session);

        // 캘린더 이벤트
        List<Map<String, Object>> diaryEvents = diaryService.getDiaryEvents(userId);
        model.addAttribute("diaryEvents", diaryEvents);

        // 마이페이지에서 날짜 클릭
        if (selectedDateParam != null && !selectedDateParam.isEmpty()) {
            try {
                LocalDateTime parsedDate = LocalDate.parse(selectedDateParam).atStartOfDay();
                session.setAttribute("selectedDate", parsedDate);
                System.out.println("📅 전달된 selectedDate → 세션 저장: " + parsedDate);
            } catch (Exception e) {
                System.err.println("❌ 날짜 파싱 실패: " + selectedDateParam);
            }
        }

        // 세션에서 선택 날짜 꺼내기
        LocalDateTime selectedDate = (LocalDateTime) session.getAttribute("selectedDate");
        if (selectedDate == null) {
            selectedDate = LocalDateTime.now();
        }
        LocalDate selectedDateOnly = selectedDate.toLocalDate();
        String selectedDateStr = selectedDate.toLocalDate().toString();
        model.addAttribute("selectedDate", selectedDateStr);

        // 상세 일기
        DiaryVO diary = diaryService.getDiaryByDate(userId, selectedDate);
        model.addAttribute("selectedDiary", diary);

        // 주간 요약 리스트
        List<DiaryVO> weeklyDiaries = diaryService.getWeeklyDiaries(userId, selectedDateOnly);
        System.out.println("✅ weeklyDiaries size = " + weeklyDiaries.size());
        model.addAttribute("weeklyDiaries", weeklyDiaries);

        // jsp 포함 위치
        model.addAttribute("diaryContent", "diary/diary.jsp");

        return "finalindex";
    }

    /* 캘린더 이모지 이벤트 조회 */
    @GetMapping("/events")
    @ResponseBody
    public List<Map<String, Object>> getDiaryEvents(HttpSession session) {
        String userId = getLoginUserId(session);

        List<Map<String, Object>> events = diaryService.getDiaryEvents(userId);
        System.out.println("이벤트 리스트: " + events);

        return events;
    }

    /* 일기 상세 조회 (일기 ID 기준) - 유저 검증은 생략 (필요 시 추가) */
    @GetMapping("/{diaryId}")
    @ResponseBody
    public ResponseEntity<?> getDiaryById(@PathVariable int diaryId) {
        DiaryVO vo = diaryService.getDiaryById(diaryId);

        if (vo == null) {
            System.out.println("[DiaryC] 일기 없음, diaryId: " + diaryId);
            return ResponseEntity.status(404).body(Map.of("message", "일기를 찾을 수 없습니다."));
        }

        System.out.println("[DiaryC] 반환할 DiaryVO: " + vo);
        return ResponseEntity.ok(vo);
    }

    /* 일기 날짜 조회 */
    @GetMapping("/date/{date}")
    @ResponseBody
    public DiaryVO getDiaryByDate(@PathVariable String date, HttpSession session) {
        String userId = getLoginUserId(session);

        LocalDate localDate = LocalDate.parse(date); // 문자열(YYYY-MM-DD)을 LocalDate로 변환
        LocalDateTime dateTime = localDate.atStartOfDay(); // 시간 정보 추가해서 LocalDateTime 만들기

        return diaryService.getDiaryByDate(userId, dateTime);
    }

    /* 위클리 ajax 호출 */
    @GetMapping("/weekly")
    @ResponseBody
    public List<DiaryVO> getWeeklySummary(@RequestParam String date, HttpSession session) {
        String userId = getLoginUserId(session);
        LocalDate selectedDate = LocalDate.parse(date);

        List<DiaryVO> weeklyDiaries = diaryService.getWeeklyDiaries(userId, selectedDate);
        System.out.println("🗓️ 주간 조회 범위: " + selectedDate.with(DayOfWeek.SUNDAY) + " ~ " + selectedDate.with(DayOfWeek.SATURDAY));
        System.out.println("✅ Ajax용 weeklyDiaries size = " + weeklyDiaries.size());

        return weeklyDiaries;
    }

    /* 일기 등록 */
    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveDiary(@RequestBody DiaryVO diaryVO, HttpSession session) {
        String userId = getLoginUserId(session);
        System.out.println("[DiaryC] 받은 diaryVO: " + diaryVO);

        diaryVO.setUser_id(userId);
        diaryService.saveDiary(diaryVO);

        return Map.of("message", "일기 등록 완료!", "diaryId", diaryVO.getDiary_id());
    }

    /* 일기 수정 */
    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> updateDiary(@RequestBody DiaryVO diaryVO, HttpSession session) {
        String userId = getLoginUserId(session);

        diaryVO.setUser_id(userId);
        diaryService.updateDiaryAndEmotion(diaryVO);

        return Map.of("message", "일기 수정 완료!");
    }

    /* 일기 삭제 (추가로 userId 검증 넣어도 좋음) */
    @DeleteMapping("/delete/{diaryId}")
    @ResponseBody
    public Map<String, Object> deleteDiary(@PathVariable int diaryId, HttpSession session) {
        String userId = getLoginUserId(session);    // 유저 검증시 필요

        diaryService.deleteDiary(diaryId, userId);
        return Map.of("message", "일기 삭제 완료!");
    }

    /* 감정 점수 저장 */
    @PutMapping("/emotion/score")
    @ResponseBody
    public Map<String, Object> saveEmotionScore(@RequestBody Map<String, Object> scoreData, HttpSession session) {
        String userId = getLoginUserId(session);

        int diaryId = (int) scoreData.get("diary_id");
        int emotionScore = (int) scoreData.get("emotion_score");

        diaryService.saveEmotionScore(diaryId, emotionScore, userId);
        return Map.of("message", "감정 점수 저장 완료!");
    }
}
