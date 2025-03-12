package com.koyoi.main.controller;

import com.koyoi.main.service.UserMyPageProfileService;
import com.koyoi.main.service.UserMyPageService;
import com.koyoi.main.vo.UserMyPageProfileVO;
import com.koyoi.main.vo.UserMyPageVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
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
            List<UserMyPageVO> users;
            UserMyPageVO loggedInUser = (UserMyPageVO) session.getAttribute("loggedInUser");

            if (loggedInUser != null) {
                model.addAttribute("user", loggedInUser);
            } else if (user_id != null) {
                users = userMyPageService.getUserById(user_id);
                if (!users.isEmpty()) {
                    model.addAttribute("user", users.get(0));
                } else {
                    model.addAttribute("error", "해당 사용자를 찾을 수 없습니다.");
                    return "redirect:/finalindex";
                }
            } else {
                users = userMyPageService.getUserById("user5");
                if (!users.isEmpty()) {
                    model.addAttribute("user", users.get(0));
                } else {
                    model.addAttribute("error", "기본 사용자 정보가 없습니다.");
                    return "redirect:/finalindex";
                }
            }
            return "/usermypage/usermypage";
        }

        @PostMapping("/checkPassword")
        public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> requestData) {
            String userId = requestData.get("user_id");
            String password = requestData.get("password");

            boolean isValid = userMyPageService.checkPassword(userId, password);

            Map<String, Boolean> response = new HashMap<>();
            response.put("valid", isValid);
            return ResponseEntity.ok(response);
        }

//        @PostMapping("/updatePassword")
//        public ResponseEntity<Map<String, Boolean>> updatePassword(@RequestBody Map<String, String> requestData) {
//            String userId = requestData.get("user_id");
//            String newPassword = requestData.get("newPassword");
//
//            boolean isUpdated = userMyPageService.updatePassword(userId, newPassword);
//
//            Map<String, Boolean> response = new HashMap<>();
//            response.put("updated", isUpdated);
//            return ResponseEntity.ok(response);
//        }

    @PostMapping("/profileupdate")
    public String updateProfile(UserMyPageVO user, Model model) {
        boolean isUpdated = userMyPageService.updateProfile(user);

        if (isUpdated) {
            return "redirect:/usermypage?user_id=" + user.getUser_id();
        } else {
            model.addAttribute("error", "프로필 수정 실패!");
            return "usermypage/usermypage";
        }
    }


@GetMapping("/usermypagechatbot")
public String usermypagechatbot() {
//            Object mode = session.getAttribute("mode"); // 유저가 로그인할때 세션값
//            model.addAttribute("page", "usermypage/usermypage.jsp");
    return "/usermypage/usermypagechatbot";

}

@GetMapping("/livechat")
public String livechat() {
//            Object mode = session.getAttribute("mode"); // 유저가 로그인할때 세션값
//            model.addAttribute("page", "usermypage/usermypage.jsp");
    return "/livechat/livechat";

}}