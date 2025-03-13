package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class HabitVO {
    private int habit_id;
    private String user_name;
    private String habit_name;
    private Timestamp created_at;
}
