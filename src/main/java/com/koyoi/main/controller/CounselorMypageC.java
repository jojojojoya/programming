package com.koyoi.main.controller;

import com.koyoi.main.service.CounselorMyPageService;
import com.koyoi.main.service.LiveChatService;
import com.koyoi.main.vo.CounselorMyPageVO;
import com.koyoi.main.vo.UserMyPageVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/counselormypage")
@RequiredArgsConstructor
public class CounselorMypageC {

    private final CounselorMyPageService counselorMyPageService;
    private final LiveChatService liveChatService;

    private String getLoginUserId(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("ログインが必要です。");
        }
        return userId;
    }

//    @GetMapping
//    public String counselormypage(HttpSession session, Model model) {
//        String userId = getLoginUserId(session);
//
//        // 상담사 정보 조회
//        List<CounselorMyPageVO> counselorList = counselorMyPageService.getCounselorById(userId);
//        if (!counselorList.isEmpty()) {
//            CounselorMyPageVO counselor = counselorList.get(0);
//            model.addAttribute("counselor", counselor); // JSP에서 ${user.xxx} 바꾸기
//        } else {
//            System.out.println("該当カウンセラーなし: " + userId);
//        }
//
//        // 예약 상태 최신화
//        liveChatService.updateReservationsStatus();
//
//        // 상담사가 받은 예약 목록
//        List<CounselorMyPageVO> reservations = counselorMyPageService.getReservationsByCounselorId(userId);
//        List<CounselorMyPageVO> chatSummaries = counselorMyPageService.getUserChatBotDetail(userId);
//
//        model.addAttribute("reservations", reservations);
//        model.addAttribute("chats", chatSummaries);
//        model.addAttribute("counselormypage", "counselormypage/counselormypage.jsp");
//
//        return "finalindex";
//    }

    // 비밀번호 확인
    @PostMapping("/checkPassword")
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData,
                                                              HttpSession session) {
        String userId = getLoginUserId(session);

        String password = requestData.get("password");
        boolean isValid = counselorMyPageService.checkPassword(userId, password);
        System.out.println("🔎 비밀번호 확인 결과: " + (isValid ? "성공" : "실패"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/profileupdate")
//    public ResponseEntity<Map<String, Boolean>> updateProfile(@RequestBody UserMyPageVO user,
//                                                              HttpSession session) {
//        String userId = getLoginUserId(session);
//
//        boolean isUpdated = CounselorMyPageService.updateProfile(user);
//        System.out.println("🔄 프로필 업데이트 결과: " + (isUpdated ? "성공" : "실패"));
//
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("updated", isUpdated);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/usermypage/updateStatus")
    public ResponseEntity<Map<String, Boolean>> updateStatus(@RequestBody Map<String, Object> requestData) {
        try {
            int counselingId = (int) requestData.get("counseling_id");
            String status = (String) requestData.get("status");

            System.out.println("🔍 상담 상태 업데이트 요청 - ID: " + counselingId + ", 상태: " + status);
            boolean success = liveChatService.updateReservationStatus(counselingId, status);

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", success);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("🚨 예외 발생: " + e.getMessage());
            e.printStackTrace();

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    // 프로필 + 이미지 수정
    @PostMapping("/profileupdatewithimg")
    public ResponseEntity<Map<String, Object>> updateProfileWithImg(
            @RequestParam(value = "user_id", required = false) String userId,
            @RequestParam("user_nickname") String nickname,
            @RequestParam(value = "user_password", required = false) String password,
            @RequestParam(value = "user_img", required = false) MultipartFile profileImg,
            HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        try {
            if (userId == null || userId.trim().isEmpty()) {
                userId = getLoginUserId(session);
            }

            String imgPath = null;
            if (profileImg != null && !profileImg.isEmpty()) {
                String uploadDir = "C:/upload/userprofile/";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                String filename = userId + "_" + profileImg.getOriginalFilename();
                File file = new File(uploadDir + filename);
                profileImg.transferTo(file);
                imgPath = "/upload/userprofile/" + filename;
            }

            CounselorMyPageVO user = new CounselorMyPageVO();
            user.setUser_id(userId);
            user.setUser_nickname(nickname);
            if (password != null && !password.isBlank()) user.setUser_password(password);
            if (imgPath != null) user.setUser_img(imgPath);

            boolean updated = counselorMyPageService.updateProfile(user);
            response.put("updated", updated);
            response.put("newImgPath", imgPath);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("updated", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
