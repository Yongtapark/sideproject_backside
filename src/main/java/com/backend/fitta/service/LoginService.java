package com.backend.fitta.service;

import com.backend.fitta.config.security.jwt.JwtTokenManager;
import com.backend.fitta.config.security.jwt.TokenInfo;
import com.backend.fitta.dto.google.AccountInfo;
import com.backend.fitta.dto.login.UserProfile;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final ConfigurableEnvironment environment;
    private final RestTemplate restTemplate=new RestTemplate();

    private final JwtTokenManager jwtTokenManager;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    String resourceUri = "https://www.googleapis.com/oauth2/v3/userinfo";


    /**
     * JWT
     */

    public TokenInfo login(String email, String password){
        //1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이 때, authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(email,password);
        log.info("authenticationToken={}",authenticationToken);
        /*
        ex : authenticationToken=
        UsernamePasswordAuthenticationToken
        [
        Principal=email@email.com,
        Credentials=[PROTECTED],
        Authenticated=false,
        Details=null,
        Granted Authorities=[]
        ]
        */

        //2. 실제 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("authentication={}",authentication);
        /*ex : authentication=
        UsernamePasswordAuthenticationToken [
        Principal=
        org.springframework.security.core.userdetails.User
        [
        Username=
        email@email.com,
        Password=[PROTECTED],
        Enabled=true,
        AccountNonExpired=true,
        credentialsNonExpired=true,
        AccountNonLocked=true,
        Granted Authorities=[ROLE_USER]
        ],
        Credentials=[PROTECTED],
        Authenticated=true,
        Details=null,
        Granted Authorities=[ROLE_USER]
        ]
        */

        //3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenManager.generateToken(authentication);
        log.info("tokenInfo={}",tokenInfo);
        return tokenInfo;
        /*
        ex : tokenInfo=TokenInfo(
        grantType=Bearer,
        accessToken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjg1NjkwMTk0fQ._rU-PWMo2HF-aisInq95C7DIwEsvtyrWq3olckGW7aY,
        refreshToken=eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODU2OTAxOTR9.CevfTfOuDWooA_LrllBhk8vQPxa-lP2QHANLEMP9FMY
        )
        */
    }



    /**
     * OAUTH 2.0
     */
    public UserProfile socialLogin(String code, String registrationId) {
        String accessToken = getAccessToken(code, registrationId);
        JsonNode userResource = getUserResource(accessToken, registrationId);
        log.info("2.accessToken={}",accessToken);
        log.info("2.userResource={}",userResource);

        String picture = userResource.get("picture").asText();
        String email = userResource.get("email").textValue();
        String name = userResource.get("name").textValue();

        UserProfile userProfile = new UserProfile(email, name, picture);
        return userProfile;
    }


    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = environment.getProperty("spring.security.oauth2.client.registration." + registrationId + ".client-id");
        String clientSecret = environment.getProperty("spring.security.oauth2.client.registration." + registrationId + ".client-secret");
        String redirectUri = environment.getProperty("spring.security.oauth2.client.registration." + registrationId + ".redirect-uri");
        String tokenUri = environment.getProperty("spring.security.oauth2.client.provider." + registrationId + ".token-uri");


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, httpHeaders);

            ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
            JsonNode accessTokenNode = responseNode.getBody();
        String token = accessTokenNode.get("access_token").asText();
        return token;


    }

    private JsonNode getUserResource(String accessToken,String registrationId){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer " + accessToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return restTemplate.exchange(resourceUri,HttpMethod.GET,httpEntity, JsonNode.class).getBody();
    }

}