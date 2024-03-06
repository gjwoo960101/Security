package com.spring.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        return http
                //csrf 공격에 대한 옵션 off
                .csrf(AbstractHttpConfigurer::disable)
                // 특정 URL에 대한 권한 설정
                .authorizeHttpRequests((authorizeRequests) -> {
                    //아직 필터링할 url이 없어서 전부 허용
                    authorizeRequests.requestMatchers("/**").permitAll();
                }).build();
    }

}
