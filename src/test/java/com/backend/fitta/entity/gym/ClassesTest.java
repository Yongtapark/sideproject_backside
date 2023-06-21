package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.member.Member;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.repository.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@RequiredArgsConstructor
class ClassesTest {

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


        Classes class1 = gym.createClasses("기본", new BigDecimal(50000), "시설 이용비");
        Classes class2 = gym.createClasses("PT_120회", new BigDecimal(1200000), "120회");
        Classes class3 = gym.createClasses("PT_60회", new BigDecimal(600000), "60회");
        Classes class4 = gym.createClasses("그룹_PT", new BigDecimal(50000), "한달 정기 구독");
        Classes class5 = gym.createClasses("락커이용", new BigDecimal(20000), "락커이용");

        Member test1 = Member.builder()
                .name("test1")
                .email("email@email")
                .isSubscribed(false)
                .gym(null)
                .build();


        //when
        test1.joinGym(gym,class1,class2,class5);

        //then
        log.info("test1.isSubscribed()={}",test1.isSubscribed());
        List<Registrations> registrations = test1.getRegistrations();
        for (Registrations registration : registrations) {
            log.info("registration={}",registration.getClasses().getName());
            log.info("gymName={}",registration.getClasses().getGym().getName());
            Assertions.assertThat(registration.getClasses().getGym()).isEqualTo(gym);
            Assertions.assertThat(registration.getClasses()).isIn(class1,class2,class5);
        }
        Assertions.assertThat(registrations.size()).isEqualTo(3);

    }

}
