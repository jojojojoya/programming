package com.koyoi.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 이미지 첨부
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 기존 설정 유지
        registry.addResourceHandler("/upload/userprofile/**")
                .addResourceLocations("file:///C:/upload/userprofile/");

        // ✅ 추가: /imgsource/** 요청을 static 리소스로 매핑
        registry.addResourceHandler("/imgsource/**")
                .addResourceLocations("classpath:/static/imgsource/");

        // ✅ CSS, JS 등 /static/** 요청 처리도 필요 시
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}
