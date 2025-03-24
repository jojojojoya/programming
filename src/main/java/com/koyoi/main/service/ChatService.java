package com.koyoi.main.service;

import com.koyoi.main.entity.ChatSummary;
import com.koyoi.main.repository.ChatSummaryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    // ✅ OpenAI API 호출에 사용될 URL, API 키 값을 주입받음
    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    // ✅ REST 통신을 위한 RestTemplate 객체 생성
    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ DB에 요약을 저장하기 위한 Repository 주입
    private final ChatSummaryRepository chatSummaryRepository;

    @Autowired
    public ChatService(ChatSummaryRepository chatSummaryRepository) {
        this.chatSummaryRepository = chatSummaryRepository;
    }

    /**
     * ✅ 실시간 GPT 채팅 처리 메서드
     * @param userMessage - 사용자의 입력 메시지
     * @return GPT 응답 문자열
     */
    public String askGpt(String userMessage) {
        // 시스템 역할과 사용자 입력 메시지 설정
        Map<String, Object> systemPrompt = Map.of(
                "role", "system",
                "content", "너는 공감 능력이 뛰어난 심리 상담사야. 사용자의 감정을 이해하고 위로해주는 답변을 해야 해. 의학적 조언은 하지 않는다."
        );

        Map<String, Object> userPrompt = Map.of(
                "role", "user",
                "content", userMessage
        );

        // GPT API 요청 바디 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(systemPrompt, userPrompt));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 150);

        // ✅ 헤더 설정 (Authorization에 반드시 Bearer 추가)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // 요청 엔티티 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // GPT API 호출 및 응답 수신
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);

        // 응답에서 choices 추출 후 메시지 반환
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseEntity.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

        return (String) message.get("content");
    }

    /**
     * ✅ 상담 종료 후 대화 내용 요약 메서드
     * @param messages - 전체 대화 기록
     * @return GPT 요약 결과 문자열
     */
    public String createSummary(List<Map<String, Object>> messages) {
        List<Map<String, Object>> gptMessages = new ArrayList<>();

        // 시스템 프롬프트 추가 (요약 명령)
        gptMessages.add(Map.of(
                "role", "system",
                "content", "다음 대화를 감정 중심으로 500자 이내로 요약해줘. 사용자의 감정을 반영하고 상담 내용을 간결하게 정리해."
        ));

        // 유저 대화 메시지 추가
        gptMessages.addAll(messages);

        // GPT API 요청 바디 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", gptMessages);
        requestBody.put("temperature", 0.5);
        requestBody.put("max_tokens", 300);

        // ✅ 헤더 설정 (Authorization에 반드시 Bearer 추가)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // 요청 엔티티 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // GPT API 호출 및 응답 수신
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);

        // 응답에서 choices 추출 후 요약 메시지 반환
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseEntity.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

        return (String) message.get("content");
    }

    /**
     * ✅ 생성된 상담 요약을 DB에 저장하는 메서드
     * @param userId - 사용자 아이디
     * @param summary - 요약 문자열
     */
    public void saveSummary(String userId, String summary) {
        ChatSummary chatSummary = new ChatSummary();
        chatSummary.setUserId(userId);
        chatSummary.setChatSummary(summary);
        chatSummaryRepository.save(chatSummary);
    }
}
