package com.backend.fitta.repository;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//JPA 를 사용하려면 트랜잭션이 필요합니다.
@Transactional
@Commit
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void preTest(){
        Team teamA=new Team("teamA");
        teamRepository.save(teamA);

        Member memberA = new Member(
                "email@email.com",
                "패스워드",
                "memberA",
                LocalDate.of(2100, Month.MAY,3),
                "phone",
                "address",
                Gender.FEMALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                null,
                null
        );

        memberRepository.save(memberA);
    }

    @Test
    void join() {
        Team teamB=new Team("teamB");
        teamRepository.save(teamB);

        Member memberB = new Member(
                "emailasdasd@email.com",
                "패스워드",
                "memberA",
                LocalDate.of(2150, Month.MAY,13),
                "phon12312312e",
                "address1231",
                Gender.FEMALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                null,
                null
        );

        Member savedMember = memberRepository.save(memberB);
        Member member = memberRepository.findById(savedMember.getId()).get();
        Assertions.assertThat(savedMember).isEqualTo(member);
    }


}