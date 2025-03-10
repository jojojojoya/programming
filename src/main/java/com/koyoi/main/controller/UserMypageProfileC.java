package com.koyoi.main.controller;

import com.koyoi.main.service.UserMyPageProfileService;
import com.koyoi.main.vo.UserMyPageProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/profile")
public class UserMypageProfileC {


    @Autowired
    private UserMyPageProfileService userMyPageProfileService;

    @PostMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UserMyPageProfileVO userMyPageProfileVO) {
        boolean isUpdated = UserMyPageProfileService.updateProfile(userMyPageProfileVO);
        if (isUpdated) {
            return ResponseEntity.ok(Collections.singletonMap("message", "프로필 수정 완료!"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "수정 실패"));
        }
    }
}