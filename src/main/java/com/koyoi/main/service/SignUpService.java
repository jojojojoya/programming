package com.koyoi.main.service;

import com.koyoi.main.vo.UserVO;
import com.koyoi.main.mapper.SignUpMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

@Service
public class SignUpService {

    private final SignUpMapper signupMapper;
    private static final Logger log = LoggerFactory.getLogger(SignUpService.class);


    public SignUpService(SignUpMapper signupMapper) {
        this.signupMapper = signupMapper;
    }

    public boolean registerUser(UserVO user, MultipartFile userImg) {

        log.info("회원가입 시도: {}", user.getUserId());

        // 프로필 이미지 업로드 처리
        if (!userImg.isEmpty()) {
            String uploadDir = "static/imgsource/userProfile";
            String imgFileName = System.currentTimeMillis() + "_" + userImg.getOriginalFilename();

            try {
                File saveFile = new File(uploadDir, imgFileName);
                userImg.transferTo(saveFile);
                user.setUserImg(imgFileName);

                log.info("이미지 업로드 성공: {}", imgFileName);

            } catch (IOException e) {
                log.error("이미지 업로드 실패!", e);
                return false;
            }
        } else {
            log.info("이미지 파일 없음");
        }

        // DB 저장
        int result = signupMapper.insertUser(user);
        log.info("회원가입 DB 저장 결과: {}", result);

        return result == 1;
    }

    // id 중복 체크
    public boolean isUserIdDuplicate(String userId) {
        return signupMapper.existsById(userId) > 0;
    }

    // name 중복 체크
    public boolean isUserNameDuplicate(String userName) {
        return signupMapper.existsByName(userName) > 0;
    }

    // nickname 중복 체크
    public boolean isUserNicknameDuplicate(String userNickname) {
        return signupMapper.existsByNickname(userNickname) > 0;
    }

}
