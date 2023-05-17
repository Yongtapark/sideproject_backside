package com.backend.fitta.config.oauth;


import com.backend.fitta.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 스프링 시큐리티 설정 활성화
@RequiredArgsConstructor
@Profile("!test")
@Configuration//이게 없어서 설정이 불가능했었다.

public class SecurityConfig{
    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .requestMatchers("/api/user/login")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();

    }
}
