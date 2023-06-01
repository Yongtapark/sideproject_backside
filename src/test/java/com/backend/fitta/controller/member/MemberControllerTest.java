package com.backend.fitta.controller.member;

import com.backend.fitta.config.jwt.TokenInfo;
import com.backend.fitta.dto.member.MemberLoginRequestDto;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        SignUpRequest signUpRequest = new SignUpRequest("email", "password", "password", "name", "address", Gender.FEMALE, "010-1111-1111", LocalDate.of(1995, Month.MAY, 3), "sas", Role.GUEST);
        memberController.saveMember(signUpRequest);
    }

    @Test
    void loginTest(){
        MemberLoginRequestDto memberLoginRequestDto = new MemberLoginRequestDto();
        memberLoginRequestDto.setEmail("email");
        memberLoginRequestDto.setPassword("password");
        TokenInfo login = memberController.login(memberLoginRequestDto);
        log.info("login={}",login);
    }

}