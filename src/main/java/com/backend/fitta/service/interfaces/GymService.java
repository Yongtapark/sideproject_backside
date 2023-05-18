package com.backend.fitta.service.interfaces;

import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Staff;

import java.util.List;

public interface GymService {
    Long save(Gym gym);
    Gym findById(Long id);
    List<Gym> findAll();
    Long update(Long id, Gym gym);
    void delete(Long id);
}
