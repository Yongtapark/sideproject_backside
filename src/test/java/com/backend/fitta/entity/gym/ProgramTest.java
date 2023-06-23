package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.entity.staff.Staff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
class ProgramTest {

   /* @BeforeEach
    public void makeClasses() throws Exception{

        Owner owner = new Owner("email", "password", "name", "01010101", "addd", "0000");
        Gym gym = new Gym("powerGym", owner, "12312321", "adddr", GenderDivision.UNISEX,"123123");
        Staff staff = new Staff("staff", LocalDate.of(1995, Month.MAY, 3), Gender.FEMALE, "0000000", "addr", gym, null);


        gym.createClasses("기본",new BigDecimal(50000),"시설 이용비");
        gym.createClasses("PT_120회",new BigDecimal(1200000),"120회");
        gym.createClasses("PT_60회",new BigDecimal(600000),"60회");
        gym.createClasses("그룹_PT",new BigDecimal(50000),"한달 정기 구독");
        gym.createClasses("락커이용",new BigDecimal(20000),"락커이용");

        Member test1 = Member.builder()
                .name("test1")
                .email("email@email")
                .isSubscribed(false)
                .gym(null)
                .build();
    }*/

    @Test
    public void subscribeTest() throws Exception{
        //given
        Owner owner = new Owner("email", "password", "name", "01010101", "addd", "0000");
        Gym gym = new Gym("powerGym", owner, "12312321", "adddr", GenderDivision.UNISEX,"123123");
        Staff staff = new Staff("staff", LocalDate.of(1995, Month.MAY, 3), Gender.FEMALE, "0000000", "addr", gym, null);


        Program class1 = gym.createClasses(new Program("기본", new BigDecimal(50000), "시설 이용비"));
        Program class2 = gym.createClasses(new Program("PT_120회", new BigDecimal(1200000), "120회"));
        Program class3 = gym.createClasses(new Program("PT_60회", new BigDecimal(600000), "60회"));
        Program class4 = gym.createClasses(new Program("그룹_PT", new BigDecimal(50000), "한달 정기 구독"));
        Program class5 = gym.createClasses(new Program("락커이용", new BigDecimal(20000), "락커이용"));

        Member test1 = Member.builder()
                .name("test1")
                .email("email@email")
                .gym(null)
                .build();


        //when
        //체육관에 등록했을때(프로그램 선택 후)
        ArrayList<Program> programs = new ArrayList<>();
        programs.add(class1);
        programs.add(class2);
        programs.add(class5);
        test1.joinGym(gym,programs);



        //then
        //맴버가 체육관에 등록되었는지 확인
        assertThat(test1.isSubscribed()).isTrue();
        //맴버의 체육관이 원하는 체육관으로 등록되었는지 확인
        assertThat(test1.getGym().getName()).isEqualTo(gym.getName());
        //등록한 체육관과, 프로그램 확인
        List<Registrations> registrations = test1.getRegistrations();
        for (Registrations registration : registrations) {
            log.info("registration={}",registration.getProgram().getName());
            log.info("gymName={}",registration.getProgram().getGym().getName());
            Assertions.assertThat(registration.getProgram().getGym()).isEqualTo(gym);
            Assertions.assertThat(registration.getProgram()).isIn(class1,class2,class5);
        }
        //등록한 프로그램 개수 확인
        Assertions.assertThat(registrations.size()).isEqualTo(3);



    }

}
