package com.koyoi.main.controller;

import com.koyoi.main.service.UserMyPageService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserMypageC {

    @Autowired
    private UserMyPageService userMyPageService;

    @GetMapping("/usermypage")
    public String usermypage(@RequestParam(value = "user_id", required = false) String user_id,
                             HttpSession session, Model model) {
        System.out.println("🔹 UserMyPageC 실행");

        // 로그인된 사용자 체크
        UserMyPageVO loggedInUser = (UserMyPageVO) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            System.out.println("✅ 로그인된 사용자: " + loggedInUser.getUser_id());
            user_id = loggedInUser.getUser_id();
            model.addAttribute("user", loggedInUser);
        } else {
            System.out.println("⚠️ 비 로그인 유저. 기본 user_id = user5 적용");
            if (user_id == null || user_id.trim().isEmpty()) {
                user_id = "user5"; // 기본 유저 ID 설정
            }
        }

        List<UserMyPageVO> users = userMyPageService.getUserById(user_id);
        if (!users.isEmpty()) {
            UserMyPageVO userProfile = users.get(0);
            model.addAttribute("user", userProfile);
            model.addAttribute("user_id", userProfile.getUser_id());  // JSP에서 접근 가능하도록 추가
            System.out.println("✅ 사용자 프로필 로드 완료: " + userProfile.getUser_id());
        } else {
            System.out.println("❌ 사용자 정보 없음. 기본 페이지 로드");
            return "usermypage/usermypage"; // 기본 페이지로 이동
        }

        List<UserMyPageVO> reservations = userMyPageService.getUserReservations(user_id);
        model.addAttribute("reservations", reservations);  // JSP에서 사용 가능하도록 추가
        System.out.println("✅ 상담 예약 내역 로드 완료, 개수: " + reservations.size());

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
}
