package com.backend.fitta.config.oauth;


import com.backend.fitta.config.jwt.JwtAuthenticationFilter;
import com.backend.fitta.config.jwt.JwtTokenProvider;
import com.backend.fitta.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity // 스프링 시큐리티 설정 활성화
@RequiredArgsConstructor
//@Profile("!test")
@Configuration//이게 없어서 설정이 불가능했었다.
public class SecurityConfig{
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<RequestMatcher> permitAllRequestMatchers = Arrays.asList(
                new AntPathRequestMatcher("/v1/userinfo"),
                new AntPathRequestMatcher("/members/login"),
                new AntPathRequestMatcher("/oauth2/authorization/google"),
                new AntPathRequestMatcher("/auth/sign"),
                new AntPathRequestMatcher("https://fitta-git-dev-yiminwook.vercel.app/**"),
                new AntPathRequestMatcher("/login/oauth2/code/{registrationId}"),
                new AntPathRequestMatcher("https://c76d-210-219-182-113.ngrok-free.app/**")
        );

        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new OrRequestMatcher(permitAllRequestMatchers)).permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .cors().configurationSource(corsConfigurationSource());

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://fitta-git-dev-yiminwook.vercel.app")); // 여기에 허용하려는 도메인을 추가
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token", "access-control-allow-credentials", "access-control-allow-origin"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
