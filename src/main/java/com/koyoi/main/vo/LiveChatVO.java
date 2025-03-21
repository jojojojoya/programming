package com.koyoi.main.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//@Data
//public class LiveChatVO {
//    private Integer session_id;
//    private String user_id;
//    private String counselor_id;
//    private Clob counseling_content;
//    private int counseling_id;
//    private Date counseling_date;
//    private int counseling_time;
//    private String category;
//    private String status;
//    private Date sysdate;
//    private int start_time;
//    private int end_time;
//    private String content;
//
//
//    private List<LiveChatVO> chatLogs;
//
//    private int log_id; // 메시지 고유 id
//    private String sender;  // 메시지 보낸 사용자 ID
//    private String message; // 메시지 내용
//    private LocalDateTime timestamp; // 메시지 전송 시간
//    private String type;  // "USER" or "COUNSELOR"
//    private String user_type;
//}


@Data
public class LiveChatVO {
    private Integer session_id;
    private String user_id;
    private String counselor_id;
    private int counseling_id;
    private java.sql.Date counseling_date;

    private int counseling_time;
    private String category;
    private String status;
    private int start_time;
    private int end_time;

    // ✅ 채팅 로그 관련 필드
    private List<LiveChatVO> chatLogs;
    private int log_id;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
    private String user_type;
}