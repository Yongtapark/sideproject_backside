package com.backend.fitta.service.gym;

import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.service.apiService.interfaces.OwnerApiService;
import com.backend.fitta.service.interfaces.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OwnerApiServiceImplTest {
    @Autowired
    OwnerService ownerService;
    @Autowired
    OwnerApiService ownerApiService;

    @Test
    void test(){
        Owner ownerA = new Owner("김사장", "000-0000-0000", "주소1", "사업자등록번호1");
        Owner ownerB = new Owner("이사장", "000-0000-0000", "주소1", "사업자등록번호1");
        Long ownerAId = ownerService.save(ownerA);
        Long ownerBId = ownerService.save(ownerB);
        Owner findOwner = ownerService.findById(ownerAId);
        //save
        assertThat(ownerA).isEqualTo(findOwner);
        //findById Exception
        assertThatThrownBy(()->ownerService.findById(11L)).isInstanceOf(OwnerNotFoundException.class);
        //FindAll
        assertThat(ownerService.findAll()).size().isEqualTo(2);
        //delete
        ownerService.delete(findOwner.getId());
        assertThatThrownBy(()->ownerService.findById(findOwner.getId())).isInstanceOf(OwnerNotFoundException.class);
    }

    @Test
    void apiTest() throws Exception{
        //given
        Owner ownerA = new Owner("김사장", "000-0000-0000", "주소1", "사업자등록번호1");
        Owner ownerB = new Owner("이사장", "000-0000-0000", "주소1", "사업자등록번호1");


        //when
        Long saved1 = ownerApiService.save(new BasicOwnerInfo(ownerA));
        Long saved2 = ownerApiService.save(new BasicOwnerInfo(ownerB));
        BasicOwnerInfo findOwner1 = ownerApiService.findById(saved1);
        BasicOwnerInfo findOwner2 = ownerApiService.findById(saved2);


        //then
        assertThat(ownerA.getName()).isEqualTo(findOwner1.getName());
        assertThat(ownerB.getName()).isEqualTo(findOwner2.getName());
        assertThatThrownBy(()->ownerApiService.findById(245L)).isInstanceOf(OwnerNotFoundException.class);




    }

}