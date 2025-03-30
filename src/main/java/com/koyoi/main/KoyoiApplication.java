package com.koyoi.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.koyoi.main.mapper")
@SpringBootApplication
@EnableScheduling  // 스케줄링 기능 활성화
public class KoyoiApplication {
    public static void main(String[] args) {
        SpringApplication.run(KoyoiApplication.class, args);
    }
}
