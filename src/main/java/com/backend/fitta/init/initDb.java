package com.backend.fitta.init;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
        initService.dbInitGym();
    }


@Component
@Transactional
@RequiredArgsConstructor
    static class InitService{
    private final MemberRepository memberRepository;
    private final OwnerRepository ownerRepository;
    private final GymRepository gymRepository;



    public void dbInit(){
        Member member = Member
                .builder()
                .email("email@email.com")
                .password("password")
                .roles(Role.USER)
                .build();
        memberRepository.save(member);

    }

    public void dbInitGym(){
        Owner owner = new Owner("owner@emai.com", "password", "owner", "010-0100-0000", "address", "123123123");
        ownerRepository.save(owner);


        Gym gym1 = new Gym("gym1", owner, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY);
        Gym gym2 = new Gym("gym2", owner, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY);
        Gym gym3 = new Gym("gym3", owner, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX);

        gymRepository.save(gym1);
        gymRepository.save(gym2);
        gymRepository.save(gym3);




    }


}
}
