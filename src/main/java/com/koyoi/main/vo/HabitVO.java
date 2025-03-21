package com.koyoi.main.vo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class HabitVO {
    private Long habit_id;
    private String user_id;
    private String habit_name;
    private Timestamp created_at;
}
