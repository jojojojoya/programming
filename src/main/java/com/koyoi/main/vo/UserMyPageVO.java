package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.util.Date;

@Data
public class UserMyPageVO {
    private int counseling_id;
    private String user_id;
    private String counselor_id;
    private Date counseling_date;
    private int counseling_time;
    private String counseling_category;
    private String counseling_status;
    private Date counseling_created_at;
    private Clob counseling_content;




}
