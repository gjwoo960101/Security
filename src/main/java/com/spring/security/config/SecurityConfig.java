package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //계층권한
    // 권한의 계층을 부여하고, 인가설정시 명시한 권한의 하위권한은 자동필터링 되는것이 가능하다.
    //ex)ROLE_ADMIN > ROLE_USER
    //ex2) .requestMatchers("/admin").hasRole("ADMIN") > USER권한은 필터링되어 접근불가
    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return hierarchy;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/login","/loginProc","/join","/joinProc")
                        .permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        //시큐리티에는 csrf가 기본적으로 동작하며, 동작시에는 csrf에 대한 토큰을 생성해서 보내줘야한다.
        // 그래서 개발시에는 disable을 한것이다.
        //http
                //.csrf((auth) -> auth.disable());

        http
                .sessionManagement((auth)-> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                );

        //세션고정 보호
        http
                .sessionManagement((auth)-> auth
                        .sessionFixation().changeSessionId()
                );




        return http.build();
    }

}
