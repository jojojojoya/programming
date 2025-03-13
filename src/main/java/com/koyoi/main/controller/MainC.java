package com.koyoi.main.controller;

import com.koyoi.main.service.AnnouncementService;
import com.koyoi.main.service.EmotionService;
import com.koyoi.main.service.QuoteService;
import com.koyoi.main.vo.EmotionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainC {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private EmotionService emotionService;

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("announcements", announcementService.getAllAnnouncements());
        model.addAttribute("quotes", quoteService.getAllQuotes());
        return "main/main";
    }

    @GetMapping("/calendar/emotions")
    @ResponseBody
    public List<EmotionVO> getAllEmotions() {
        String userId = "user2"; // 로그인 기능 추가 시 변경 필요
        return emotionService.getUserAllEmotions(userId);
    }


}
