package com.backend.fitta.repository;

import com.backend.fitta.dto.team.MemberTeamResponse;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//JPA 를 사용하려면 트랜잭션이 필요합니다.
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    EntityManager em;

//    @BeforeEach
//    void preTest(){
//        Team teamA=new Team("teamA");
//        teamRepository.save(teamA);
//
//        Member memberA = new Member(
//                "email@email.com",
//                "memberA",
//                20L,
//                "address",
//                Gender.FEMALE,
//                180L,
//                80L,
//                "beck-dev",
//                "nothing",
//                null,
//                null,
//                null);
//
//        memberRepository.save(memberA);
//    }

//    @Test
//    void join() {
//        Team teamB=new Team("teamB");
//        teamRepository.save(teamB);
//
//        Member memberB = new Member(
//                "email@email.com",
//                "memberB",
//                20L,
//                "address",
//                Gender.MALE,
//                180L,
//                80L,
//                "beck-dev",
//                "nothing",
//                null,
//                null,
//                teamB);
//
//        Member savedMember = memberRepository.save(memberB);
//        Member member = memberRepository.findById(savedMember.getId()).get();
//        Assertions.assertThat(savedMember).isEqualTo(member);
//    }
//

    @Test
    public void searchTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1@naver.com", "1234", "member1", LocalDate.of(2012, 10, 22),
                "010234", "대구", Gender.FEMALE, null, null, null, null, null, teamA);
        Member member2 = new Member("member2@naver.com", "1234", "member2", LocalDate.of(2012, 10, 22),
                "010234", "서울", Gender.FEMALE, null, null, null, null, null, teamA);

        Member member3 = new Member("member3@naver.com", "1234", "member3", LocalDate.of(2012, 10, 22),
                "010234", "부산", Gender.FEMALE, null, null, null, null, null, teamB);
        Member member4 = new Member("member4@naver.com", "1234", "member4", LocalDate.of(2012, 10, 22),
                "010234", "인천", Gender.FEMALE, null, null, null, null, null, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

//        List<MemberTeamResponse> result = memberRepository.search(1L);
//        for (MemberTeamResponse memberTeamResponse : result) {
//            System.out.println(memberTeamResponse);
//        }
    }
}