package com.koyoi.main.controller;

import com.koyoi.main.service.LiveChatService;
import com.koyoi.main.service.UserMyPageService;
import com.koyoi.main.vo.UserMyPageVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
public class UserMypageC {

    @Autowired
    private UserMyPageService userMyPageService;

    @Autowired
    private LiveChatService liveChatService;

    private String getLoginUserId(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return userId;
    }

    @GetMapping("/usermypage")
    public String usermypage(HttpSession session, Model model) {
        String userId = getLoginUserId(session);

        // 세션에서 가져온 userId로 조회
        List<UserMyPageVO> userList = userMyPageService.getUserById(userId);
        if (!userList.isEmpty()) {
            UserMyPageVO user = userList.get(0);
            model.addAttribute("user", user);
            System.out.println("유저 정보 로딩: " + user.getUser_id());
        } else {
            System.out.println("해당 user_id 없음: " + userId);
        }

        liveChatService.updateReservationsStatus();

        List<UserMyPageVO> reservations = userMyPageService.getUserReservations(userId);
        List<UserMyPageVO> chatSummaries = userMyPageService.getUserChatBotDetail(userId);

        model.addAttribute("reservations", reservations);
        model.addAttribute("chats", chatSummaries);
        model.addAttribute("usermypage", "usermypage/usermypage.jsp");

        return "/finalindex";
    }


    @PostMapping("/checkPassword")
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData,
                                                              HttpSession session) {
        String userId = getLoginUserId(session);


        String password = requestData.get("password");
        boolean isValid = userMyPageService.checkPassword(userId, password);
        System.out.println("비밀번호 확인 결과: " + (isValid ? "성공" : "실패"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profileupdate")
    public ResponseEntity<Map<String, Boolean>> updateProfile(@RequestBody UserMyPageVO user,
                                                              HttpSession session) {
        String userId = getLoginUserId(session);

        boolean isUpdated = userMyPageService.updateProfile(user);
        System.out.println("프로필 업데이트 결과: " + (isUpdated ? "성공" : "실패"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("updated", isUpdated);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/usermypage/updateStatus")
    public ResponseEntity<Map<String, Boolean>> updateStatus(@RequestBody Map<String, Object> requestData) {
        try {
            int counselingId = (int) requestData.get("counseling_id");
            String status = (String) requestData.get("status");

            System.out.println("상담 상태 업데이트 요청 - ID: " + counselingId + ", 상태: " + status);
            boolean success = liveChatService.updateReservationStatus(counselingId, status);

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", success);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("예외 발생: " + e.getMessage());
            e.printStackTrace();

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/profileupdatewithimg")
    public ResponseEntity<Map<String, Object>> updateProfileWithImg(
            @RequestParam(value = "user_id", required = false) String userId,
            @RequestParam("user_nickname") String nickname,
            @RequestParam(value = "user_password", required = false) String password,
            @RequestParam(value = "user_img", required = false) MultipartFile profileImg,
            HttpSession session) {

        Map<String, Object> response = new HashMap<>();

        try {
            String userIdFromSession = getLoginUserId(session);
            userId = userIdFromSession;


            System.out.println("userId: " + userId);
            System.out.println("nickname: " + nickname);
            System.out.println("password: " + password);
            System.out.println("첨부된 파일: " + (profileImg != null ? profileImg.getOriginalFilename() : "없음"));

            String imgPath = null;
            if (profileImg != null && !profileImg.isEmpty()) {
                String projectPath = System.getProperty("user.dir");
                String uploadDirPath = projectPath + "/src/main/resources/static/imgsource/userProfile";

                File uploadDir = new File(uploadDirPath);
                if (!uploadDir.exists()) {
                    System.out.println("디렉토리 없음 → 생성 시도");
                    uploadDir.mkdirs();
                }

                String filename = userId + "_" + profileImg.getOriginalFilename();
                File file = new File(uploadDir, filename); // 올바른 경로 연결 방식
                profileImg.transferTo(file);
                imgPath = "/imgsource/userProfile/" + filename;


                System.out.println("이미지 저장 완료: " + imgPath);
            }

            UserMyPageVO user = new UserMyPageVO();
            user.setUser_id(userId);
            user.setUser_nickname(nickname);
            if (password != null && !password.isBlank()) {
                user.setUser_password(password);
            }
            if (imgPath != null) {
                user.setUser_img(imgPath);
            }

            boolean updated = userMyPageService.updateProfile(user);
            response.put("updated", updated);
            response.put("newImgPath", imgPath);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("예외 발생: " + e.getMessage());
            e.printStackTrace();
            response.put("updated", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/checkNicknameDuplicate")
    @ResponseBody
    public Map<String, Boolean> checkNicknameDuplicate(@RequestBody Map<String, String> data, HttpSession session) {
        String nickname = data.get("nickname");

        // 세션에서 못 가져오는 경우 대비
        String userId = data.get("user_id");
        if (userId == null) {
            userId = getLoginUserId(session);
        }

        int count = userMyPageService.countNicknameExcludeCurrentUser(nickname, userId);
        boolean isDuplicate = count > 0;

        Map<String, Boolean> response = new HashMap<>();
        response.put("duplicate", isDuplicate);
        return response;
    }
    @GetMapping("/maincalendar")
    public String maincalendar() {
        return "main/maincalendar";
    }


}