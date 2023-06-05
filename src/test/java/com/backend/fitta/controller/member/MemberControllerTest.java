package com.backend.fitta.controller.member;

import com.backend.fitta.config.jwt.TokenInfo;
import com.backend.fitta.dto.member.MemberLoginRequestDto;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Role;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
@Slf4j
@Transactional
class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    @BeforeEach
    void save(){
        SignUpRequest signUpRequest = SignUpRequest
                .builder()
                .email("email123")
                .password("password")
                .passwordConfirm("password")
                .role(Role.USER)
                .build();
        memberController.saveMember(signUpRequest);
    }

    /*@Test
    void loginTest(){
        MemberLoginRequestDto memberLoginRequestDto = new MemberLoginRequestDto();
        memberLoginRequestDto.setEmail("email123");
        memberLoginRequestDto.setPassword("password");

        HttpServletResponse response = new MockHttpServletResponse();
        ResponseEntity<TokenInfo> login = memberController.login(memberLoginRequestDto, response);
        log.info("login={}",login);
    }*/

}