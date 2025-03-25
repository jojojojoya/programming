package com.koyoi.main.service;

import com.koyoi.main.entity.ChatSummary;
import com.koyoi.main.repository.ChatSummaryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatService {

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ChatSummaryRepository chatSummaryRepository;

    @Autowired
    public ChatService(ChatSummaryRepository chatSummaryRepository) {
        this.chatSummaryRepository = chatSummaryRepository;
    }

    public String askGpt(String userMessage) {
        Map<String, Object> systemPrompt = Map.of(
                "role", "system",
                "content", "너는 공감 능력이 뛰어난 심리 상담사야. 사용자의 감정을 이해하고 위로해주는 답변을 해야 해. 의학적 조언은 하지 않는다."
        );

        Map<String, Object> userPrompt = Map.of(
                "role", "user",
                "content", userMessage
        );

        return callGptApi(List.of(systemPrompt, userPrompt), 150);
    }

    public String createSummary(List<Map<String, Object>> messages) {
        List<Map<String, Object>> prompt = new ArrayList<>();
        prompt.add(Map.of("role", "system", "content", "다음 대화를 감정 중심으로 500자 이내로 요약해줘. 사용자의 감정을 반영하고 상담 내용을 간결하게 정리해."));
        prompt.addAll(messages);

        return callGptApi(prompt, 300);
    }

    private String callGptApi(List<Map<String, Object>> messages, int maxTokens) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", maxTokens);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        int retryCount = 0;
        int maxRetries = 3;

        while (retryCount < maxRetries) {
            try {
                ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);
                Map<String, Object> message = (Map<String, Object>) ((List<Map<String, Object>>) response.getBody().get("choices")).get(0).get("message");
                return (String) message.get("content");
            } catch (org.springframework.web.client.HttpClientErrorException.TooManyRequests e) {
                retryCount++;
                String retryAfter = e.getResponseHeaders() != null ? e.getResponseHeaders().getFirst("Retry-After") : null;
                int waitTime = retryAfter != null ? Integer.parseInt(retryAfter) : 5;

                System.out.println("🔁 429 에러 발생! " + waitTime + "초 대기 후 재시도 (" + retryCount + "/" + maxRetries + ")");
                try {
                    Thread.sleep(waitTime * 1000L);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("스레드 인터럽트 발생", ie);
                }
            }
        }

        throw new RuntimeException("GPT 요청 실패: 여러 번 재시도 했지만 실패했습니다.");
    }

    public void saveSummary(String userId, String summary) {
        ChatSummary chatSummary = new ChatSummary();
        chatSummary.setUserId(userId);
        chatSummary.setChatSummary(summary);
        chatSummaryRepository.save(chatSummary);
    }
}
