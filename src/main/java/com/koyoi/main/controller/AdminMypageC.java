package com.koyoi.main.controller;

import com.koyoi.main.dto.AdminPageDTO;
import com.koyoi.main.mapper.AdminMypageMapper;
import com.koyoi.main.service.AdminMypageService;
import com.koyoi.main.service.AnnouncementService;
import com.koyoi.main.vo.AdminMypageVO;
import com.koyoi.main.vo.AnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminMypageC {

    @Autowired
    private AdminMypageService adminMypageService;

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/admin")
    public String admin(Model model) {

        model.addAttribute("users", adminMypageService.getAllUsers());
        model.addAttribute("counselors", adminMypageService.getAllCounselors());
        model.addAttribute("announcements", announcementService.getAllAnnouncements());
        return "adminmypage/adminmypage";

    }

/*    @GetMapping("/admin/userList")
    @ResponseBody
    public AdminPageDTO<AdminMypageVO> getUserList(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "10") int size) {

        int offset = (page - 1) * size;
        List<AdminMypageVO> users = adminMypageService.getPagedUserList(offset, size);
        int total = adminMypageService.getUserTotalCount();

        return new AdminPageDTO<>(users, total);
    }*/

    @GetMapping("/admin/userDetail")
    @ResponseBody
    public AdminMypageVO userDetail(String userId) {
        return adminMypageService.getUserById(userId);
    }

    @DeleteMapping("/admin/deleteUser")
    @ResponseBody
    public int deleteUser(String userId) {
        return adminMypageService.deleteUserById(userId);
    }

    @PostMapping("/admin/updateUser")
    @ResponseBody
    public int updateUser(@RequestBody AdminMypageVO adminMypageVO) {
        return adminMypageService.updateUser(adminMypageVO);
    }

    /* 공지사항 */
    @GetMapping("/admin/announcementDetail/{id}")
    @ResponseBody
    public AnnouncementVO announcementDetail(@PathVariable("id") int id) {
        return announcementService.getAnnouncementById(id);
    }

    @PostMapping("/admin/updateAnnouncement")
    @ResponseBody
    public int updateAnnouncemnet(@RequestBody AnnouncementVO announcementVO) {
        return announcementService.updateAnnouncement(announcementVO);
    }

    @DeleteMapping("/admin/deleteAnnouncement")
    @ResponseBody
    public int deleteAnnouncement(@RequestParam("announcement_id") int announcementId) {
        return announcementService.deleteAnnouncement(announcementId);
    }

    @PostMapping("/admin/createAnnouncement")
    @ResponseBody
    public int createAnnouncement(@RequestBody AnnouncementVO announcementVO) {
        announcementVO.setAdmin_id("admin001");
        return announcementService.createAnnouncement(announcementVO);
    }
}
