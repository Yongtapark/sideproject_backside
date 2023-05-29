package com.backend.fitta.service.member;

import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.dto.member.UpdateMemberRequest;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.service.apiService.interfaces.TeamApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired TeamApiService teamApiService;

    private Member testMember;

    @BeforeEach
    public void beforeEach() {
        SignUpRequest signUpRequest = new SignUpRequest("firstMail@naver.com", "1234", "1234", "초기멤버",
                "서울", Gender.MALE, "01012341234", LocalDate.of(1999, 5, 10), "학생");
        Long memberId = memberService.save(signUpRequest);
        testMember = memberRepository.findById(memberId).orElseThrow();
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
        log.info("finMember={}",memberService.findById(findMember.getId()).get().getId());
        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest("changeMail", "1234", "1234", 20L, "부산", Gender.MALE, 175L, 70L, "학생", null, "01012341234", LocalDate.of(1999, 3, 12));
        Long update = memberService.update(findMember.getId(), updateMemberRequest);
        log.info("update={}",memberService.findById(update).get().getId());
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
        List<Member> all = memberRepository.findAll();
        log.info("allMembers={}",all.stream().map(member -> member.getId()).collect(Collectors.toList()));
        Member findMember = memberService.findById(testMember.getId()).orElseThrow(()->new RuntimeException("no member"));
        log.info("findMember={}",findMember);
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

    @Test
    void saveMemberTeam() {
        SignUpRequest signUpRequest = new SignUpRequest("email@naver.com", "1234", "1234", "멤버1",
                "대전", Gender.FEMALE, "01012341234", LocalDate.of(1995, 12, 10), "학생");
        Long saveMemberId = memberService.save(signUpRequest);
        SaveTeamRequest teamRequest = new SaveTeamRequest("팀1");
        Long saveTeamId = teamApiService.save(teamRequest);
        Member findMember = memberService.findById(saveMemberId).orElseThrow();
        assertThat(findMember.getTeam()).isNull();
        memberService.saveTeamMember(saveMemberId, saveTeamId);
        assertThat(findMember.getTeam().getName()).isEqualTo("팀1");
    }
}