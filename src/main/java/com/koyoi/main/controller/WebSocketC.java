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

    @MessageMapping("/chat")  // 프론트에서 보낸 메시지 → "/app/chat"
    public void handleChatMessage(@Payload LiveChatVO message) {
        System.out.println("[WebSocketController] 받은 메시지: " + message);

        String destination = "/topic/chat/" + message.getSession_id();
        messagingTemplate.convertAndSend(destination, message);
    }
}

