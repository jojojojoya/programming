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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
//@Controller
//public class UserMypageC {
//
//    @Autowired
//    private UserMyPageService userMyPageService;
//    @Autowired
//    private LiveChatService liveChatService;
//
//
//    // UserMypageC 클래스 안쪽에 추가
//    private String getLoginUserId(HttpSession session) {
//        Object userIdObj = session.getAttribute("userId");
//        if (userIdObj != null) {
//            return userIdObj.toString();
//        }
//
//        Object userObj = session.getAttribute("loggedInUser");
//        if (userObj instanceof UserMyPageVO user) {
//            return user.getUser_id();
//        }
//
//        return "user5"; // 기본값
//    }
//
//
//
//    @GetMapping("/usermypage")
//    public String usermypage(@RequestParam(value = "user_id", required = false) String user_id,
//                             HttpSession session, Model model) {
//        System.out.println("🔹 UserMyPageC 실행");
//
//        // 1. 세션에서 로그인된 유저 객체 가져오기
//        UserMyPageVO loggedInUser = (UserMyPageVO) session.getAttribute("loggedInUser");
//
//        if (loggedInUser != null) {
//            // 세션에 유저 정보가 있으면 그대로 사용
//            user_id = loggedInUser.getUser_id();
//            model.addAttribute("user", loggedInUser);
//            System.out.println("✅ 세션에서 가져온 user_id: " + user_id);
//        } else {
//            // 세션이 없고, 파라미터도 없으면 기본값 user5 적용
//            if (user_id == null || user_id.trim().isEmpty()) {
//                user_id = "user5";
//                System.out.println("⚠️ 세션도 없고 user_id도 없음 → 기본 user5 사용");
//            } else {
//                System.out.println("✅ 전달된 user_id: " + user_id);
//            }
//
//            // DB에서 유저 정보 조회
//            List<UserMyPageVO> userList = userMyPageService.getUserById(user_id);
//            if (!userList.isEmpty()) {
//                UserMyPageVO user = userList.get(0);
//                model.addAttribute("user", user);
//                System.out.println("✅ DB에서 가져온 user: " + user.getUser_id());
//            } else {
//                System.out.println("❌ DB에 해당 user_id 없음");
//            }
//        }
//
//        // 상담 상태 최신화
//        liveChatService.updateReservationsStatus();
//
//        // 상담 내역, 챗봇 요약 불러오기
//        List<UserMyPageVO> reservations = userMyPageService.getUserReservations(user_id);
//        List<UserMyPageVO> chatSummaries = userMyPageService.getUserChatBotDetail(user_id);
//
//        model.addAttribute("reservations", reservations);
//        model.addAttribute("chats", chatSummaries);
//
//        return "usermypage/usermypage";
//    }
//
//
//    @PostMapping("/checkPassword")
//    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData) {
//        String userId = requestData.get("user_id");
//        String password = requestData.get("password");
//
//        if (userId == null || userId.trim().isEmpty()) {
//            System.out.println("❌ [오류] user_id가 제공되지 않음. 기본 user5 사용");
//            userId = "user5"; // 기본 유저 ID 할당
//        }
//
//        boolean isValid = userMyPageService.checkPassword(userId, password);
//        System.out.println("🔎 비밀번호 확인 결과: " + (isValid ? "성공" : "실패"));
//
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("valid", isValid);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/profileupdate")
//    public ResponseEntity<Map<String, Boolean>> updateProfile(@RequestBody UserMyPageVO user) {
//        if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
//            System.out.println("❌ [오류] user_id가 비어 있음. 기본 user5 적용");
//            user.setUser_id("user5");
//        }
//
//        boolean isUpdated = userMyPageService.updateProfile(user);
//        System.out.println("🔄 프로필 업데이트 결과: " + (isUpdated ? "성공" : "실패"));
//
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("updated", isUpdated);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/usermypage/updateStatus")  // 변경됨
//    public ResponseEntity<Map<String, Boolean>> updateStatus(@RequestBody Map<String, Object> requestData) {
//        try {
//            int counselingId = (int) requestData.get("counseling_id");
//            String status = (String) requestData.get("status");
//
//            System.out.println("🔍 [백엔드] 업데이트 요청 - 상담 ID: " + counselingId + ", 상태: " + status);
//
//            boolean success = liveChatService.updateReservationStatus(counselingId, status);
//
//            if (!success) {
//                System.err.println("❌ [백엔드] 상담 상태 업데이트 실패! 상담 ID가 존재하는지 확인 필요.");
//            } else {
//                System.out.println("✅ [백엔드] 상담 상태 업데이트 성공!");
//            }
//
//            Map<String, Boolean> response = new HashMap<>();
//            response.put("success", success);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            System.err.println("🚨 [백엔드] 예외 발생: " + e.getMessage());
//            e.printStackTrace();
//
//            Map<String, Boolean> response = new HashMap<>();
//            response.put("success", false);
//            return ResponseEntity.internalServerError().body(response);
//        }
//    }
//    @PostMapping("/profileupdatewithimg")
//    public ResponseEntity<Map<String, Object>> updateProfileWithImg(
//            @RequestParam("user_id") String userId,
//            @RequestParam("user_nickname") String nickname,
//            @RequestParam(value = "user_password", required = false) String password,
//            @RequestParam(value = "user_img", required = false) MultipartFile profileImg) {
//
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            System.out.println("userId: " + userId);
//            System.out.println("nickname: " + nickname);
//            System.out.println("password: " + password);
//            System.out.println("첨부된 파일: " + (profileImg != null ? profileImg.getOriginalFilename() : "없음"));
//
//            String imgPath = null;
//            if (profileImg != null && !profileImg.isEmpty()) {
//                String uploadDir = "C:/upload/userprofile/";
//                File dir = new File(uploadDir);
//                if (!dir.exists()) {
//                    System.out.println("📁 디렉토리 없음 → 생성 시도");
//                    dir.mkdirs();
//                }
//
//                String filename = userId + "_" + profileImg.getOriginalFilename();
//                File file = new File(uploadDir + filename);
//                profileImg.transferTo(file);
//                imgPath = "/upload/userprofile/" + filename;
//
//                System.out.println("✅ 이미지 저장 완료: " + imgPath);
//            }
//
//            UserMyPageVO user = new UserMyPageVO();
//            user.setUser_id(userId);
//            user.setUser_nickname(nickname);
//            if (password != null && !password.isBlank()) {
//                user.setUser_password(password);
//            }
//            if (imgPath != null) {
//                user.setUser_img(imgPath);
//            }
//
//            boolean updated = userMyPageService.updateProfile(user);
//            response.put("updated", updated);
//            response.put("newImgPath", imgPath);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            System.err.println("🚨 예외 발생: " + e.getMessage());
//            e.printStackTrace();
//            response.put("updated", false);
//            return ResponseEntity.internalServerError().body(response);
//        }
//    }
//
//}



