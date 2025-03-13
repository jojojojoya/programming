package com.koyoi.main.vo;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DiaryVO {
    private int diary_id;
    private String user_id;
//    private String diary_title;
    private String diary_content;
    private LocalDateTime created_at;

    private String emotion_emoji;

    public String getFormattedCreatedAt() {
        if (created_at != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return created_at.format(formatter);
        }
        return null;
    }
}
