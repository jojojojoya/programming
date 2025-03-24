package com.koyoi.main.controller;

import com.koyoi.main.dto.ChatMessageDTO;
import com.koyoi.main.dto.ChatHistoryDTO;
import com.koyoi.main.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST API 전용 컨트롤러 → @RestController는 JSON 형태로 반환되도록 자동 설정됨
@RestController
@RequestMapping("/api/chat") // API 엔드포인트 기본 URL은 /api/chat으로 시작함
public class ChatRestC {

    private final ChatService chatService; // 서비스 레이어 주입받음

    @Autowired
    public ChatRestC(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 실시간 채팅 메시지 전송 처리
     * 클라이언트가 채팅 메시지를 보내면 GPT 응답을 받아서 반환함
     */
    @PostMapping // POST 요청 처리 → http://localhost:8080/api/chat
    public ResponseEntity<String> chat(@RequestBody ChatMessageDTO chatMessageDTO, HttpSession session) {
        String userMessage = chatMessageDTO.getMessage(); // 클라이언트가 보낸 메시지 추출
        String gptResponse = chatService.askGpt(userMessage); // GPT에 메시지 전달하고 응답 받기
        return ResponseEntity.ok(gptResponse); // 클라이언트에게 GPT 응답을 반환 (200 OK)
    }

    /**
     * 상담 종료 후 대화 요약 저장
     * 오늘의 대화를 요약해서 DB에 저장하는 처리
     */
    @PostMapping("/summary") // POST 요청 처리 → http://localhost:8080/api/chat/summary
    public ResponseEntity<String> saveSummary(@RequestBody ChatHistoryDTO historyDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId"); // 세션에서 유저 정보 가져오기

        // 로그인 여부 확인 (비로그인 상태면 401 Unauthorized 응답)
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        // 대화 내용(messages)을 요약 생성
        String summary = chatService.createSummary(historyDTO.getMessages());

        // 생성된 요약을 DB에 저장
        chatService.saveSummary(userId, summary);

        return ResponseEntity.ok("요약이 저장되었습니다!"); // 저장 완료 응답
    }
}
