package com.koyoi.main.controller;

import com.koyoi.main.dto.UserDTO;
import com.koyoi.main.service.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpC {
    private final SignUpService signupService;

    public SignUpC(SignUpService signupService) {
        this.signupService = signupService;
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signupPage() {
        return "login/signup"; // signup.jsp
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signupProcess(@ModelAttribute UserDTO userDTO, Model model) {

        // ID 중복 체크
        if (signupService.isUserIdDuplicate(userDTO.getUser_id())) {
            model.addAttribute("signupFailed", "ID is already taken.");
            return "login/signup";
        }

        // 회원가입 처리
        boolean isSuccess = signupService.registerUser(userDTO);

        if (isSuccess) {
            return "redirect:/login";
        } else {
            model.addAttribute("signupFailed", "Signup failed. Try again.");
            return "login/signup";
        }
    }

    // AJAX ID 중복 체크
    @PostMapping("/signup/id-check")
    @ResponseBody
    public boolean checkUserId(@RequestParam("user_id") String userId) {
        return signupService.isUserIdDuplicate(userId);
    }
}
