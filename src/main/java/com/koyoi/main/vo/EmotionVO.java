package com.koyoi.main.vo;

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
    private LocalDateTime recorded_at;


    public String getFormattedRecordedAt() {
        if (recorded_at != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return recorded_at.format(formatter);
        }
        return null;
    }
}
