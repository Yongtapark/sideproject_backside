package com.backend.fitta.service;

import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.exception.StaffNotFoundException;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.service.interfaces.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Transactional
@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    @Override
    public Long save(Staff staff) {
        return staffRepository.save(staff).getId();
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id).orElseThrow(()->new StaffNotFoundException());
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Long update(Long id, Staff staff) {
        Staff findStaff = findById(id);
        findStaff.changeStaffInfo(staff.getName(),staff.getBirthday(),staff.getPhoneNumber(), staff.getAddress(),staff.getGrade());
        return findStaff.getId();
    }

    @Override
    public void delete(Long id) {
        Staff findStaff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        staffRepository.delete(findStaff);
    }
}
