package com.koyoi.main.controller;

import com.koyoi.main.dto.ChatMessageDTO;
import com.koyoi.main.dto.ChatHistoryDTO;
import com.koyoi.main.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatRestC {

    private final ChatService chatService;

    @Autowired
    public ChatRestC(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody ChatMessageDTO chatMessageDTO, HttpSession session) {
        String userMessage = chatMessageDTO.getMessage();
        String gptResponse = chatService.askGpt(userMessage);
        return ResponseEntity.ok(gptResponse);
    }

    // summary + title 저장
    @PostMapping("/summary")
    public ResponseEntity<String> saveSummary(@RequestBody ChatHistoryDTO historyDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ログインが必要です。");
        }

//        String summary = chatService.createSummary(historyDTO.getMessages());
        chatService.saveSummaryWithTitle(userId, historyDTO.getMessages());

        return ResponseEntity.ok("要約とタイトルが保存されました！");
    }
}
