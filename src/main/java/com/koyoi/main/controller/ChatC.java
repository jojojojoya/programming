package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// chat view 전용 컨트롤러
@Controller
@RequestMapping("/chat")
public class ChatC {

    @GetMapping
    public String showChatPage() {
        return "chat/chat";  // chat.jsp
    }
}
