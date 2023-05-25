package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.team.FindStaffByIdResponse;
import com.backend.fitta.dto.team.SaveStaffRequest;
import com.backend.fitta.dto.team.UpdateStaffRequest;
import com.backend.fitta.entity.gym.Staff;

import java.util.List;

public interface StaffApiService {
    Long save(SaveStaffRequest request);
    FindStaffByIdResponse findById(Long id);
    List<Staff> findAll();
    Long update(Long id, UpdateStaffRequest request);
    void delete(Long id);
    void saveTeamStaff(long staffId, long teamId);

}