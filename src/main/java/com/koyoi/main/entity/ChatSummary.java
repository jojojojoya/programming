package com.koyoi.main.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TEST_CHAT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Lob
    @Column(name = "chat_summary", nullable = false)
    private String chatSummary;

    @Column(name = "create_at", insertable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "chat_title", length = 200)
    private String chatTitle;

}
