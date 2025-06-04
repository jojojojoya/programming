package com.jojonezip.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jojonezip.demo.mapper")
public class JojonezipApplication {

	public static void main(String[] args) {
		SpringApplication.run(JojonezipApplication.class, args);
	}

}
