package com.koyoi.main.controller;

import com.koyoi.main.dto.AdminPageDTO;
import com.koyoi.main.mapper.AdminMypageMapper;
import com.koyoi.main.service.AdminMypageService;
import com.koyoi.main.service.AnnouncementService;
import com.koyoi.main.vo.AdminMypageVO;
import com.koyoi.main.vo.AnnouncementVO;
import jakarta.servlet.http.HttpSession;
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
    public String admin(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "5") int size,
                        Model model, HttpSession session) {

        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);
        }

        int offset = (page - 1) * size;

        // 1. 유저 목록 1페이지
        List<AdminMypageVO> users = adminMypageService.getPagedUserList(offset, size);
        int userTotal = adminMypageService.getUserTotalCount();

        // 2. 상담사 목록 1페이지
        List<AdminMypageVO> counselors = adminMypageService.getPagedCounselorList(offset, size);
        int counselorTotal = adminMypageService.getCounselorTotalCount();

        // 3. 공지사항 목록 1페이지
        List<AnnouncementVO> announcements = announcementService.getPagedAnnouncementList(offset, size);
        int announcementTotal = announcementService.getTotalCount();

        model.addAttribute("users", users);
        model.addAttribute("counselors", counselors);
        model.addAttribute("announcements", announcements);

        model.addAttribute("userTotal", userTotal);
        model.addAttribute("counselorTotal", counselorTotal);
        model.addAttribute("announcementTotal", announcementTotal);

        model.addAttribute("userPage", page);
        model.addAttribute("counselorPage", page);
        model.addAttribute("announcementPage", page);

        return "adminmypage/adminmypage";
    }


    /* 회원 목록 API */
    // Page 단위로 조회해서 DTO에 총 개수와 리스트를 담아 반환
    @GetMapping("/admin/userList")
    @ResponseBody
    public AdminPageDTO<AdminMypageVO> getUserList(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "5") int size) {

        int offset = (page - 1) * size;
        List<AdminMypageVO> users = adminMypageService.getPagedUserList(offset, size);
        int total = adminMypageService.getUserTotalCount();

        return new AdminPageDTO<>(users, total);
    }

    @GetMapping("/admin/counselorList")
    @ResponseBody
    public AdminPageDTO<AdminMypageVO> getcounselorList(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "5") int size) {

        int offset = (page - 1) * size;
        List<AdminMypageVO> users = adminMypageService.getPagedCounselorList(offset, size);
        int total = adminMypageService.getCounselorTotalCount();

        return new AdminPageDTO<>(users, total);
    }

    /* 회원 정보 상세 조회 */
    @GetMapping("/admin/userDetail")
    @ResponseBody
    public AdminMypageVO userDetail(String userId) {
        return adminMypageService.getUserById(userId);
    }

    /* 회원 정보 삭제 */
    @DeleteMapping("/admin/deleteUser")
    @ResponseBody
    public int deleteUser(String userId) {
        return adminMypageService.deleteUserById(userId);
    }

    /* 회원 정보 수정 */
    @PostMapping("/admin/updateUser")
    @ResponseBody
    public int updateUser(@RequestBody AdminMypageVO adminMypageVO) {
        return adminMypageService.updateUser(adminMypageVO);
    }


    /* 공지사항 목록 API */
    // Page 단위로 조회해서 DTO에 총 개수와 리스트를 담아 반환
    @GetMapping("/admin/announcementList")
    @ResponseBody
    public AdminPageDTO<AnnouncementVO> getAnnouncementList(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        int offset = (page - 1) * size;
        List<AnnouncementVO> list = announcementService.getPagedAnnouncementList(offset, size);
        int total = announcementService.getTotalCount();
        return new AdminPageDTO<>(list, total);
    }

    /* 공지사항 상세 */
    @GetMapping("/admin/announcementDetail/{id}")
    @ResponseBody
    public AnnouncementVO announcementDetail(@PathVariable("id") int id) {
        return announcementService.getAnnouncementById(id);
    }

    /* 공지사항 수정 */
    @PostMapping("/admin/updateAnnouncement")
    @ResponseBody
    public int updateAnnouncemnet(@RequestBody AnnouncementVO announcementVO) {
        return announcementService.updateAnnouncement(announcementVO);
    }

    /* 공지사항 삭제 */
    @DeleteMapping("/admin/deleteAnnouncement")
    @ResponseBody
    public int deleteAnnouncement(@RequestParam("announcement_id") int announcementId) {
        return announcementService.deleteAnnouncement(announcementId);
    }

    /* 공지사항 작성 */
    @PostMapping("/admin/createAnnouncement")
    @ResponseBody
    public int createAnnouncement(HttpSession session, @RequestBody AnnouncementVO announcementVO, Model model) {

        // 로그인 세션
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            AdminMypageVO user = adminMypageService.getUserById(userId);
            model.addAttribute("user", user);

            announcementVO.setAdmin_id(userId);
        }

        return announcementService.createAnnouncement(announcementVO);
    }
}
