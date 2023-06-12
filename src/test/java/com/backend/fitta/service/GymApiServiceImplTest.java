package com.backend.fitta.service;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.GymNotFoundException;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.repository.gym.GymRepository;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
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
    @Autowired
    OwnerService ownerService;




    @Test
    void ytGymSaveTest() throws Exception{
        //given
        //오너 등록
       Owner owner = new Owner("email", "pass", "name", "123", "ad", "12312312");
        Long savedOwner = ownerService.save(owner);

        //when
        // 체육관 생성
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX,savedOwner,"1231"));
        BasicGymInfo gymDto = gymApiService.findById(savedGymId);

        //then
        assertThat(gymDto.getName()).isEqualTo("헬스장1");
        assertThat(gymDto.getOwnerName()).isEqualTo("name");
        assertThatThrownBy(()->gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX,5L,"123123"))).isInstanceOf(OwnerNotFoundException.class);


    }

    @Test
    void saveAndFindById() {

        //오너 등록
        Owner owner = new Owner("email", "pass", "name", "123", "ad", "12312312");
        Long savedOwner = ownerService.save(owner);


        // 체육관 생성
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX,savedOwner,"1231"));
        BasicGymInfo gymDto = gymApiService.findById(savedGymId);

        assertThat(gymDto.getName()).isEqualTo("헬스장1");
        assertThat(gymDto.getPhoneNumber()).isEqualTo("01012341234");
        assertThat(gymDto.getAddress()).isEqualTo("대전");
        assertThat(gymDto.getGenderDivision()).isEqualTo(GenderDivision.UNISEX);
    }

    @Test
    void findAll() {
        //오너 등록
        Owner owner = new Owner("email", "pass", "name", "123", "ad", "12312312");
        Long savedOwner = ownerService.save(owner);

        gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX,owner.getId(),"12312312"));
        gymApiService.save(new SaveGymRequest("헬스장2", "01012341234", "대전", GenderDivision.UNISEX,owner.getId(),"12312312"));
        Result<List<BasicGymInfo>> all = gymApiService.findAll();
        //assertThat(all.getData().size()).isEqualTo(2);
      /*  assertThat(all.getData().get(0).getName()).isEqualTo("헬스장1");
        assertThat(all.getData().get(1).getName()).isEqualTo("헬스장2");*/

    }

    @Test
    void update() {
        Owner owner = new Owner("email", "pass", "name", "123", "ad", "12312312");
        Long savedOwner = ownerService.save(owner);
        Owner findOwner = ownerService.findById(savedOwner);

        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX,findOwner.getId(),"123123123"));
        Gym gym = gymRepository.findById(savedGymId).orElseThrow();
        gymApiService.update(savedGymId, new UpdateGymRequest("헬스장2", "01012341234", "대구", GenderDivision.FEMALE_ONLY));
        assertThat(gym.getName()).isEqualTo("헬스장2");
        assertThat(gym.getPhoneNumber()).isEqualTo("01012341234");
        assertThat(gym.getAddress()).isEqualTo("대구");
        assertThat(gym.getGenderDivision()).isEqualTo(GenderDivision.FEMALE_ONLY);
    }

    @Test
    void delete() {
        Owner owner = new Owner("email", "pass", "name", "123", "ad", "12312312");
        Long savedOwner = ownerService.save(owner);
        Owner findOwner = ownerService.findById(savedOwner);
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX,findOwner.getId(),"1231232"));
        gymApiService.delete(savedGymId);
        //삭제해서 아이디로 찾으면 오류터짐
        assertThatThrownBy(()->gymApiService.findById(savedGymId))
                .isInstanceOf(GymNotFoundException.class);
    }

    @Test
    void saveOwnerGym() {
        Owner owner = new Owner("email", "pass", "name", "123", "ad", "12312312");
        Long savedOwner = ownerService.save(owner);
        Owner findOwner = ownerService.findById(savedOwner);
        Long savedGymId = gymApiService.save(new SaveGymRequest("헬스장1", "01012341234", "대전", GenderDivision.UNISEX,findOwner.getId(),"123123123"));
        Long savedOwnerId = ownerApiService.save(new SignUpOwnerRequest("asd123@naver.com", "1234", "오너1", "01012341234", "부천", "123456"));
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