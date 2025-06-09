package com.koyoi.main.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class LiveChatVO {

    // 라이브 상담 예약 관련 필드
    private Integer session_id; // 상담 채팅방별 고유 ID
    private String user_id;
    private String counselor_id;
    private int counseling_id;
    private java.sql.Date counseling_date;

    private int counseling_time;
    private String category;
    private String status;
    private int start_time;
    private int end_time;

    // 라이브 상담 채팅 기록 관련 필드
    private List<LiveChatVO> chatLogs;
    private int log_id;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
    private String user_type;
}