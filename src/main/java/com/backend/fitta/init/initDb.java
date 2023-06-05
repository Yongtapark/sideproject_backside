package com.backend.fitta.init;

import com.backend.fitta.controller.owner.OwnerController;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

//
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final MemberRepository memberRepository;
        private final OwnerRepository ownerRepository;
        private final GymRepository gymRepository;

        private final OwnerApiService ownerApiService;
        private final OwnerController ownerController;


        //테스트용 맴버 생성
        public void dbInit(){
            Member member = Member
                    .builder()
                    .email("email@email.com")
                    .password("password")
                    .roles(Role.MEMBER)
                    .name("testMember")
                    .build();
            memberRepository.save(member);

        }

        //테스트용 오너와 짐 생성

        public void dbInitGym(){
            Owner owner = new Owner("owner@emai.com", "password", "testOwner", "010-0100-0000", "address", "123123123");
            Owner save = ownerRepository.save(owner);



            Gym gym1 = new Gym("gym1", save, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY);
            Gym gym2 = new Gym("gym2", save, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY);
            Gym gym3 = new Gym("gym3", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX);

            gymRepository.save(gym1);
            gymRepository.save(gym2);
            gymRepository.save(gym3);




        }


    }
}
