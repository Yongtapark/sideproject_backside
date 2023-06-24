package com.backend.fitta.controller.member;

import com.backend.fitta.dto.member.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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