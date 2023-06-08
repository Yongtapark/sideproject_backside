package com.backend.fitta.controller.login;


import com.backend.fitta.config.security.jwt.JwtTokenProvider;
import com.backend.fitta.config.security.jwt.TokenInfo;
import com.backend.fitta.dto.google.AccountInfo;
import com.backend.fitta.dto.login.UserProfile;
import com.backend.fitta.dto.member.BasicMemberInfo;
import com.backend.fitta.dto.login.LoginRequestDto;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.exception.MemberNotFoundException;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.service.LoginService;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import com.backend.fitta.service.apiService.MemberApiService;
import com.backend.fitta.service.member.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
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
    private final MemberService memberService;
    private final OwnerService ownerService;

    private final JwtTokenProvider jwtTokenProvider;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;
    private String loginPage;



  /*  @Operation(summary = "테스트 userdata")
    @GetMapping("/userdata")
    public ResponseEntity<TestLoginData> login(){
        Owner owner = ownerApiService.findByID(1L);
        return ResponseEntity.ok(new TestLoginData(owner.getId(),owner.getRole(), owner.getName(),owner.getProfileImage()));
    }*/

    /**
     * JWT 로그인
     * @return
     */

    @GetMapping("/userdata")
    public ResponseEntity<?> getMemberInfo(HttpServletRequest request){
        String accessToken = getAccessTokenFromCookies(request);
        if(accessToken == null) {
            return new ResponseEntity<>("Access Token not found in cookies.", HttpStatus.NO_CONTENT);
        }
        /*if(!jwtTokenProvider.validateToken(accessToken)){
            return new ResponseEntity<>("Invalid access token.", HttpStatus.NO_CONTENT);
        }*/
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        String username = authentication.getName();
        log.info("username={}",username);
        try{
            Member member = memberService.findMemberByEmail(username);
            log.info("memberService={}",username);
            return ResponseEntity.ok(new UserProfile(member));
        } catch (MemberNotFoundException e){
            log.info("ownerService={}",username);
            Owner owner = ownerService.findByEmail(username);
            return ResponseEntity.ok(new UserProfile(owner));
        }catch (OwnerNotFoundException e){
            return new ResponseEntity<>("No Data",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }


    private String getAccessTokenFromCookies(HttpServletRequest request) {
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

    @PostMapping("/signin")
    public ResponseEntity<TokenInfo> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        TokenInfo tokenInfo = loginService.login(email, password);
        /*Authorization 으로 값을 보내면 새로고침 시 access token 이 사라진다. 대신 cookie 로 값을 전송한다. */
        ResponseCookie cookie = ResponseCookie.from("accessToken", tokenInfo.getAccessToken())
                //.httpOnly(false)
                //.secure(true)
                .path("/")
                .maxAge(1296000)
                //.domain(".fitta-git-dev-yiminwook.vercel.app")
                // .sameSite("none")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        //http에서 https와 cross origin 환경을 진행하면 setCookie 속성이 적용되지 않는다.
        return ResponseEntity.ok(tokenInfo);
    }
   /* @PostMapping("/signout")
    public ResponseEntity<?> logout(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                log.info("cookie={}",cookies);
                if(cookie.getName().equals("accessToken")){
                    cookie.setMaxAge(0);
                    log.info("isThereCookie={}",cookie.getValue(),cookie.getMaxAge());
                    response.addCookie(cookie);//무조건 쿠키에 추가해야함..
                }
            }
        }
        return new ResponseEntity<>("signOut",HttpStatus.OK);
    }*/

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
    @GetMapping("/login/oauth2/code/{registrationId}")
    public ResponseEntity<AccountInfo> login(@RequestParam String code, @PathVariable String registrationId) throws JsonProcessingException {
        AccountInfo accountInfo = loginService.socialLogin(code, registrationId);
        log.info("userInfo={}",accountInfo);
        return ResponseEntity.ok(accountInfo);
    }
}
