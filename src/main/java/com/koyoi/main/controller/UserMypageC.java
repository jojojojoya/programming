package com.koyoi.main.controller;

import com.koyoi.main.service.LiveChatService;
import com.koyoi.main.service.UserMyPageService;
import com.koyoi.main.vo.UserMyPageVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserMypageC {

    @Autowired
    private UserMyPageService userMyPageService;
    @Autowired
    private LiveChatService liveChatService;


//    @GetMapping("/usermypage")
//    public String usermypage(@RequestParam(value = "user_id", required = false) String user_id,
//                             HttpSession session, Model model) {
//        System.out.println("🔹 UserMyPageC 실행");
//
//        if (user_id == null || user_id.trim().isEmpty()) {
//            System.out.println("⚠️ user_id가 없음! 기본값 user5 적용");
//            user_id = "user5";
//        } else {
//            System.out.println("✅ 전달된 user_id: " + user_id);
//        }
//
//        UserMyPageVO loggedInUser = (UserMyPageVO) session.getAttribute("loggedInUser");
//        if (loggedInUser != null) {
//            user_id = loggedInUser.getUser_id();
//            model.addAttribute("user", loggedInUser);
//            System.out.println("🔍 세션 user_id: " + user_id);
//        }
//
//        List<UserMyPageVO> reservations = userMyPageService.getUserReservations(user_id);
//        System.out.println(reservations);
//        model.addAttribute("reservations", reservations);
//
//        return "usermypage/usermypage";
//    }

    @GetMapping("/usermypage")
    public String usermypage(@RequestParam(value = "user_id", required = false) String user_id,
                             HttpSession session, Model model) {
        System.out.println("🔹 UserMyPageC 실행");

        if (user_id == null || user_id.trim().isEmpty()) {
            System.out.println("⚠️ user_id가 없음! 기본값 user5 적용");
            user_id = "user5";
        } else {
            System.out.println("✅ 전달된 user_id: " + user_id);
        }

        // 세션에 로그인된 유저 정보가 있다면 사용
        UserMyPageVO loggedInUser = (UserMyPageVO) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            user_id = loggedInUser.getUser_id();
            model.addAttribute("user", loggedInUser);
            System.out.println("🔍 세션에서 가져온 user: " + loggedInUser);
        } else {
            // DB에서 user5 데이터 가져오기
            List<UserMyPageVO> userList = userMyPageService.getUserById(user_id);

            if (!userList.isEmpty()) {
                UserMyPageVO user = userList.get(0);
                model.addAttribute("user", user);
                System.out.println("✅ DB에서 가져온 user: " + user);
            } else {
                System.out.println("❌ user5 정보 없음");
            }
        }

        // ✅ 상담 내역을 가져오기 전에 상담 상태를 최신화
        liveChatService.updateReservationsStatus();

        List<UserMyPageVO> reservations = userMyPageService.getUserReservations(user_id);
        model.addAttribute("reservations", reservations);


        return "usermypage/usermypage";
    }



    @PostMapping("/checkPassword")
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData) {
        String userId = requestData.get("user_id");
        String password = requestData.get("password");

        if (userId == null || userId.trim().isEmpty()) {
            System.out.println("❌ [오류] user_id가 제공되지 않음. 기본 user5 사용");
            userId = "user5"; // 기본 유저 ID 할당
        }

        boolean isValid = userMyPageService.checkPassword(userId, password);
        System.out.println("🔎 비밀번호 확인 결과: " + (isValid ? "성공" : "실패"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profileupdate")
    public ResponseEntity<Map<String, Boolean>> updateProfile(@RequestBody UserMyPageVO user) {
        if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
            System.out.println("❌ [오류] user_id가 비어 있음. 기본 user5 적용");
            user.setUser_id("user5");
        }

        boolean isUpdated = userMyPageService.updateProfile(user);
        System.out.println("🔄 프로필 업데이트 결과: " + (isUpdated ? "성공" : "실패"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("updated", isUpdated);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/usermypage/updateStatus")  // 변경됨
    public ResponseEntity<Map<String, Boolean>> updateStatus(@RequestBody Map<String, Object> requestData) {
        try {
            int counselingId = (int) requestData.get("counseling_id");
            String status = (String) requestData.get("status");

            System.out.println("🔍 [백엔드] 업데이트 요청 - 상담 ID: " + counselingId + ", 상태: " + status);

            boolean success = liveChatService.updateReservationStatus(counselingId, status);

            if (!success) {
                System.err.println("❌ [백엔드] 상담 상태 업데이트 실패! 상담 ID가 존재하는지 확인 필요.");
            } else {
                System.out.println("✅ [백엔드] 상담 상태 업데이트 성공!");
            }

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", success);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("🚨 [백엔드] 예외 발생: " + e.getMessage());
            e.printStackTrace();

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }
}