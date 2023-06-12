package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.staff.BasicStaffInfo;
import com.backend.fitta.dto.staff.SaveStaffRequest;
import com.backend.fitta.dto.staff.UpdateStaffRequest;

import java.util.List;

public interface StaffApiService {
    Long save(SaveStaffRequest request);
    BasicStaffInfo findById(Long id);
    Result<List<BasicStaffInfo>> findAll();
    Long update(Long id, UpdateStaffRequest request);
    void delete(Long id);
    void saveTeamStaff(long staffId, long teamId);

    void saveGymStaff(long staffId, long gymId);
}