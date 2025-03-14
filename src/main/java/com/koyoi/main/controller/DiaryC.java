package com.koyoi.main.controller;

import com.koyoi.main.service.DiaryService;
import com.koyoi.main.vo.DiaryVO;
import lombok.RequiredArgsConstructor;
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

    /* 캘린더 이모지 이벤트 조회 (FullCalendar에서 호출하는 API) */
    @GetMapping("/events")
    @ResponseBody
    public List<Map<String, Object>> getDiaryEvents() {
        String userId = "user1";
        return diaryService.getDiaryEvents(userId);
    }

    /* 일기 상세 조회 (일기 ID 기준) */
    @GetMapping("/{diaryId}")
    @ResponseBody
    public DiaryVO getDiaryById(@PathVariable int diaryId) {
        return diaryService.getDiaryById(diaryId);
    }

    /* 일기 등록 */
    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> writeDiary(@RequestBody DiaryVO diaryVO) {
        diaryVO.setUser_id("userId"); // 하드코딩
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
