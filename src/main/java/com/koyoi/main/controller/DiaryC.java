package com.koyoi.main.controller;

import com.koyoi.main.service.DiaryService;
import com.koyoi.main.vo.DiaryVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        String selectedDate = requestBody.get("date");

        if (selectedDate == null || selectedDate.isEmpty()) {
            return ResponseEntity.badRequest().body("날짜 값이 없습니다.");
        }

        session.setAttribute("selectedDate", selectedDate);  // 세션에 날짜 저장

        return ResponseEntity.ok().build();
    }

    // 뷰 페이지 렌더링
    @GetMapping
    public String diaryPage(HttpSession session, Model model) {
        String userId = getLoginUserId(session);

        List<Map<String, Object>> diaryEvents = diaryService.getDiaryEvents(userId);
        model.addAttribute("diaryEvents", diaryEvents);

        // 세션에서 선택 날짜 꺼내기
        String selectedDate = (String) session.getAttribute("selectedDate");

        if (selectedDate != null && !selectedDate.isEmpty()) {
            DiaryVO diary = diaryService.getDiaryByDate(userId, selectedDate);
            model.addAttribute("selectedDiary", diary);
            model.addAttribute("selectedDate", selectedDate);

            session.removeAttribute("selectedDate");  // 사용 후 초기화 (선택)
        }

        return "diary/diary";
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
            return ResponseEntity.status(404).body(Map.of("message",  "일기를 찾을 수 없습니다."));
        }

        System.out.println("[DiaryC] 반환할 DiaryVO: " + vo);
        return ResponseEntity.ok(vo);
    }


    /* 일기 날짜 조회 */
    @GetMapping("/date/{date}")
    @ResponseBody
    public DiaryVO getDiaryByDate(@PathVariable String date, HttpSession session) {
        String userId = getLoginUserId(session);

        return diaryService.getDiaryByDate(userId, date);
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
