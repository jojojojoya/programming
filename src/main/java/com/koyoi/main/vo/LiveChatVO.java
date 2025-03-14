package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LiveChatVO {
    private String session_id;
    private String user_id;
    private String counselor_id;
    private Clob counseling_content;
    private int counseling_id;
    private java.sql.Date counseling_date;
    private int counseling_time;
    private String category;
    private String status;
    private Date sysdate;
    private Date start_time;
    private Date end_time;

}