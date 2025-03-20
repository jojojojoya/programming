package com.koyoi.main.controller;

import com.koyoi.main.service.AnnouncementService;
import com.koyoi.main.service.EmotionService;
import com.koyoi.main.service.HabitTrackingService;
import com.koyoi.main.service.QuoteService;
import com.koyoi.main.vo.AnnouncementVO;
import com.koyoi.main.vo.EmotionVO;
import com.koyoi.main.vo.HabitTrackingVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class MainC {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private EmotionService emotionService;

    @Autowired
    private HabitTrackingService habitTrackingService;

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("announcements", announcementService.getAllAnnouncements());
        model.addAttribute("quotes", quoteService.getAllQuotes());
        return "main/main";
    }

    @GetMapping("/calendar/emotions")
    @ResponseBody
    public List<EmotionVO> getAllEmotions(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if(userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }
        return emotionService.getUserAllEmotions(userId);
    }

    @GetMapping("/mood/scores")
    @ResponseBody
    public List<EmotionVO> getWeeklyMoodScores(HttpSession session, @RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        String userId = (String) session.getAttribute("userId");

        if(userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }

        return emotionService.getWeeklyMoodScores(userId, startDate, endDate);
    }

    @GetMapping("/habit-tracking/list")
    @ResponseBody
    public List<HabitTrackingVO> getHabitTrackingList() {
        String userId = "user1";
        List<HabitTrackingVO> habits = habitTrackingService.getHabitTrackingByUser(userId);
        return habits; // "habits" 키 없이 리스트 자체를 반환
    }

}
