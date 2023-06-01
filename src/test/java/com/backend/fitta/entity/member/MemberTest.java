package com.backend.fitta.entity.member;

import com.backend.fitta.controller.member.MemberController;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
public class MemberTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberController memberController;


    @Test
    public void testEntity() {
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

        //초기화
        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : members) {
            System.out.println("asdsadasdasdsad");
            System.out.println("member = " + member);
            System.out.println("-> member.team" + member.getTeam());
        }
    }


}
