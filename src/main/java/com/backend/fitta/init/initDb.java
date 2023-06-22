package com.backend.fitta.init;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.member.MemberRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.repository.team.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
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
        initService.dbInitGym3();
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
        private final TeamRepository teamRepository;


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
                gymRepository.save(new Gym("gym"+i,save,"0"+i+"-0000-0000","gymAddress"+i,GenderDivision.UNISEX,"asd"+i+i));
            }
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



        Gym gym4 = new Gym("gym1", save, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY,"asdasd");
        Gym gym5 = new Gym("gym2", save, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY,"asdasdad");
        Gym gym6 = new Gym("gym3", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym7 = new Gym("gym4", save, "03-0000-0000", "gymAddress4", GenderDivision.UNISEX,"asdasdsad");

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

        //team 생성
        Team team1 = new Team("teamA", staff1);
        Team team2 = new Team("teamB", staff2);
        Team team3 = new Team("teamC", staff3);
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);



    }

    public void dbInitGym3(){
        Owner save = ownerRepository.findById(1L).get();
        Gym gym4 = new Gym("gym4", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym5 = new Gym("gym5", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym6 = new Gym("gym6", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym7 = new Gym("gym7", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym8 = new Gym("gym8", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym9 = new Gym("gym9", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym10 = new Gym("gym10", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym11 = new Gym("gym11", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym12 = new Gym("gym12", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym13 = new Gym("gym13", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym14 = new Gym("gym14", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym15 = new Gym("gym15", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym16 = new Gym("gym16", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym17 = new Gym("gym17", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym18 = new Gym("gym18", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym19 = new Gym("gym19", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym20 = new Gym("gym20", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym21 = new Gym("gym21", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym22 = new Gym("gym22", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym23 = new Gym("gym23", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym24 = new Gym("gym24", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym25 = new Gym("gym25", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym26 = new Gym("gym26", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym27 = new Gym("gym27", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym28 = new Gym("gym28", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym29 = new Gym("gym29", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        Gym gym30 = new Gym("gym30", save, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"asdasdsad");
        gymRepository.save(gym4);
        gymRepository.save(gym5);
        gymRepository.save(gym6);
        gymRepository.save(gym7);
        gymRepository.save(gym8);
        gymRepository.save(gym9);
        gymRepository.save(gym10);
        gymRepository.save(gym11);
        gymRepository.save(gym12);
        gymRepository.save(gym13);
        gymRepository.save(gym14);
        gymRepository.save(gym15);
        gymRepository.save(gym16);
        gymRepository.save(gym17);
        gymRepository.save(gym18);
        gymRepository.save(gym19);
        gymRepository.save(gym20);
        gymRepository.save(gym21);
        gymRepository.save(gym22);
        gymRepository.save(gym23);
        gymRepository.save(gym24);
        gymRepository.save(gym25);
        gymRepository.save(gym26);
        gymRepository.save(gym27);
        gymRepository.save(gym28);
        gymRepository.save(gym29);
        gymRepository.save(gym30);

    }




    }
}
