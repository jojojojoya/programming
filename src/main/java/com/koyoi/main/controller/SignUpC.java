package com.koyoi.main.controller;

import com.koyoi.main.dto.UserDTO;
import com.koyoi.main.service.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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



    // user 프로필 등록
    @PostMapping("/signup")
    public String signup(
            @RequestParam("user_id") String userId,
            @RequestParam("user_pw") String userPw,
            @RequestParam("user_img") MultipartFile userImg
    ) {
        try {
            // 파일명 중복 방지용 - UUID
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "_" + userImg.getOriginalFilename();

            // 2. 파일 저장 경로 (ex: 서버 로컬 디렉토리)
            String uploadDir = "static/imgsource/userProfile";
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs(); // 폴더 없으면 생성
            }

            // 실제 파일 저장
            File file = new File(uploadPath, fileName);
            userImg.transferTo(file);

            // 4. DB에 저장될 경로
            String dbFilePath = "static/imgsource/userProfile/" + fileName;

            // DB에 user 정보 + 이미지 경로 저장
//            SignUpService.registerUser(userId, userPw, dbFilePath);

        } catch (IOException e) {
            e.printStackTrace();
            // 에러 처리
        }

        return "redirect:/login";
    }



}
