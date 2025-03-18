package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class LiveChatVO {
    private int session_id;
    private String user_id;
    private String counselor_id;
    private Clob counseling_content;
    private int counseling_id;
    private Date counseling_date;
    private int counseling_time;
    private String category;
    private String status;
    private Date sysdate;
    private Date start_time;
    private Date end_time;

    private List<LiveChatVO> chatLogs;

    private String sender;  // 메시지 보낸 사용자 ID
    private String content; // 채팅 메시지
    private LocalDateTime timestamp; // 메시지 전송 시간
    private String type;  // "USER" or "COUNSELOR"
}