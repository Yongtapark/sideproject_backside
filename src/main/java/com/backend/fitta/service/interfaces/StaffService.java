package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.gym.Owner;
import com.backend.fitta.entity.gym.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffService{
    Long save(Staff staff);
    Staff findById(Long id);
    List<Staff> findAll();
    Long update(Long id, Staff staff);
    void delete(Long id);

}
