package com.koyoi.main.controller;

import com.koyoi.main.dto.AdminPageDTO;
import com.koyoi.main.service.AnnouncementService;
import com.koyoi.main.vo.AdminMypageVO;
import com.koyoi.main.vo.AnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AnnouncementC {

    @Autowired
    private AnnouncementService announcementService;

    /* 전체 공지사항 목록 */
 /*   @GetMapping("/announcement/list")
    public String getAllAnnouncements(Model model) {
        List<AnnouncementVO> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        model.addAttribute("announcementList", "announcement/announcementList.jsp");
        return "finalindex";
    }*/

    // AJAX 요청에 응답하기 위한 JSON 전용 API
    @GetMapping("/announcement/list")
    @ResponseBody
    public AdminPageDTO<AnnouncementVO> getAnnouncementList(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        int offset = (page - 1) * size;
        List<AnnouncementVO> list = announcementService.getPagedAnnouncementList(offset, size);
        int total = announcementService.getTotalCount();
        return new AdminPageDTO<>(list, total);
    }

    /* 초기 공지사항 목록 페이지 */
    @GetMapping("/announcement")
    public String showAnnouncementPage(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       Model model) {
        int offset = (page - 1) * size;
        List<AnnouncementVO> announcements = announcementService.getPagedAnnouncementList(offset, size);
        int total = announcementService.getTotalCount();
        int totalPages = (int) Math.ceil((double) total / size);

        model.addAttribute("announcements", announcements);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalCount", total);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("announcementList", "announcement/announcementList.jsp");

        return "finalindex";
    }


    /* 공지사항 상세 페이지 이동 */
    @GetMapping("/announcement/view/{id}")
    public String showAnnouncement(@PathVariable("id") int id, Model model) {
        AnnouncementVO announcement = announcementService.getAnnouncementById(id);
        model.addAttribute("announcement", announcement);
        model.addAttribute("announcementDetail", "announcement/announcementDetail.jsp");
        return "finalindex";
    }


}
