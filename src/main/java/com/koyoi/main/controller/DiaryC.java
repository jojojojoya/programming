package com.koyoi.main.controller;

import com.koyoi.main.service.DiaryService;
import com.koyoi.main.vo.DiaryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    // 뷰 페이지 렌더링
    @GetMapping
    public String diaryPage(Model model) {
        String userId = "user1";
        List<Map<String, Object>> diaryEvents = diaryService.getDiaryEvents(userId);
        model.addAttribute("diaryEvents", diaryEvents);

        return "diary/diary";
    }

    /* 캘린더 이모지 이벤트 조회 */
    @GetMapping("/events")
    @ResponseBody
    public List<Map<String, Object>> getDiaryEvents() {
        String userId = "user1";
        List<Map<String, Object>> events = diaryService.getDiaryEvents(userId);

        System.out.println("이벤트 리스트: " + events);

        return events;

//        String userId = "user1";
//        return diaryService.getDiaryEvents(userId);
    }

    /* 일기 상세 조회 (일기 ID 기준) */
    @GetMapping("/{diaryId}")
    @ResponseBody
    public ResponseEntity<?> getDiaryById(@PathVariable int diaryId) {
        System.out.println("[DiaryController] 요청받은 diaryId: " + diaryId);

        DiaryVO vo = diaryService.getDiaryById(diaryId);

        if (vo == null) {
            System.out.println("[DiaryController] 일기 없음, diaryId: " + diaryId);
            return ResponseEntity.status(404).body(Map.of("message",  "일기를 찾을 수 없습니다."));
        }

        System.out.println("[DiaryC] 반환할 DiaryVO: " + vo);
        return ResponseEntity.ok(vo);
    }


    /* 일기 날짜 조회 */
    @GetMapping("/date/{date}")
    @ResponseBody
    public DiaryVO getDiaryByDate(@PathVariable String date) {
        String userId = "user1"; // 로그인 연동 시 세션에서 userId 가져오기!

        return diaryService.getDiaryByDate(userId, date);
    }

    /* 일기 등록 */
    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveDiary(@RequestBody DiaryVO diaryVO) {
        System.out.println("[DiaryC] 받은 diaryVO: " + diaryVO);
        diaryVO.setUser_id("user1"); // 하드코딩
        diaryService.saveDiary(diaryVO);

        return Map.of("message", "일기 등록 완료!", "diaryId", diaryVO.getDiary_id());
    }

    /* 일기 수정 */
    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> updateDiary(@RequestBody DiaryVO diaryVO) {
        diaryVO.setUser_id("userId");
        diaryService.updateDiary(diaryVO);

        return Map.of("message", "일기 수정 완료!");
    }

    /* 일기 삭제 */
    @DeleteMapping("/delete/{diaryId}")
    @ResponseBody
    public Map<String, Object> deleteDiary(@PathVariable int diaryId) {
        diaryService.deleteDiary(diaryId);
        return Map.of("message", "일기 삭제 완료!");
    }

    /* 감정 점수 저장 */
    @PutMapping("/emotion/score")
    @ResponseBody
    public Map<String, Object> saveEmotionScore(@RequestBody Map<String, Object> scoreData) {
        int diaryId = (int) scoreData.get("diary_id");
        int emotionScore = (int) scoreData.get("emotion_score");

        diaryService.saveEmotionScore(diaryId, emotionScore);
        return Map.of("message", "감정 점수 저장 완료!");
    }
}
