package com.koyoi.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatC {

    @GetMapping
    public String showChatPage() {
        return "chat/chat";  // /WEB-INF/views/chat/chat.jsp
    }

    @GetMapping("/modal")
    public String chatModal() {
        return "chat/chatModal"; // /WEB-INF/views/chat/chatModal.jsp
    }

}
