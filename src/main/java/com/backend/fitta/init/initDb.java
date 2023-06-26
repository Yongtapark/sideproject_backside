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
import org.springframework.security.access.vote.RoleVoter;
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
        initService.dbInitGym1();
        initService.dbInitGym2();
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
                    .gender(Gender.MALE)
                    .birthdate(LocalDate.of(1996,Month.MAY,3))
                    .name("testMember")
                    .role(Role.MEMBER)
                    .build();
            memberRepository.save(member);

            Member member2 = Member
                    .builder()
                    .email("bjjtachan@gmail.com")
                    .password("password")
                    .gender(Gender.MALE)
                    .birthdate(LocalDate.of(1996,Month.MAY,3))
                    .name("testMember")
                    .role(Role.MEMBER)
                    .build();
            memberRepository.save(member2);

        }

        //테스트용 오너와 짐 생성

        public void dbInitGym1(){
            Owner owner = Owner
                    .builder()
                    .email("owner@email.com")
                    .password("password")
                    .name("testOwner")
                    .phoneNumber("010-0100-0000")
                    .address("address")
                    .businessRegistrationNumber("asdasdasda")
                    .role(Role.OWNER)
                    .build();
            Owner save = ownerRepository.save(owner);


            for (int i = 8; i <= 30; i++) {
                gymRepository.save(new Gym("gym" + i, save, null, null, "0" + i + "-0000-0000", "gymAddress" + i, GenderDivision.UNISEX, "asd" + i + i));
            }
            Gym gym1 = new Gym("gym1", save, null, null, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY, "asdasd");
            Gym gym2 = new Gym("gym2", save, null, null, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY, "asdasdad");
            Gym gym3 = new Gym("gym3", save, null, null, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX, "asdasdsad");

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

            //맴버 가입
            Member member1 = Member
                    .builder()
                    .email("email1@email.com")
                    .password("password")
                    .name("testMember1")
                    .gender(Gender.FEMALE)
                    .gym(gym1)
                    .role(Role.MEMBER)
                    .birthdate(LocalDate.of(1995,Month.MAY,3))
                    .build();


            Member member2 = Member
                    .builder()
                    .email("email2@email.com")
                    .password("password")
                    .name("testMember2")
                    .gender(Gender.FEMALE)
                    .birthdate(LocalDate.of(1985,Month.MAY,3))
                    .gym(gym1)
                    .role(Role.MEMBER)
                    .build();

            Member member3 = Member
                    .builder()
                    .email("email3@email.com")
                    .password("password")
                    .name("testMember3")
                    .gender(Gender.MALE)
                    .birthdate(LocalDate.of(2005,Month.MAY,3))
                    .gym(gym1)
                    .role(Role.MEMBER)
                    .build();

            //맴버 가입
            Member member4 = Member
                    .builder()
                    .email("email4@email.com")
                    .password("password")
                    .name("testMember4")
                    .gender(Gender.MALE)
                    .gym(gym1)
                    .role(Role.MEMBER)
                    .birthdate(LocalDate.of(1955,Month.MAY,3))
                    .build();


            Member member5 = Member
                    .builder()
                    .email("email6@email.com")
                    .password("password")
                    .name("testMember6")
                    .gender(Gender.MALE)
                    .role(Role.MEMBER)
                    .birthdate(LocalDate.of(1945,Month.MAY,3))
                    .gym(gym1)
                    .build();

            Member member6 = Member
                    .builder()
                    .email("email3@email.com")
                    .password("password")
                    .name("testMember3")
                    .gender(Gender.MALE)
                    .role(Role.MEMBER)
                    .birthdate(LocalDate.of(2005,Month.MAY,3))
                    .gym(gym1)
                    .build();


            memberRepository.save(member1);
            memberRepository.save(member2);
            memberRepository.save(member3);
            memberRepository.save(member4);
            memberRepository.save(member5);
            memberRepository.save(member6);







        }

    public void dbInitGym2(){
        Owner owner = new Owner("owne2r@emai.com", "password", "testOwner", "010-0100-0000", "address", "123123123");
        Owner save = ownerRepository.save(owner);


        Gym gym4 = new Gym("gym1", save, null, null, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY, "asdasd");
        Gym gym5 = new Gym("gym2", save, null, null, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY, "asdasdad");
        Gym gym6 = new Gym("gym3", save, null, null, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX, "asdasdsad");
        Gym gym7 = new Gym("gym4", save, null, null, "03-0000-0000", "gymAddress4", GenderDivision.UNISEX, "asdasdsad");

        gymRepository.save(gym4);
        gymRepository.save(gym5);
        gymRepository.save(gym6);
        gymRepository.save(gym7);

        //staff 생성
        Staff staff1 = new Staff("staff1", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym4, null);
        Staff staff2 = new Staff("staff2", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym4, null);
        Staff staff3 = new Staff("staff3", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym4, null);

        staffRepository.save(staff1);
        staffRepository.save(staff2);
        staffRepository.save(staff3);

        Staff staff4 = new Staff("staff4", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym5, null);
        Staff staff5 = new Staff("staff5", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym5, null);
        Staff staff6 = new Staff("staff6", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym5, null);

        staffRepository.save(staff4);
        staffRepository.save(staff5);
        staffRepository.save(staff6);

        Staff staff7 = new Staff("staff7", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym6, null);
        Staff staff8 = new Staff("staff8", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym6, null);
        Staff staff9 = new Staff("staff9", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym6, null);

        staffRepository.save(staff7);
        staffRepository.save(staff8);
        staffRepository.save(staff9);

        Staff staff10 = new Staff("staff10", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym7, null);
        Staff staff11= new Staff("staff11", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym7, null);
        Staff staff12 = new Staff("staff12", LocalDate.of(1111, Month.MAY, 1), Gender.FEMALE, "13221312", "ad", gym7, null);

        staffRepository.save(staff10);
        staffRepository.save(staff11);
        staffRepository.save(staff12);



    }




    }
}
