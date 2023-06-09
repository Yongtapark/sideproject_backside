package com.backend.fitta.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilterV2 extends OncePerRequestFilter {
    /**
     *  HTTP 요청이 왔을때 가장 먼저 값을 확인하는 클레스입니다.
     *  현재 코드는 JwtTokenManager 라는 클래스를 통해 토큰을 검증, 생성 하고있습니다.
     */
    private final JwtTokenManager jwtTokenManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal is ready?");
        String token = getTokenFromRequest(request);
        log.info("doFilterInternal_is_here?={}",token);
        if(StringUtils.hasText(token)&& jwtTokenManager.validateToken(token)){
            log.info("is_here={}",token);
            Authentication authentication = jwtTokenManager.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);

    }

    private String getTokenFromRequest(HttpServletRequest request){
        log.info("getTokenFromRequest_isHere..?");
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("accessToken")){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
