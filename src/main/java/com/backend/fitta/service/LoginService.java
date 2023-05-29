package com.backend.fitta.service;

import com.backend.fitta.dto.google.AccountInfo;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.*;
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

    String resourceUri = "https://www.googleapis.com/oauth2/v3/userinfo";


    public AccountInfo socialLogin(String code, String registrationId) {
        String accessToken = getAccessToken(code, registrationId);
        JsonNode userResource = getUserResource(accessToken, registrationId);

        String picture = userResource.get("picture").asText();
        String email = userResource.get("email").textValue();
        String name = userResource.get("name").textValue();

        AccountInfo accountInfo = new AccountInfo(email, name, picture);
        return accountInfo;
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
            return accessTokenNode.get("access_token").asText();


    }

    private JsonNode getUserResource(String accessToken,String registrationId){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer " + accessToken);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return restTemplate.exchange(resourceUri,HttpMethod.GET,httpEntity, JsonNode.class).getBody();
    }

}