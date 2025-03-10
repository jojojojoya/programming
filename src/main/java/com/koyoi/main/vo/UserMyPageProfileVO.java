package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Clob;
import java.util.Date;

@Data
public class UserMyPageProfileVO {
    private String id;
    private String password;
    private String nickname;
}