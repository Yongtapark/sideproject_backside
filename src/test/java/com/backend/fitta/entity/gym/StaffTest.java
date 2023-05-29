package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.enums.Grade;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class StaffTest {
    @Test
    void staffIntoGym(){
        Owner owner = new Owner("email","pass","박사장", "010-0100-0000", "ownerAddress","1213-12314-8432-1112");
        Gym gym = new Gym("testGym", owner, "02-1234-1242", "testGymAddress", GenderDivision.UNISEX);
        Staff staff1 = new Staff(
                "박직원",
                LocalDate.of(1999,1,1),
                Gender.MALE,
                "010-1234-1234",
                "주소어드레스",
                Grade.TRAINER,
                null,
                null
        );
        Staff staff2 = new Staff(
                "최직원",
                LocalDate.of(1999,1,1),
                Gender.FEMALE,
                "010-1234-1234",
                "주소어드레스",
                Grade.TRAINER,
                null,
                null
        );

        staff1.changeGym(gym);
        staff2.changeGym(gym);

        assertThat(gym.getStaff()).contains(staff1);
        assertThat(gym.getStaff()).contains(staff2);
        assertThat(gym.getStaff()).size().isEqualTo(2);
    }

    @Test
    void staffIntoTeam(){
        Owner owner = new Owner("email","pass","박사장", "010-0100-0000", "ownerAddress","1213-12314-8432-1112");
        Gym gym = new Gym("testGym", owner, "02-1234-1242", "testGymAddress", GenderDivision.UNISEX);
        Team team = new Team("teamA");
        Staff staff1 = new Staff(
                "박직원",
                LocalDate.of(1999,1,1),
                Gender.MALE,
                "010-1234-1234",
                "주소어드레스",
                Grade.TRAINER,
                null,
                null
        );
        Staff staff2 = new Staff(
                "최직원",
                LocalDate.of(1999,1,1),
                Gender.FEMALE,
                "010-1234-1234",
                "주소어드레스",
                Grade.TRAINER,
                null,
                null
        );

        staff1.changeGym(gym);
        staff2.changeGym(gym);

        staff1.changeTeam(team);

        assertThat(team.getStaffs()).contains(staff1);
    }


}