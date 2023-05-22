package com.backend.fitta.service;

import com.backend.fitta.dto.team.FindStaffByIdResponse;
import com.backend.fitta.dto.team.SaveStaffRequest;
import com.backend.fitta.dto.team.UpdateStaffRequest;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.exception.StaffNotFoundException;
import com.backend.fitta.repository.StaffRepository;
import com.backend.fitta.service.interfaces.StaffApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Transactional
@Service
public class StaffApiServiceImpl implements StaffApiService {
    private final StaffRepository staffRepository;

    @Override
    public Long save(SaveStaffRequest request) {
        Staff staff = new Staff(request.getName(),request.getBirthday(),request.getGender(),request.getPhoneNumber(),request.getAddress(),request.getGrade(),request.getGym(), request.getTeam());
        return staffRepository.save(staff).getId();
    }

    @Override
    public FindStaffByIdResponse findById(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        return new FindStaffByIdResponse(staff.getName(), staff.getBirthday(), staff.getGender(), staff.getPhoneNumber(), staff.getAddress(), staff.getGrade());
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Long update(Long id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        staff.changeStaffInfo(request.getName(),request.getBirthday(),request.getPhoneNumber(), request.getAddress(),request.getGrade());
        return staff.getId();
    }

    @Override
    public void delete(Long id) {
        Staff findStaff = staffRepository.findById(id).orElseThrow(() -> new StaffNotFoundException());
        staffRepository.delete(findStaff);
    }
}