@Controller
public class UserMypageC {

    @Autowired
    private UserMyPageService userMyPageService;

    @Autowired
    private LiveChatService liveChatService;

    // ✅ 세션에서 userId 가져오기 (없으면 기본값 "user5")
    private String getLoginUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj != null) {
            return userIdObj.toString();
        }

        Object userObj = session.getAttribute("loggedInUser");
        if (userObj instanceof UserMyPageVO user) {
            return user.getUser_id();
        }

        return "user5";
    }

    @GetMapping("/usermypage")
    public String usermypage(@RequestParam(value = "user_id", required = false) String user_id,
                             HttpSession session, Model model) {
        System.out.println("🔹 UserMyPageC 실행");

        if (user_id == null || user_id.trim().isEmpty()) {
            user_id = getLoginUserId(session);
        }

        // DB에서 유저 정보 조회
        List<UserMyPageVO> userList = userMyPageService.getUserById(user_id);
        if (!userList.isEmpty()) {
            UserMyPageVO user = userList.get(0);
            model.addAttribute("user", user);
            System.out.println("✅ 유저 정보 로딩: " + user.getUser_id());
        } else {
            System.out.println("❌ 해당 user_id 없음: " + user_id);
        }

        // 상담 상태 최신화
        liveChatService.updateReservationsStatus();

        // 상담 내역, 챗봇 요약 불러오기
        List<UserMyPageVO> reservations = userMyPageService.getUserReservations(user_id);
        List<UserMyPageVO> chatSummaries = userMyPageService.getUserChatBotDetail(user_id);

        model.addAttribute("reservations", reservations);
        model.addAttribute("chats", chatSummaries);

        return "usermypage/usermypage";
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData,
                                                              HttpSession session) {
        String userId = requestData.get("user_id");
        if (userId == null || userId.trim().isEmpty()) {
            userId = getLoginUserId(session);
            System.out.println("❗ user_id 없음 → 세션 또는 기본값 사용: " + userId);
        }

        String password = requestData.get("password");
        boolean isValid = userMyPageService.checkPassword(userId, password);
        System.out.println("🔎 비밀번호 확인 결과: " + (isValid ? "성공" : "실패"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isValid);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profileupdate")
    public ResponseEntity<Map<String, Boolean>> updateProfile(@RequestBody UserMyPageVO user,
                                                              HttpSession session) {
        String userId = user.getUser_id();
        if (userId == null || userId.trim().isEmpty()) {
            userId = getLoginUserId(session);
            user.setUser_id(userId);
            System.out.println("❗ user_id 없음 → 세션 또는 기본값 사용: " + userId);
        }

        boolean isUpdated = userMyPageService.updateProfile(user);
        System.out.println("🔄 프로필 업데이트 결과: " + (isUpdated ? "성공" : "실패"));

        Map<String, Boolean> response = new HashMap<>();
        response.put("updated", isUpdated);
        return ResponseEntity.ok(response);
    }

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
                System.out.println("❗ user_id 없음 → 세션 또는 기본값 사용: " + userId);
            }

            System.out.println("userId: " + userId);
            System.out.println("nickname: " + nickname);
            System.out.println("password: " + password);
            System.out.println("첨부된 파일: " + (profileImg != null ? profileImg.getOriginalFilename() : "없음"));

            String imgPath = null;
            if (profileImg != null && !profileImg.isEmpty()) {
                String uploadDir = "C:/upload/userprofile/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    System.out.println("📁 디렉토리 없음 → 생성 시도");
                    dir.mkdirs();
                }

                String filename = userId + "_" + profileImg.getOriginalFilename();
                File file = new File(uploadDir + filename);
                profileImg.transferTo(file);
                imgPath = "/upload/userprofile/" + filename;

                System.out.println("✅ 이미지 저장 완료: " + imgPath);
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
            System.err.println("🚨 예외 발생: " + e.getMessage());
            e.printStackTrace();
            response.put("updated", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }
}