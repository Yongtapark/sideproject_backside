package com.backend.fitta.config.aouth;


import com.backend.fitta.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 스프링 시큐리티 설정 활성화
@RequiredArgsConstructor
public class SecurityConfig{
    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //??
                .and()
                .authorizeRequests()//url별 권한 관리를 설정하는 옵션의 시작점. authorizeRequests 가 선언되어야만 requestMatchers 를 사용가능
              /*  requestMatchers - 권한 관리 대상을 지정하는 옵션, url,http 메소드별로 관리가 가능
                "/"등 지정된 url 들은 permitAll()옵션을  통해 전체 열람 권한을 줌*/
                .requestMatchers("/","/css/**","/images/**","/js/**").permitAll()
                //"/api/v1/**" 주소를 가진 API 는 USER 권한을 가진 사람만 가능하도록 설정
                .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                /*anyRequest = 설정된 값들 이외 나머지 url 들을 나타냄
                여기서는 authenticated()을 추가하여 나머지 url들은 모두 인증된 사용자에게만 허용하게 설정
                인증된 사용자 즉, 로그인한 사용자들을 의미*/
                .anyRequest().authenticated()
                .and()
                //logout().logoutSuccessUrl("/) - 로그아웃 기능에 설정 진입점, 로그아웃 성공 시 / 로 이동
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login() // OAuth 2 로그인 기능에 대한 설정 진입점
                .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
               /*  userService - 소셜 로그인 성소 후속 조치를 진행할 UserService 인터페이스의 구현체를등록
               리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
                */
                .userService(customOAuth2UserService);
        return http.build();

    }
}
