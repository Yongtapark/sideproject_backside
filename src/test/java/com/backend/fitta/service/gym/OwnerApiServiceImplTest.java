package com.backend.fitta.service.gym;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import com.backend.fitta.service.interfaces.GymApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
    OwnerApiService ownerApiService;
    @Autowired
    GymApiService gymApiService;

    @Test
    void test(){
        Owner ownerA = new Owner("김사장", "000-0000-0000", "주소1", "사업자등록번호1");
        Owner ownerB = new Owner("이사장", "000-0000-0000", "주소1", "사업자등록번호1");
        //save
        Long ownerAId = ownerService.save(ownerA);
        Long ownerBId = ownerService.save(ownerB);
        //update
        Owner update = ownerService.update(ownerBId, new Owner("수정사장", "수정번호", "수정주소", "수정번호"));
        log.info("confirm={}",update.getName());
        //findById
        Owner findOwner = ownerService.findById(ownerAId);
        assertThat(ownerA).isEqualTo(findOwner);
        //findByIdWithUpdate
        assertThat(ownerService.findById(ownerBId).getName()).isEqualTo("수정사장");

        //findById Exception
        assertThatThrownBy(()->ownerService.findById(11L)).isInstanceOf(OwnerNotFoundException.class);
        //FindAll
        assertThat(ownerService.findAll()).size().isEqualTo(2);
        //delete
        ownerService.delete(findOwner.getId());
        assertThatThrownBy(()->ownerService.findById(findOwner.getId())).isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    @Rollback(false)
    void apiTest() throws Exception{
        //given
        // 사장 정보를 생성
        Owner ownerA = new Owner("김사장", "000-0000-0000", "주소1", "사업자등록번호1");
        Owner ownerB = new Owner("이사장", "000-0000-0000", "주소1", "사업자등록번호1");

        //when
        // 사장 정보를 저장
        Long saved1 = ownerApiService.save(new BasicOwnerInfo(ownerA));
        Long saved2 = ownerApiService.save(new BasicOwnerInfo(ownerB));
        BasicOwnerInfo update = ownerApiService.update(saved2, new BasicOwnerInfo(new Owner("수정된사장", "수정된번호", "수정된주소", "수정된사업자번호")));


        // DB에서 저장된 사장 정보를 조회
        Owner savedOwner1 = ownerService.findById(saved1);
        Owner savedOwner2 = ownerService.findById(saved2);

        //사장2의 정보를 수정
        log.info("update={}",update);

        // 저장된 사장1의 정보로 헬스장을 생성
        Gym gym = new Gym("testGym", savedOwner1, "02-1234-1242", "testGymAddress", GenderDivision.UNISEX);
        // 헬스장 정보 저장
        gymApiService.save(gym);

        // 헬스장 정보를 저장한 후 다시 사장 정보를 조회
        savedOwner1=ownerService.findById(saved1);
        savedOwner2=ownerService.findById(saved2);

        BasicOwnerInfo findOwner1 = ownerApiService.findById(savedOwner1.getId());
        BasicOwnerInfo findOwner2 = ownerApiService.findById(savedOwner2.getId());
        // 사장 전체 조회
        Result<List<BasicOwnerInfo>> owners = ownerApiService.findAll();

        //then
        //저장된 사장의 이름과 조회된 사장의 이름이 같은지 확인
        assertThat(ownerA.getName()).isEqualTo(findOwner1.getName());

        assertThat(findOwner2.getName()).isEqualTo("수정된사장");
//        //존재하지 않는 사장 정보를 조회하려 할 때 예외가 발생하는지 확인
//        assertThatThrownBy(()->ownerApiService.findById(245L)).isInstanceOf(OwnerNotFoundException.class);
//        //사장1의 gymList 에 체육관 정보가 존재하는지 확인
        assertThat(findOwner1.getGymList()).contains(new BasicGymInfo(gym));
        //사장들의 리스트를 확인
        assertThat(owners.getData()).contains(findOwner1,findOwner2);




    }

}