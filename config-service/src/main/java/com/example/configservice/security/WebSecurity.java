package com.example.configservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
//@EnableWebSecurity
public class WebSecurity {
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf( (csrf) -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/encrypt/**").permitAll()
//                        .requestMatchers("/decrypt/**").denyAll()
//                        .requestMatchers("/**").access(
//                                new WebExpressionAuthorizationManager(
//                                        "hasIpAddress('127.0.0.1') or hasIpAddress('::1')")) // host pc ip address
//                        .anyRequest().authenticated()              // 그 외는 인증 필요
//                )
//                .httpBasic(Customizer.withDefaults())  // ← Basic 인증 추가
//                .headers((headers) -> headers
//                        .frameOptions((frameOptions) -> frameOptions.sameOrigin()));
//
//        return http.build();
//    }
}
