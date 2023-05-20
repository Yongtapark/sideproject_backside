package com.backend.fitta.controller.google;


import com.backend.fitta.dto.google.AccountInfo;
import com.backend.fitta.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

@RestController
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;
    private String loginPage;

    /**
     * 구글 로그인 페이지로 이동합니다
     * @param
     * @throws IOException
     */
    @GetMapping("/auth/sign")
    public ResponseEntity<Urls> googleLogin() throws IOException {
        String scope = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
        String frontRedirectUrl ="https://fitta-git-dev-yiminwook.vercel.app/";
        String encodedScope = URLEncoder.encode(scope, "UTF-8");
        String encodeRedirectUrl = URLEncoder.encode(redirectUri, "UTF-8");
        String encodeFrontRedirectUrl = URLEncoder.encode(frontRedirectUrl, "UTF-8");
        loginPage="https://accounts.google.com/o/oauth2/auth/oauthchooseaccount?client_id="+clientId+"&redirect_uri="+encodeRedirectUrl+"&response_type=code&scope="+encodedScope;

        URI uri = URI.create(loginPage);

        Urls urls = new Urls(loginPage);
        return ResponseEntity.created(uri).build();
    }

    /**
     * 구글에서 보내주는 값을 받아 AccountInfo 객체로 변환합니다.
     * @param code
     * @param registrationId
     * @return
     */
    @GetMapping("/login/oauth2/code/{registrationId}")
    public ResponseEntity<AccountInfo> login(@RequestParam String code, @PathVariable String registrationId) {
        AccountInfo accountInfo = loginService.socialLogin(code, registrationId);
        log.info("userInfo={}",accountInfo);
        return ResponseEntity.ok().body(accountInfo);
    }


}
