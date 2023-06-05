package com.backend.fitta.service;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Slf4j
@Transactional
class GymApiServiceImplTest {

    @Autowired
    EntityManager em;
    @Autowired
    GymRepository gymRepository;
    @Autowired
    GymApiService gymApiService;
    @Autowired
    OwnerApiService ownerApiService;


    @Test
    void saveAndFindById() {
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX));
        BasicGymInfo gymInfo = gymApiService.findById(savedGymId);
        assertThat(gymInfo.getName()).isEqualTo("헬스장1");
        assertThat(gymInfo.getPhoneNumber()).isEqualTo("01012341234");
        assertThat(gymInfo.getAddress()).isEqualTo("대전");
        assertThat(gymInfo.getGenderDivision()).isEqualTo(GenderDivision.UNISEX);
    }

    @Test
    void findAll() {
        gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX));
        gymApiService.save(new SaveGymRequest("헬스장2", "01012341234", "대전", GenderDivision.UNISEX));
        Result<List<BasicGymInfo>> all = gymApiService.findAll();
        //assertThat(all.getData().size()).isEqualTo(2);
//        assertThat(all.getData().get(0).getName()).isEqualTo("헬스장1");
     //   assertThat(all.getData().get(1).getName()).isEqualTo("헬스장2");

    }

    @Test
    void update() {
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX));
        Gym gym = gymRepository.findById(savedGymId).orElseThrow();
        gymApiService.update(savedGymId, new UpdateGymRequest("헬스장2", "01012341234", "대구", GenderDivision.FEMALE_ONLY));
        assertThat(gym.getName()).isEqualTo("헬스장2");
        assertThat(gym.getPhoneNumber()).isEqualTo("01012341234");
        assertThat(gym.getAddress()).isEqualTo("대구");
        assertThat(gym.getGenderDivision()).isEqualTo(GenderDivision.FEMALE_ONLY);
    }

    @Test
    void delete() {
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX));
        gymApiService.delete(savedGymId);
        //삭제해서 아이디로 찾으면 오류터짐
        assertThatThrownBy(()->gymApiService.findById(savedGymId))
                .isInstanceOf(GymNotFoundException.class);
    }

    @Test
    void saveOwnerGym() {
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX));
        Long savedOwnerId = ownerApiService.save(new SignUpOwnerRequest("asd123@naver.com", "1234", "1234", "오너1", "01012341234", "부천", "123456"));
        gymApiService.saveOwnerGym(savedGymId, savedOwnerId);
        Gym gym = gymRepository.findById(savedGymId).orElseThrow();
        assertThat(gym.getOwner().getEmail()).isEqualTo("asd123@naver.com");
        assertThat(gym.getOwner().getPassword()).isEqualTo("1234");
        assertThat(gym.getOwner().getName()).isEqualTo("오너1");
        assertThat(gym.getOwner().getPhoneNumber()).isEqualTo("01012341234");
        assertThat(gym.getOwner().getAddress()).isEqualTo("부천");
        assertThat(gym.getOwner().getBusinessRegistrationNumber()).isEqualTo("123456");
    }
}