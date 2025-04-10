package com.sj.spring06.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReviewVO {

    private int r_no;
    private String r_title;
    private String r_txt;
    private Date r_date;
}
