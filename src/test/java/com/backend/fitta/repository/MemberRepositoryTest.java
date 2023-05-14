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

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//JPA 를 사용하려면 트랜잭션이 필요합니다.
@Transactional
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
                "memberA",
                20L,
                "address",
                Gender.MALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                teamA
        );

        memberRepository.save(memberA);
    }

    @Test
    void join() {
        Team teamB=new Team("teamB");
        teamRepository.save(teamB);

        Member memberB = new Member(
                "email@email.com",
                "memberB",
                20L,
                "address",
                Gender.MALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                teamB
        );
        Member savedMember = memberRepository.save(memberB);
        Member member = memberRepository.findById(savedMember.getId()).get();
        Assertions.assertThat(savedMember).isEqualTo(member);
    }


}