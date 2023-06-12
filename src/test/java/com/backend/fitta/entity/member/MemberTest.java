package com.backend.fitta.entity.member;

import com.backend.fitta.controller.member.MemberController;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
public class MemberTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberController memberController;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    GymRepository gymRepository;

    Gym savedGym;
    Staff savedStaff;

    @BeforeEach
    void init(){
        Owner owner = new Owner("email", "password", "name", "01010101", "addd", "0000");
        Owner savedOwner = ownerRepository.save(owner);

        Gym gym = new Gym("powerGym", savedOwner, "12312321", "adddr", GenderDivision.UNISEX,"123123");
        savedGym = gymRepository.save(gym);

        Staff staff = new Staff("staff", LocalDate.of(1995, Month.MAY, 3), Gender.FEMALE, "0000000", "addr", savedGym, null);
        savedStaff = staffRepository.save(staff);
    }

    @Test
    public void subScribeTest(){
        Member member1 = Member
                .builder()
                .name("member1")
                .gender(Gender.FEMALE)
                .gym(savedGym)
                .isSubscribed(true)
                .birthdate(LocalDate.of(2012, 10, 22))
                .build();

        Member member2 = new Member("member1@naver.com", "1234", "member1", LocalDate.of(2012, 10, 22),
                "010234", "대구", Gender.FEMALE, null, null, null, null, null, savedGym,true);

        Member save = memberRepository.save(member2);

        log.info("member1.subscribe={}",member1.getSubscribeDate());

        assertThat(save.getSubscribeDate()).isEqualTo(member1.getSubscribeDate());
        assertThat(save.getEndSubscribeDate()).isEqualTo(member1.getEndSubscribeDate());

        log.info("save.getSubscribeDate()={}",save.getSubscribeDate());
        log.info("save.getEndSubscribeDate()={}",save.getEndSubscribeDate());

    }


    @Test
    public void testEntity() {
        Team teamA = new Team("teamA",savedStaff);
        Team teamB = new Team("teamB",savedStaff);
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
