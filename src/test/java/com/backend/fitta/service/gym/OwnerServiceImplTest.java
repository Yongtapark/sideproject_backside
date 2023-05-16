package com.backend.fitta.service.gym;

import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.exception.OwnerNotFoundException;
import com.backend.fitta.service.interfaces.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OwnerServiceImplTest {
    @Autowired
    OwnerService ownerService;

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

}