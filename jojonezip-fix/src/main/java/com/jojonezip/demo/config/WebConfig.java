package com.jojonezip.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 📁 정적 리소스(static/img)는 그대로 유지
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");

        registry.addResourceHandler("/img/product/**")
        .addResourceLocations("file:///C:/jojonezip/upload/product/");

        // 📁 업로드된 리뷰 이미지만 따로 외부 디렉토리로
        registry.addResourceHandler("/img/review/**")
                .addResourceLocations("file:///C:/jojonezip/upload/review/");
    }}