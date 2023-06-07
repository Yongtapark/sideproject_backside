package com.backend.fitta.init;

import com.backend.fitta.controller.owner.OwnerController;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

@Component
@RequiredArgsConstructor
@Profile("!test")
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
        private final StaffRepository staffRepository;

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



            Gym gym1 = new Gym("gym1", save, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY,"asdasd");
            Gym gym2 = new Gym("gym2", save, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY,"asdasdad");
            Gym gym3 = new Gym("gym3", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");

            gymRepository.save(gym1);
            gymRepository.save(gym2);
            gymRepository.save(gym3);

            //staff 생성
            Staff staff1 = new Staff("staff1", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym1, null);
            Staff staff2 = new Staff("staff2", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym1, null);
            Staff staff3 = new Staff("staff3", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym1, null);

            staffRepository.save(staff1);
            staffRepository.save(staff2);
            staffRepository.save(staff3);

            Staff staff4 = new Staff("staff4", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym2, null);
            Staff staff5 = new Staff("staff5", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym2, null);
            Staff staff6 = new Staff("staff6", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym2, null);

            staffRepository.save(staff4);
            staffRepository.save(staff5);
            staffRepository.save(staff6);

            Staff staff7 = new Staff("staff7", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym3, null);
            Staff staff8 = new Staff("staff8", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym3, null);
            Staff staff9 = new Staff("staff9", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym3, null);

            staffRepository.save(staff7);
            staffRepository.save(staff8);
            staffRepository.save(staff9);



        }




    }
}
