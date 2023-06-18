package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.staff.BasicStaffInfo;
import com.backend.fitta.dto.staff.SaveStaffRequest;
import com.backend.fitta.dto.staff.UpdateStaffRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StaffApiService {
    Long save(SaveStaffRequest request);
    BasicStaffInfo findById(Long id);
    Result<List<BasicStaffInfo>> findAll();
    Long update(Long id, UpdateStaffRequest request, MultipartFile multipartFile) throws IOException;
    void delete(Long id);
    void saveTeamStaff(long staffId, long teamId);

    void saveGymStaff(long staffId, long gymId);
}