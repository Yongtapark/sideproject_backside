package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.gym.Program;
import com.backend.fitta.entity.staff.Staff;

import java.util.List;

public interface ProgramService {
    Long save(Program program);
    Program findById(Long id);
    List<Program> findAll();
    List<Program> findAllByGymId(Long gymId);
    Long update(Long id, Program program);
    void delete(Long id);
}
