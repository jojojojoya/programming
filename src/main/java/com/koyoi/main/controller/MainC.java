package com.koyoi.main.controller;

import com.koyoi.main.service.AnnouncementService;
import com.koyoi.main.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainC {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("announcements", announcementService.getAllAnnouncements());
        model.addAttribute("quotes", quoteService.getAllQuotes());
        return "main/main";
    }




}
