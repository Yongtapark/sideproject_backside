package com.backend.fitta.service.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.owner.Owner;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.service.apiService.interfaces.GymApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class OwnerApiServiceImplTest {
    @Autowired
    OwnerService ownerService;
    @Autowired
    com.backend.fitta.service.apiService.interfaces.OwnerApiService ownerApiService;
    @Autowired
    GymApiService gymApiService;

    @Test
    void test(){
        Owner ownerA = new Owner("email","pwd" ,"김사장", "000-0000-0000", "주소1", "사업자등록번호1");
        Owner ownerB = new Owner("email","pwd","이사장", "000-0000-0000", "주소1", "사업자등록번호1");
        //save
        Long ownerAId = ownerService.save(ownerA);
        Long ownerBId = ownerService.save(ownerB);
        //update
        Owner update = ownerService.update(ownerBId, new Owner("email","pwd" ,"수정사장", "수정번호", "수정주소", "수정번호"));
        log.info("confirm={}",update.getName());
        //findById
        Owner findOwner = ownerService.findById(ownerAId);
        assertThat(ownerA).isEqualTo(findOwner);
        //findByIdWithUpdate
        assertThat(ownerService.findById(ownerBId).getName()).isEqualTo("수정사장");

        //findById Exception
        assertThatThrownBy(()-> ownerService.findById(11L)).isInstanceOf(OwnerNotFoundException.class);
        //FindAll
       // assertThat(ownerService.findAll()).size().isEqualTo(2);
        log.info("ownerService.findAll", ownerService.findAll());
        //delete
        ownerService.delete(findOwner.getId());
        assertThatThrownBy(()-> ownerService.findById(findOwner.getId())).isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void apiTest() throws Exception{
        //given
        // 사장 정보를 생성
        Owner ownerA = new Owner("email","pwd" ,"김사장", "000-0000-0000", "주소1", "사업자등록번호1");
        Owner ownerB = new Owner("email","pwd" ,"이사장", "000-0000-0000", "주소1", "사업자등록번호1");

        //when
        // 사장 정보를 저장
        Long saved1 = ownerService.save(ownerA);
        Long saved2 = ownerService.save(ownerB);
        Owner update = ownerService.update(saved2, new Owner("email", "pwd", "수정된사장", "수정된번호", "수정된주소", "수정된사업자번호"));


        // DB에서 저장된 사장 정보를 조회
        Owner savedOwner1 = ownerService.findById(saved1);
        Owner savedOwner2 = ownerService.findById(saved2);

        //사장2의 정보를 수정
        log.info("update={}",update);

        // 저장된 사장1의 정보로 헬스장을 생성
        Gym gym = new Gym("testGym", savedOwner1,null,null, "02-1234-1242", "testGymAddress", GenderDivision.UNISEX,"123123");
        // 헬스장 정보 저장
//        gymApiService.save(gym);

        // 헬스장 정보를 저장한 후 다시 사장 정보를 조회
        savedOwner1= ownerService.findById(saved1);
        savedOwner2= ownerService.findById(saved2);

        Owner findOwner1 = ownerService.findById(savedOwner1.getId());
        Owner findOwner2 = ownerService.findById(savedOwner2.getId());
        // 사장 전체 조회
        List<Owner> owners = ownerService.findAll();

        //then
        //저장된 사장의 이름과 조회된 사장의 이름이 같은지 확인
        assertThat(ownerA.getName()).isEqualTo(findOwner1.getName());

        assertThat(findOwner2.getName()).isEqualTo("수정된사장");
//        //존재하지 않는 사장 정보를 조회하려 할 때 예외가 발생하는지 확인
       assertThatThrownBy(()-> ownerService.findById(245L)).isInstanceOf(OwnerNotFoundException.class);
//        //사장1의 gymList 에 체육관 정보가 존재하는지 확인
        assertThat(findOwner1.getGym()).contains(gym);
        //사장들의 리스트를 확인
        assertThat(owners).contains(findOwner1,findOwner2);




    }

}