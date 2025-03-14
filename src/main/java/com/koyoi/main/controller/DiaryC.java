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

    // 뷰 페이지 호출 (jsp 이동)
    @GetMapping
    public String diaryPage() {
        return "/diary/diary"; // JSP 위치
    }

    // 캘린더 이모지 이벤트
    @GetMapping("/events")
    @ResponseBody
    public List<Map<String, Object>> getDiaryEvents() {
        List<Map<String, Object>> events = diaryService.getDiaryEvents();
        return events;
    }

    // 날짜별 일기 조회
    @GetMapping("/get")
    @ResponseBody
    public DiaryVO getDiaryById(@RequestParam int diaryId) {
        return diaryService.getDiaryById(diaryId);
    }

    // 일기 등록 (이모지 + 내용)
    @PostMapping("/write")
    @ResponseBody
    public String diaryWrite(@RequestBody DiaryVO diaryVO) {
        diaryVO.setUser_id("user2"); // test user
        diaryService.addDiaryAndEmotion(diaryVO);
        return "일기 등록 완료!";
    }

//    // ✅ 뷰 페이지 호출
//    @GetMapping
//    public String diaryPage(Model model) {
//        List<DiaryVO> diaryList = diaryService.getAllDiaries();
//        model.addAttribute("diaryList", diaryList);
//        return "/diary/diary"; // JSP 하나만 사용
//    }
//
//    // ✅ 특정 일기 상세 조회 (AJAX 호출)
//    @GetMapping("/get")
//    @ResponseBody
//    public DiaryVO getDiaryByDate(@RequestParam String date) {
//        return diaryService.getDiaryByDate(date);
//    }
//
//    // ✅ 다이어리 등록
//    @PostMapping("/write")
//    @ResponseBody
//    public String diaryWrite(@RequestBody DiaryVO diaryVO) {
//        diaryService.addDiary(diaryVO);
//        return "일기 등록 완료!";
//    }
//
//    // ✅ 다이어리 수정
//    @PostMapping("/edit")
//    @ResponseBody
//    public String diaryEdit(@RequestBody DiaryVO diaryVO) {
//        diaryService.updateDiaryAndEmotion(diaryVO);
//        return "일기 수정 완료!";
//    }
//
//    // ✅ 다이어리 삭제
//    @PostMapping("/delete")
//    @ResponseBody
//    public String diaryDelete(@RequestParam int diaryId) {
//        diaryService.deleteDiary(diaryId);
//        return "일기 삭제 완료!";
//    }
//
//    // ✅ 감정 점수 수정
//    @PostMapping("/emotion/score")
//    @ResponseBody
//    public String updateEmotionScore(@RequestParam int diaryId, @RequestParam int score) {
//        diaryService.updateEmotionScore(diaryId, score);
//        return "감정 점수 저장 완료!";
//    }
//
//    // ✅ 전체 다이어리를 JSON으로 반환 (캘린더에 사용)
//    @GetMapping("/list/json")
//    @ResponseBody
//    public List<DiaryVO> getAllDiariesJson() {
//        return diaryService.getAllDiariesForApi();
//    }
}

