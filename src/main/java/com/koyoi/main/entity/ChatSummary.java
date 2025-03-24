package com.koyoi.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Repeatable;
import java.time.LocalDateTime;

@Entity
@Table(name = "MAIN_CHAT")
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

    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();
}
