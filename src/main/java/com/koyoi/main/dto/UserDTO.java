package com.koyoi.main.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String user_id;         // 사용자 고유 ID
    private Integer user_type;      // 사용자 유형 (1=상담사 / 2=유저 / 3=관리자)
    private String user_name;       // 이름
    private String user_nickname;   // 닉네임
    private String user_email;      // 이메일
    private String user_password;   // 비밀번호 (암호화된 값)
    private String user_img;        // 프로필 이미지 경로
//    private  created_at;   // 생성일시

}
