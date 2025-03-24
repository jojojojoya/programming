package com.koyoi.main.controller;

import com.koyoi.main.service.AnnouncementService;
import com.koyoi.main.vo.AdminMypageVO;
import com.koyoi.main.vo.AnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AnnouncementC {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcement")
    public String showAllAnnouncements(Model model) {
        List<AnnouncementVO> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        return "announcement/announcementList";
    }

    @GetMapping("/announcement/view/{id}")
    public String showAnnouncement(@PathVariable("id") int id, Model model) {
        AnnouncementVO announcement = announcementService.getAnnouncementById(id);
        model.addAttribute("announcement", announcement);
        return "announcement/announcementView";
    }


}
