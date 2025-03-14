package com.koyoi.main.controller;

import com.koyoi.main.service.LiveChatService;
import com.koyoi.main.service.UserMyPageService;
import com.koyoi.main.vo.LiveChatVO;
import com.koyoi.main.vo.UserMyPageVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LiveChatC {

    @Autowired
    private LiveChatService liveChatService;

    // "입장하기" 버튼이 필요한 상담 조회
    @GetMapping("/available")
    public List<LiveChatVO> getAvailableReservations() {
        return liveChatService.getAvailableReservations();

    }

    @GetMapping("/livechatreservation")
    public String showLiveChatReservations(Model model) {
        List<LiveChatVO> availableReservations = liveChatService.getAvailableReservations();
        model.addAttribute("availableReservations", availableReservations);
        return "/livechat/livechatreservation";
    }
    @GetMapping("/livechatdetail")
    public String showLiveChatDetails(Model model) {
        List<LiveChatVO> livechattings = liveChatService.getAvailableReservations();
        model.addAttribute("livechattings", livechattings);
        return "/livechat/livechatdetail";
    }

    // "완료된 상담" 버튼이 필요한 상담 조회
    @GetMapping("/completed")
    public List<LiveChatVO> getCompletedReservations() {
        return liveChatService.getCompletedReservations();
    }

    // 상담 예약하기 로직
    @PostMapping("/livechatreservation")
    public ResponseEntity<?> reserveLiveChat(@RequestBody Map<String, String> request, HttpSession session) {
        //  로그인된 유저가 없다면 기본값 "user5" 설정
        String userId = (String) session.getAttribute("user_id");
        if (userId == null) {
            userId = "user5";  // 🔥 기본 유저 "user5" 사용
        }

        System.out.println("현재 예약하는 유저 ID: " + userId); // 디버깅용 출력

        // 날짜 변환 (String → java.sql.Date)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(request.get("livechatreservedate"), formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate); // ✅ SQL DATE로 변환

        // 시간 변환 (String → int)
        int counselingTime = Integer.parseInt(request.get("livechatreservetime").split(":")[0]);
        String category = request.get("livechatcategory");

        // 예약 객체 생성
        LiveChatVO reservation = new LiveChatVO();
        reservation.setUser_id(userId);
        reservation.setCounseling_date(sqlDate);  // ✅ DB 저장 가능하도록 SQL Date 사용
        reservation.setCounseling_time(counselingTime);
        reservation.setCategory(category);
        reservation.setStatus("대기"); // 기본값: 대기
        reservation.setCounselor_id("counselor001"); // 상담사 배정 (임시)

        // 예약 처리
        boolean isReserved = liveChatService.reserveCounseling(reservation);

        if (isReserved) {
            return ResponseEntity.ok(Map.of("success", true, "message", "예약이 완료되었습니다."));
        } else {
            return ResponseEntity.ok(Map.of("success", false, "message", "예약에 실패했습니다."));
        }
    }}