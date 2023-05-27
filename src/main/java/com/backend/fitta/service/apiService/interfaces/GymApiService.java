package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.gym.FindGymByIdResponse;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.entity.gym.Gym;

import java.util.List;

public interface GymApiService {
    Long save(SaveGymRequest request);
    FindGymByIdResponse findById(Long id);
    List<Gym> findAll();
    Long update(Long id, UpdateGymRequest request);
    void delete(Long id);
}
