package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.repository.owner.OwnerRepository;
import com.backend.fitta.repository.staff.StaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class StaffTest {

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

        Gym gym = new Gym("powerGym", savedOwner,null,null, "12312321", "adddr", GenderDivision.UNISEX,"123123");
        Gym savedGym = gymRepository.save(gym);

        Staff staff = new Staff("staff", LocalDate.of(1995, Month.MAY, 3), Gender.FEMALE, "0000000", "addr", savedGym, null);
        savedStaff = staffRepository.save(staff);
    }
    @Test
    void staffIntoGym(){
        Owner owner = new Owner("email","pass","박사장", "010-0100-0000", "ownerAddress","1213-12314-8432-1112");
        Gym gym = new Gym("testGym", owner,null,null, "02-1234-1242", "testGymAddress", GenderDivision.UNISEX,"12312312");
        Staff staff1 = new Staff(
                "박직원",
                LocalDate.of(1999,1,1),
                Gender.MALE,
                "010-1234-1234",
                "주소어드레스",
                null,
                null
        );
        Staff staff2 = new Staff(
                "최직원",
                LocalDate.of(1999,1,1),
                Gender.FEMALE,
                "010-1234-1234",
                "주소어드레스",
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
        Gym gym = new Gym("testGym", owner,null,null, "02-1234-1242", "testGymAddress", GenderDivision.UNISEX,"12312312");
        Team team = new Team("teamA",savedStaff);
        Staff staff1 = new Staff(
                "박직원",
                LocalDate.of(1999,1,1),
                Gender.MALE,
                "010-1234-1234",
                "주소어드레스",
                null,
                null
        );
        Staff staff2 = new Staff(
                "최직원",
                LocalDate.of(1999,1,1),
                Gender.FEMALE,
                "010-1234-1234",
                "주소어드레스",
                null,
                null
        );

        staff1.changeGym(gym);
        staff2.changeGym(gym);

        staff1.changeTeam(team);

        assertThat(team.getStaff()).isEqualTo(savedStaff);
    }


}