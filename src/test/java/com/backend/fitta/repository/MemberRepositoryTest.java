package com.backend.fitta.repository;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.repository.team.TeamRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

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

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    GymRepository gymRepository;

    Staff savedStaff;

    @BeforeEach
    void init(){
        Owner owner = new Owner("email", "password", "name", "01010101", "addd", "0000");
        Owner savedOwner = ownerRepository.save(owner);

        Gym gym = new Gym("powerGym", savedOwner, "12312321", "adddr", GenderDivision.UNISEX,"123123");
        Gym savedGym = gymRepository.save(gym);

        Staff staff = new Staff("staff", LocalDate.of(1995, Month.MAY, 3), Gender.FEMALE, "0000000", "addr", savedGym, null);
        savedStaff = staffRepository.save(staff);
    }

    @Test
    public void searchTest() {
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

//        List<MemberTeamResponse> result = memberRepository.search(1L);
//        for (MemberTeamResponse memberTeamResponse : result) {
//            System.out.println(memberTeamResponse);
//        }
    }
}