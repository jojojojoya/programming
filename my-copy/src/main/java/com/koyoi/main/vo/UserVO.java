package com.koyoi.main.vo;


import lombok.Data;

@Data
public class UserVO {

    private String userId;         // 사용자 고유 ID
    private int userType;          // 사용자 유형
    private String userName;       // 사용자 이름
    private String userNickname;   // 사용자 닉네임
    private String userEmail;      // 이메일 주소
    private String userPassword;   // 사용자 비밀번호
    private String userImg;        // 프로필 이미지 경로
    private String createdAt;      // 계정 생성일시

}
