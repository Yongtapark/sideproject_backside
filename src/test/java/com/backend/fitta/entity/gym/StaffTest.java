package com.backend.fitta.entity.gym;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.enums.Grade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class StaffTest {
    @Test
    void test(){
        Owner owner = new Owner("박사장", "010-0100-0000", "ownerAddress","1213-12314-8432-1112");
        Gym gym = new Gym("testGym", owner, "02-1234-1242", "testGymAddress", GenderDivision.UNISEX);
        Staff staff = new Staff("박직원", 20L, Gender.MALE, "010-1234-1234", "주소어드레스", Grade.TRAINER, null, null);

        staff.changeGym(gym);

        log.info("staffList={}",gym.getStaff());


    }


}