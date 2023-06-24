package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.gym.Program;
import com.backend.fitta.entity.gym.Registrations;

import java.util.List;

public interface RegistrationsService {
    Long save(Registrations registrations);
    Registrations findById(Long id);
    List<Registrations> findAll();
    Long update(Long id, Registrations registrations);
    void delete(Long id);
}
