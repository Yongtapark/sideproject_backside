package com.backend.fitta.controller.login;


import com.backend.fitta.controller.owner.OwnerController;
import com.backend.fitta.dto.google.AccountInfo;
import com.backend.fitta.dto.login.TestLoginData;
import com.backend.fitta.dto.owner.OwnerProfileInfo;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.service.LoginService;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "인증",description ="OAuth2.0을 이용한 google 로그인 로직입니다(미구현)" )
public class LoginController {
    private final LoginService loginService;
    private final OwnerApiService ownerApiService;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;
    private String loginPage;



    @Operation(summary = "테스트 userdata")
    @GetMapping("/userdata")
    public ResponseEntity<TestLoginData> login(){
        Owner owner = ownerApiService.findByID(1L);
        return ResponseEntity.ok(new TestLoginData(owner.getId(),owner.getRole(), owner.getName(),owner.getProfileImage()));
    }

    /**
     * 구글 로그인 페이지로 이동합니다
     * @param
     * @throws IOException
     */
     @Operation(summary = "로그인 url 리다이렉션 메서드",description = "현재 프론트와 조정중 입니다.")
     @GetMapping("/auth/sign")
    public ResponseEntity<Urls> googleLogin() throws IOException {
        String scope = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
        String frontRedirectUrl ="https://fitta-git-dev-yiminwook.vercel.app/";
        String encodedScope = URLEncoder.encode(scope, "UTF-8");
        String encodeRedirectUrl = URLEncoder.encode(redirectUri, "UTF-8");
        loginPage="https://accounts.google.com/o/oauth2/auth/oauthchooseaccount?client_id="+clientId+"&redirect_uri="+encodeRedirectUrl+"&response_type=code&scope="+encodedScope;

        Urls urls = new Urls(loginPage);
         ResponseEntity.ok().body(urls);
        return ResponseEntity.ok(urls);
    }

    /**
     * 구글에서 보내주는 값을 받아 AccountInfo 객체로 변환합니다.
     * @param code
     * @param registrationId
     * @return
     */

    @Operation(summary = "구글에서 리데이렉션 해서 보내주는 값을 받는 메서드",description = "사용자의 이메일, 이름, 사진을 받아옵니다")
    @Cacheable(value = "accountInfo",key = "#code")
    @GetMapping("/login/oauth2/code/{registrationId}")
    @Scheduled(fixedRateString = "6000")
    public ResponseEntity<AccountInfo> login(@RequestParam String code, @PathVariable String registrationId) throws JsonProcessingException {
        AccountInfo accountInfo = loginService.socialLogin(code, registrationId);
        log.info("userInfo={}",accountInfo);
        return ResponseEntity.ok(accountInfo);
    }
}
