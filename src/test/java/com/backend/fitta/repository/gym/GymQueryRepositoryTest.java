package com.backend.fitta.repository.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.service.interfaces.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class GymQueryRepositoryTest {
    @Autowired
    private GymQueryRepository gymQueryRepository;

    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private OwnerService ownerService;

    public Long savedOwnerId = null;

    @BeforeEach
    public void init(){
        Owner owner = new Owner("owner@owner.com", "pass", "ownerName", "numbert", "add", "num");
        savedOwnerId = ownerService.save(owner);
        Owner findOwner = ownerService.findById(savedOwnerId);

        Gym gym1 = new Gym("gym1", findOwner, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY,"123123");
        Gym gym2 = new Gym("gym2", findOwner, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY,"123123");
        Gym gym3 = new Gym("gym3", findOwner, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"12312312312");

        gymRepository.save(gym1);
        gymRepository.save(gym2);
        gymRepository.save(gym3);


    }

    //ownerId에 따른 gym count
    @Test
    void GymCount() throws Exception{
        //when
        Owner owner = new Owner("test@owner.com", "pass", "ownerName", "numbert", "add", "num");
        Long id = ownerService.save(owner);
        Owner findOwner = ownerService.findById(id);

        Gym gym1 = new Gym("test1", findOwner, "01-0000-0000", "gymAddress1", GenderDivision.MALE_ONLY,"123123");
        Gym gym2 = new Gym("test2", findOwner, "02-0000-0000", "gymAddress2", GenderDivision.FEMALE_ONLY,"12312312");
        Gym gym3 = new Gym("test3", findOwner, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"123123");
        Gym gym4 = new Gym("test4", findOwner, "03-0000-0000", "gymAddress3", GenderDivision.UNISEX,"12312312321");

        gymRepository.save(gym1);
        gymRepository.save(gym2);
        gymRepository.save(gym3);
        gymRepository.save(gym4);

        //when
        Long count = gymQueryRepository.GymCountByOwnerId(savedOwnerId);
        Long count2 = gymQueryRepository.GymCountByOwnerId(id);


        //then
        assertThat(count).isEqualTo(3);
        assertThat(count2).isEqualTo(4);
    }


}