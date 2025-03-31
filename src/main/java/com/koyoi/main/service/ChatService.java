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

    // chat prompt
    public String askGpt(String userMessage) {
        Map<String, Object> systemPrompt = Map.of(
                "role", "system",
                "content", "You are a compassionate Japanese-speaking psychological counselor who talks like a close friend. " +
                        "Regardless of the language used by the user, always reply in Japanese using no more than 2–3 sentences (within 300 characters). " +
                        "Avoid excessive empathy or advice, and aim for a natural, friendly tone. " +
                        "Medical advice is not allowed. Ask questions as needed to better understand the user's intent. " +
                        "Do not output in any language other than Korean."
        );

        Map<String, Object> userPrompt = Map.of(
                "role", "user",
                "content", userMessage
        );

        return callGptApi(List.of(systemPrompt, userPrompt), 300); // 글자수 제한 (사실상 무제한)
    }

    // summary prompt
    public String createSummary(List<Map<String, Object>> messages) {
        List<Map<String, Object>> prompt = new ArrayList<>();
        prompt.add(Map.of("role", "system", "content", "Based on the following conversation, summarize briefly in Korean what the user experienced today, " +
                "how they felt, and how the suggested coping or solutions worked out. " +
                "Reflect their emotions with empathy, and keep the tone warm rather than analytical. " +
                "The response must be in Korean only and within 500 characters."));
        prompt.addAll(messages);

        return callGptApi(prompt, 500); //글자수 500자 이내
    }

    // title prompt
//    public String createTitle(List<Map<String, Object>> messages) {
//        List<Map<String, Object>> prompt = new ArrayList<>();
//        prompt.add(Map.of("role", "system", "content", "Based on the following summary text, " +
//                "create a natural-sounding Korean title that reflects both the content and emotions. " +
//                "The title must be within 30 characters and the response should be in Korean only. " +
//                "Choose words that are accurate yet emotionally resonant."));
//        prompt.addAll(messages);
//
//        return callGptApi(prompt, 30);
//    }

    // 요약 + 제목 생성 후 DB 저장하는 새로운 메서드
    public void saveSummaryWithTitle(String userId, List<Map<String, Object>> messages) {
        // 요약 생성
        String summary = createSummary(messages);

        // summary 기반으로 title 생성
        List<Map<String, Object>> titlePrompt = new ArrayList<>();
        titlePrompt.add(Map.of("role", "system", "content", "Based on the following summary text, " +
                "create a natural-sounding Korean title that reflects both the content and emotions. " +
                "The title must be within 30 characters and the response should be in Korean only. " +
                "Choose words that are accurate yet emotionally resonant."));
        titlePrompt.add(Map.of("role", "user", "content", summary));
        String title = callGptApi(titlePrompt, 30);

        System.out.println(title);
        System.out.println(summary);

        // DB 저장
        ChatSummary chatSummary = new ChatSummary();
        chatSummary.setUserId(userId);
        chatSummary.setChatSummary(summary);
        chatSummary.setChatTitle(title.length() > 30 ? title.substring(0, 30) : title); // 30자 숫자 제한

        chatSummaryRepository.save(chatSummary);
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

//        throw new RuntimeException("GPT 요청 실패: 여러 번 재시도 했지만 실패했습니다.");
        throw new RuntimeException("GPTリクエストに失敗しました：複数回再試行しましたが、失敗しました。");
    }

//    public void saveSummary(String userId, String summary) {
//        ChatSummary chatSummary = new ChatSummary();
//        chatSummary.setUserId(userId);
//        chatSummary.setChatSummary(summary);
//        chatSummaryRepository.save(chatSummary);
//    }

}


