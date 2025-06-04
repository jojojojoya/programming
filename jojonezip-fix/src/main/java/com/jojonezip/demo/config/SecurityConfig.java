//package com.jojonezip.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//	// Security 설정 파일 예시
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http
//	        .csrf().disable()
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/", "/joinpage", "/css/**").permitAll()
//	            .anyRequest().authenticated()
//	        )
//	        .formLogin(form -> form
//	            .loginPage("/loginpage")  // 로그인 폼
//	            .loginProcessingUrl("/login") // 로그인 요청 처리 URL
//	            .defaultSuccessUrl("/", true)
//	            .permitAll()
//	        )
//	        .logout(logout -> logout
//	            .logoutUrl("/logout")
//	            .logoutSuccessUrl("/loginpage")
//	        );
//	    return http.build();
//	}
//}