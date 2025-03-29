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
import java.time.temporal.TemporalAdjusters;
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
            throw new IllegalStateException("ログインが必要です。");
        }
        return userId;
    }

    // 메인페이지에서 넘어온 날짜를 세션에 저장
    @PostMapping("/setSelectedDate")
    @ResponseBody
    public ResponseEntity<?> setSelectedDate(@RequestBody Map<String, String> requestBody, HttpSession session) {
        String selectedDateStr = requestBody.get("date"); // "YYYY-MM-DD"

        if (selectedDateStr == null || selectedDateStr.isEmpty()) {
            return ResponseEntity.badRequest().body("日付の値が見つかりません。");
        }

        LocalDateTime selectedDate = LocalDate.parse(selectedDateStr).atStartOfDay();
        session.setAttribute("selectedDate", selectedDate); // 세션에 저장
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
            } catch (Exception e) {
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
        return events;
    }

    /* 일기 상세 조회 (diaryId 기준) */
    @GetMapping("/{diaryId}")
    @ResponseBody
    public ResponseEntity<?> getDiaryById(@PathVariable int diaryId) {
        DiaryVO vo = diaryService.getDiaryById(diaryId);

        if (vo == null) {
            return ResponseEntity.status(404).body(Map.of("message", "こよいが見つかりませんでした。"));
        }
        return ResponseEntity.ok(vo);
    }

    /* 일기 날짜 조회 */
    @GetMapping("/date/{date}")
    @ResponseBody
    public DiaryVO getDiaryByDate(@PathVariable String date, HttpSession session) {
        String userId = getLoginUserId(session);

        LocalDate localDate = LocalDate.parse(date); // String date -> LocalDate
        LocalDateTime dateTime = localDate.atStartOfDay(); // LocalDate -> LocalDateTime

        return diaryService.getDiaryByDate(userId, dateTime);
    }

    /* 위클리 ajax 호출 */
    @GetMapping("/weekly")
    @ResponseBody
    public List<DiaryVO> getWeeklySummary(@RequestParam String date, HttpSession session) {
        String userId = getLoginUserId(session);
        LocalDate selectedDate = LocalDate.parse(date);

        LocalDate start = selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate end = start.plusDays(6);

        return diaryService.getWeeklyDiaries(userId, selectedDate);
    }

    /* 일기 등록 */
    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveDiary(@RequestBody DiaryVO diaryVO, HttpSession session) {
        String userId = getLoginUserId(session);

        diaryVO.setUser_id(userId);
        diaryService.saveDiary(diaryVO);

        return Map.of("message", "こよいの登録ができました。", "diaryId", diaryVO.getDiary_id());
    }

    /* 일기 수정 */
    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> updateDiary(@RequestBody DiaryVO diaryVO, HttpSession session) {
        String userId = getLoginUserId(session);

        diaryVO.setUser_id(userId);
        diaryService.updateDiaryAndEmotion(diaryVO);

        return Map.of("message", "こよいの編集ができました。");
    }

    /* 일기 삭제 */
    @DeleteMapping("/delete/{diaryId}")
    @ResponseBody
    public Map<String, Object> deleteDiary(@PathVariable int diaryId, HttpSession session) {
        String userId = getLoginUserId(session);

        diaryService.deleteDiary(diaryId, userId);
        return Map.of("message", "こよいを削除しました。");
    }

    /* 감정 점수 저장 */
    @PutMapping("/emotion/score")
    @ResponseBody
    public Map<String, Object> saveEmotionScore(@RequestBody Map<String, Object> scoreData, HttpSession session) {
        String userId = getLoginUserId(session);

        int diaryId = (int) scoreData.get("diary_id");
        int emotionScore = (int) scoreData.get("emotion_score");

        diaryService.saveEmotionScore(diaryId, emotionScore, userId);
        return Map.of("message", "スコアの登録ができました");
    }
}
