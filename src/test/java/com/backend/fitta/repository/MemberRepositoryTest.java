package com.backend.fitta.repository;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    void preTest(){
        Owner owner = new Owner("박사장", "010-0100-0000","ownerAddress" ,"1213-12314-8432-1112");
        ownerRepository.save(owner);
        Gym gym = new Gym("ultraGym", owner, "010-0101-1010", "gymAddress", GenderDivision.UNISEX);
        gymRepository.save(gym);
        Team teamA=new Team("teamA");
        teamRepository.save(teamA);

        Member memberA = new Member(
                "email@email.com",
                "memberA",
                LocalDate.of(1999,1,1),
                "010-0000-0000",
                "address",
                Gender.MALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                gym,
                teamA
        );

        memberRepository.save(memberA);
    }

    @Test
    void join() {
        Owner owner = new Owner("김사장", "010-0100-0000","ownerAddress" ,"1213-12314-8432-1112");
        ownerRepository.save(owner);
        Gym gym = new Gym("powerGym", owner, "010-0101-1010", "gymAddress", GenderDivision.MALE_ONLY);
        gymRepository.save(gym);
        Team teamB=new Team("teamB");
        teamRepository.save(teamB);

        Member memberB = new Member(
                "email@email.com",
                "memberB",
                LocalDate.of(1999,1,1),
                "010-0000-0000",
                "address",
                Gender.MALE,
                180L,
                80L,
                "beck-dev",
                "nothing",
                gym,
                teamB
        );
        Member savedMember = memberRepository.save(memberB);
        Member member = memberRepository.findById(savedMember.getId()).get();
        Assertions.assertThat(savedMember).isEqualTo(member);
    }


}