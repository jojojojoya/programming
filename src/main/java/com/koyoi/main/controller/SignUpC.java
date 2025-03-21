package com.koyoi.main.controller;

import com.koyoi.main.vo.UserVO;
import com.koyoi.main.service.SignUpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Autowired
    private SignUpService signUpService;


    @PostMapping("/signup")
    public String signup(@RequestParam("user_id") String userId,
                         @RequestParam("user_pw") String userPw,
                         @RequestParam("user_name") String userName,
                         @RequestParam("user_nickname") String userNickname,
                         @RequestParam("user_email") String userEmail,
                         @RequestParam("user_img") MultipartFile userImg,
                         HttpSession session) {

        Logger log = LoggerFactory.getLogger(this.getClass());

        // 진입 시점 로그
        log.info("[SignUpC] 회원가입 요청 시작");
        log.debug("user_id={}, user_name={}, user_nickname={}, user_email={}", userId, userName, userNickname, userEmail);

        // UserVO 생성 후 확인
        UserVO user = new UserVO();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserNickname(userNickname);
        user.setUserEmail(userEmail);
        user.setUserPassword(userPw); // 암호화는 서비스에서 처리
        user.setUserType(2); // 일반 유저 기본값

        System.out.println("logtest: " + userId);

        log.debug("[SignUpC] UserVO 생성 완료: {}", user);

        boolean result = false;

        try {
            result = signUpService.registerUser(user, userImg);

            if (result) {
                log.info("[SignUpC] 회원가입 성공 - userId: {}", userId);

                // 세션 처리 로그
                session.setAttribute("loginId", userId);
                log.debug("[SignUpC] 세션 저장 완료 - loginId: {}", userId);

                return "redirect:/login";
            } else {
                log.warn("[SignUpC] 회원가입 실패 - userId: {}", userId);
                return "redirect:/signup?error=fail"; // 실패 시 에러 메시지 전달
            }

        } catch (Exception e) {
            log.error("[SignUpC] 회원가입 중 예외 발생", e);
            return "redirect:/signup?error=exception";
        } finally {
            log.info("[SignUpC] 회원가입 요청 종료 - userId: {}", userId);
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
