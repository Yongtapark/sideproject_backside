package com.backend.fitta.repository;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    /*@BeforeEach
    void preTest(){
        Team team=new Team("teamA");
        teamRepository.save(team);

        Member member = new Member(
                "email@email.com",
                "memberName",
                20L,
                "address",
                Gender.MALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                new Team("teamA")
        );
    }*/
    @Commit
    @Test
    void join() {
        Team teamB=new Team("teamB");
        teamRepository.save(teamB);

        Member member = new Member(
                "email@email.com",
                "memberName",
                20L,
                "address",
                Gender.MALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                teamB
        );
        memberRepository.save(member);
    }


}