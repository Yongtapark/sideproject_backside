package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.program.ProgramInfo;
import com.backend.fitta.entity.gym.Program;

import java.util.List;

public interface ProgramApiService {
    Long save(ProgramInfo programInfo);
    ProgramInfo findById(Long id);
    List<ProgramInfo> findAll();
    List<ProgramInfo> findAllByGymId(Long gymId);
    Long update(Long id, ProgramInfo programInfo);
    void delete(Long id);
}
