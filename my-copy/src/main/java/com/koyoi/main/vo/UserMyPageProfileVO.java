package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserMyPageProfileVO {
        private String user_id;
        private int user_type;
        private String user_name;
        private String user_email;
        private String user_password;
        private String user_img;
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


    }