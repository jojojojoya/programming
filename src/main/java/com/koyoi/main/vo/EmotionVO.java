package com.koyoi.main.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class EmotionVO {
    private int emotion_id;
    private String user_id;
    private int diary_id;
    private int emotion_score;
    private String emotion_emoji;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime recorded_at;


    public String getFormattedRecordedAt() {
        if (recorded_at != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return recorded_at.format(formatter);
        }
        return null;
    }
}
