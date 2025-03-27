package com.koyoi.main.controller;

import com.koyoi.main.service.CounselorMyPageService;
import com.koyoi.main.service.LiveChatService;
import com.koyoi.main.vo.CounselorMyPageVO;
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

    // 세션에서 로그인된 유저 ID 가져오기
    private String getLoginUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        return (userIdObj != null) ? userIdObj.toString() : "counselor1"; // 기본값
    }

    @GetMapping
    public String counselormypage(@RequestParam(value = "user_id", required = false) String user_id,
                                  HttpSession session, Model model) {
        System.out.println("🔹 CounselorMypageC 실행");

        if (user_id == null || user_id.trim().isEmpty()) {
            user_id = getLoginUserId(session);
        }

        // 상담사 정보 조회
        List<CounselorMyPageVO> counselorList = counselorMyPageService.getCounselorById(user_id);
        if (!counselorList.isEmpty()) {
            CounselorMyPageVO counselor = counselorList.get(0);
            model.addAttribute("counselor", counselor);
            model.addAttribute("user", counselor); // ✅ JSP에서 ${user.xxx} 쓰는 경우를 위해
            System.out.println("✅ 상담사 정보 로딩: " + counselor.getUser_id());
        } else {
            System.out.println("❌ 해당 상담사 없음: " + user_id);
        }

        // 예약 상태 최신화
        liveChatService.updateReservationsStatus();

        // 상담사가 받은 예약 목록
        List<CounselorMyPageVO> reservations = counselorMyPageService.getReservationsByCounselorId(user_id);
        System.out.println("🔍 상담사 예약 수: " + reservations.size());
        model.addAttribute("reservations", reservations);

        // 캐시 방지용 타임스탬프
        model.addAttribute("now", System.currentTimeMillis());

        model.addAttribute("counselormypage", "/WEB-INF/views/counselormypage/counselormypage.jsp");

        return "finalindex";
    }

    // 비밀번호 확인
    @PostMapping("/checkPassword")
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData,
                                                              HttpSession session) {
        String userId = requestData.get("user_id");
        if (userId == null || userId.trim().isEmpty()) {
            userId = getLoginUserId(session);
        }

        String password = requestData.get("password");
        boolean isValid = counselorMyPageService.checkPassword(userId, password);
        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);
        return ResponseEntity.ok(response);
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
