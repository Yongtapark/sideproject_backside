package com.backend.fitta.service.member;

import com.backend.fitta.dto.member.FindByEmailResponse;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void login() {
    }


    @Test
    void save() {
        SignUpRequest signUpRequest = new SignUpRequest("email@naver.com", "1234", "1234", "멤버1",
                "대전", Gender.FEMALE, "01012341234", LocalDate.of(1995, 12, 10), "학생");
        Long savedId = memberService.save(signUpRequest);
    }

    @Test
    void update() {
    }

    @Test
    void findMember() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void findByEmail() {
    }
}