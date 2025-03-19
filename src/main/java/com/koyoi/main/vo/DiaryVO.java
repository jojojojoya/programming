package com.koyoi.main.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DiaryVO {
    private int diary_id;
    private String user_id;
    private String title;
    private String diary_content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created_at;

    private String emotion_emoji;

    @JsonProperty("formattedCreatedAt")
    public String getFormattedCreatedAt() {
        if (created_at != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            return created_at.format(formatter);
        }
        return null;
    }
}
