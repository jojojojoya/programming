package com.koyoi.main.controller;

import com.koyoi.main.vo.LiveChatVO;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketC {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketC(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/{sessionId}")
    public void handleMessage(@Payload LiveChatVO message, @DestinationVariable String sessionId) {
        System.out.println("[WebSocket] 메시지 수신: " + message.getMessage());

        String destination = "/topic/chat/" + sessionId;
        messagingTemplate.convertAndSend(destination, message);
    }}

