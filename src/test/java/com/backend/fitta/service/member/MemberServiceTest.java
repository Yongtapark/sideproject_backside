package com.backend.fitta.service.member;

import com.backend.fitta.dto.member.FindByEmailResponse;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.dto.member.UpdateMemberRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        SignUpRequest signUpRequest = new SignUpRequest("firstMail@naver.com", "1234", "1234", "초기멤버",
                "서울", Gender.MALE, "01012341234", LocalDate.of(1999, 5, 10), "학생");
        memberService.save(signUpRequest);
    }

    @Test
    void login() {
    }


    @Test
    void save() {
        SignUpRequest signUpRequest = new SignUpRequest("email@naver.com", "1234", "1234", "멤버1",
                "대전", Gender.FEMALE, "01012341234", LocalDate.of(1995, 12, 10), "학생");
        memberService.save(signUpRequest);
        Member findMember = memberRepository.findByEmail(signUpRequest.getEmail()).orElseThrow();
        assertThat(signUpRequest.getEmail()).isEqualTo(findMember.getEmail());
        assertThat(signUpRequest.getPassword()).isEqualTo(findMember.getPassword());
        assertThat(signUpRequest.getName()).isEqualTo(findMember.getName());
        assertThat(signUpRequest.getOccupation()).isEqualTo(findMember.getOccupation());
        assertThat(signUpRequest.getBirthday()).isEqualTo(findMember.getBirthday());
        assertThat(signUpRequest.getAddress()).isEqualTo(findMember.getAddress());
        assertThat(signUpRequest.getPhoneNumber()).isEqualTo(findMember.getPhoneNumber());
        assertThat(signUpRequest.getGender()).isEqualTo(findMember.getGender());
    }

    @Test
    void update() {
        Member findMember = memberRepository.findByEmail("firstMail@naver.com").orElseThrow();
        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest("changeMail", "1234", "1234", 20L, "부산", Gender.MALE, 175L, 70L, "학생", null, "01012341234", LocalDate.of(1999, 3, 12));
        memberService.update(findMember.getId(), updateMemberRequest);
        assertThat(findMember.getEmail()).isEqualTo(updateMemberRequest.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(updateMemberRequest.getPassword());
        assertThat(findMember.getName()).isEqualTo(updateMemberRequest.getName());
        assertThat(findMember.getOccupation()).isEqualTo(updateMemberRequest.getOccupation());
        assertThat(findMember.getBirthday()).isEqualTo(updateMemberRequest.getBirthday());
        assertThat(findMember.getAddress()).isEqualTo(updateMemberRequest.getAddress());
        assertThat(findMember.getPhoneNumber()).isEqualTo(updateMemberRequest.getPhoneNumber());
        assertThat(findMember.getGender()).isEqualTo(updateMemberRequest.getGender());
    }

    @Test
    void findMember() {
        FindByEmailResponse findMember = memberService.findMember(1L);
        assertThat(findMember.getEmail()).isEqualTo("firstMail@naver.com");
        assertThat(findMember.getPassword()).isEqualTo("1234");
        assertThat(findMember.getName()).isEqualTo("초기멤버");
        assertThat(findMember.getOccupation()).isEqualTo("학생");
        assertThat(findMember.getBirthday()).isEqualTo(LocalDate.of(1999, 5, 10));
        assertThat(findMember.getAddress()).isEqualTo("서울");
        assertThat(findMember.getPhoneNumber()).isEqualTo("01012341234");
        assertThat(findMember.getGender()).isEqualTo(Gender.MALE);
    }

    @Test
    void deleteMember() {
        memberService.deleteMember(1L);
        assertThatThrownBy(() -> memberService.findById(1L).orElseThrow()).isInstanceOf(NoSuchElementException.class);
    }
}