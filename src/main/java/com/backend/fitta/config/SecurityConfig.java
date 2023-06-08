package com.backend.fitta.config;


import com.backend.fitta.config.security.jwt.JwtAuthenticationFilter;
import com.backend.fitta.config.security.jwt.JwtTokenProvider;
import com.backend.fitta.config.security.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
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
@Configuration//이게 없어서 설정이 불가능했었다.
@Profile("!test")
public class SecurityConfig{
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<RequestMatcher> permitAllRequestMatchers = Arrays.asList(
                //Api
                new AntPathRequestMatcher("/v1/userinfo"),
                new AntPathRequestMatcher("/members/login"),
                new AntPathRequestMatcher("/oauth2/authorization/google"),
                new AntPathRequestMatcher("/auth/sign"),
                new AntPathRequestMatcher("/login/oauth2/code/{registrationId}"),
                //swagger
                new AntPathRequestMatcher("/swagger-ui/**"),//Swagger 페이지
                new AntPathRequestMatcher("/v3/api-docs/**"),//Swagger api 들을 불러오는 주소

                //FrontPage
                new AntPathRequestMatcher("https://fitta-git-dev-yiminwook.vercel.app/**"),
                new AntPathRequestMatcher("http://localhost:3000/**"),
                //HostingDomain
                new AntPathRequestMatcher("https://8b79-210-219-182-113.ngrok-free.app/**")
        );

        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new OrRequestMatcher(permitAllRequestMatchers)).permitAll()
                                .anyRequest().permitAll()
                )
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(200))
                .deleteCookies("accessToken")
                .and()
                .cors().configurationSource(corsConfigurationSource());

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://fitta-git-dev-yiminwook.vercel.app","http://localhost:3000")); // 여기에 허용하려는 도메인을 추가
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
