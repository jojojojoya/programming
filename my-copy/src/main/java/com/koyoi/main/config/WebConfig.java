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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 기존 설정 유지
        registry.addResourceHandler("/upload/userprofile/**")
                .addResourceLocations("file:C:/SHY/koyoi_upload/");

        registry.addResourceHandler("/imgsource/**")
                .addResourceLocations("classpath:/static/imgsource/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}
