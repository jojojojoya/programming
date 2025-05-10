package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CounselorMyPageVO {
    private String user_id; // 유저 ID
    private int user_type; // 유저 타입
    private String user_name; // 유저 이름
    private String user_nickname; // 유저 닉네임
    private String user_email; // 유저 메일
    private String user_password; // 유저 비밀번호
    private String user_img; // 유저 사진
    private LocalDateTime create_time;
    private int session_id;

    private Date start_time;
    private Date end_time;
    private String status;
    private Clob counseling_content;
    private String counselor_id;
    private int counseling_id;
    private Date counseling_date;
    private int counseling_time;
    private String category;


    private int chat_id; // 유저 챗봇 상담 id
    private String chat_summary;
    private String chat_title; // 유저 챗봇 상담 내역 요약








}
