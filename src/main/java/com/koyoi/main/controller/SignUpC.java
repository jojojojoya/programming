package com.koyoi.main.controller;

import com.koyoi.main.dto.UserDTO;
import com.koyoi.main.service.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

        boolean isSuccess = signupService.registerUser(userDTO);

        if (isSuccess) {
            return "redirect:/login";
        } else {
            model.addAttribute("signupFailed", "Signup failed. Try again.");
            return "login/signup";
        }
    }

    // 아이디, 닉네임, 이름 중복 체크
    @GetMapping("/check")
    @ResponseBody
    public Map<String, Boolean> checkDuplicate(
            @RequestParam String type,
            @RequestParam String value
    ) {
        // debug
        System.out.println("[CHECK 요청 수신]");
        System.out.println("type: " + type);
        System.out.println("value: " + value);

        boolean exists = false;

        switch (type) {
            case "id":
                exists = signupService.isUserIdDuplicate(value);
                System.out.println("[SignUpC/checkDuplicate] ID 중복 여부: " + exists);
                break;
            case "name":
                exists = signupService.isUserNameDuplicate(value);
                System.out.println("[SignUpC/checkDuplicate] NAME 중복 여부: " + exists);
                break;
            case "nickname":
                exists = signupService.isUserNicknameDuplicate(value);
                System.out.println("[SignUpC/checkDuplicate] NICKNAME 중복 여부: " + exists);
                break;
            default:
                System.out.println("[SignUpC/checkDuplicate] 잘못된 type 파라미터입니다.");
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        System.out.println("[SignUpC/checkDuplicate] 최종 반환 데이터: " + response);

        return response;
    }



}
