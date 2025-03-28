package com.koyoi.main.controller;

import com.koyoi.main.dto.CompletedDTO;
import com.koyoi.main.dto.TodayHabitDTO;
import com.koyoi.main.service.*;
import com.koyoi.main.vo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private AdminMypageService adminMypageService;

    /* 메인 페이지 */
    @GetMapping("/main")
    public String main(HttpSession session, Model model) {

        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }

        // 공지사항 모달에 띄우기
        model.addAttribute("announcements", announcementService.getFiveAnnouncements());

        // 슬라이더에 명언 데이터 띄우기
        model.addAttribute("quotes", quoteService.getAllQuotes());

        return "main/main";
    }

    /* Emoji */
    @GetMapping("/calendar/emotions")
    @ResponseBody
    public List<EmotionVO> getAllEmotions(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }
        return emotionService.getUserAllEmotions(userId);
    }

    @GetMapping("/mood/scores")
    @ResponseBody
    public List<EmotionVO> getWeeklyMoodScores(HttpSession session, @RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }

        return emotionService.getWeeklyMoodScores(userId, startDate, endDate);
    }

    @GetMapping("/habit-tracking/list")
    @ResponseBody
    public List<HabitTrackingVO> getHabitTrackingList(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }
        List<HabitTrackingVO> habits = habitTrackingService.getHabitTrackingByUser(userId);
        return habits;
    }


/*    @GetMapping("/habit-tracking/list/today")
    @ResponseBody
    public TodayHabitDTO getTodayHabitTrackingList(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if(userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }

        boolean hasHabits = habitTrackingService.countHabitTrackingByUser(userId);
        List<HabitTrackingVO> habits = habitTrackingService.getTodayHabitTrackingByUser(userId);

        return new TodayHabitDTO(hasHabits, habits);*/

    @GetMapping("/habit-tracking/list/today")
    @ResponseBody
    public TodayHabitDTO getTodayHabitTrackingList(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }

        LocalDate today = LocalDate.now();
        List<HabitTrackingVO> habits = habitTrackingService.getHabitsWithTrackingByDate(userId, today);

        boolean hasHabits = !habits.isEmpty();
        return new TodayHabitDTO(hasHabits, habits);

    }

    @GetMapping("/habit-tracking/list/by-date")
    @ResponseBody
    public List<HabitTrackingVO> getHabitTrackingListByDate(HttpSession session, @RequestParam("date") String formattedDate) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(formattedDate, formatter);
        return habitTrackingService.getHabitTrackingByUserAndDate(userId, date);
    }


    @PostMapping("/habit-tracking/toggle/{habitId}")
    @ResponseBody
    public String toggleHabit(@PathVariable int habitId, @RequestBody CompletedDTO dto, HttpSession session) {

        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }

        int completed = dto.getCompleted();
        LocalDate today = LocalDate.now();

        habitTrackingService.toggleHabit(userId, habitId, today, completed);
        return "OK";
    }

}

