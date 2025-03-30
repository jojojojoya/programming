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

            // 프로젝트 경로 기준으로 절대경로 만들기
            String projectPath = System.getProperty("user.dir"); // 현재 프로젝트 루트 경로
            String uploadDirPath = projectPath + "/src/main/resources/static/imgsource/userProfile";

            File uploadDir = new File(uploadDirPath);

            // 폴더가 존재하지 않으면 생성
            if (!uploadDir.exists()) {
                boolean dirCreated = uploadDir.mkdirs();
                if (!dirCreated) {
                    log.error("디렉터리 생성 실패! 경로: {}", uploadDirPath);
                    return false;
                }
            }

            // ✅ 파일명 생성 (타임스탬프 + 원본 파일명)
            String imgFileName = System.currentTimeMillis() + "_" + userImg.getOriginalFilename();

            try {
                // ✅ 실제 저장할 파일 경로
                File saveFile = new File(uploadDir, imgFileName);

                // ✅ 파일 저장 처리
                userImg.transferTo(saveFile);

                // ✅ DB에는 상대경로로 저장해야 웹에서 접근 가능!
//                String relativePath = "/imgsource/userProfile/" + imgFileName;
//                user.setUserImg(relativePath);
//
//                log.info("이미지 업로드 성공: {}", relativePath);
                String webPath = "/imgsource/userProfile/" + imgFileName;
                user.setUserImg(webPath);

                log.info("이미지 저장 성공: {}", webPath);


            } catch (IOException e) {
                log.error("이미지 업로드 실패!", e);
                return false;
            }

        } else {
            log.info("이미지 파일 없음");
        }

        // DB 저장
        System.out.println("-----여기 확인-----");
        System.out.println(user);
        int result = signupMapper.insertUser(user);
        log.info("회원가입 DB 저장 결과: {}", result);

        return result == 1;
    }

    // id 중복 체크
    public boolean isUserIdDuplicate(String userId) {
        return signupMapper.existsById(userId) > 0;
    }

    // email 중복 체크
    public boolean isUserEMailDuplicate(String userEmail) {
        return signupMapper.existsByName(userEmail) > 0;
    }

    // nickname 중복 체크
    public boolean isUserNicknameDuplicate(String userNickname) {
        return signupMapper.existsByNickname(userNickname) > 0;
    }

}
