package com.backend.fitta.service.interfaces;

import com.backend.fitta.dto.gym.FindGymByIdResponse;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.entity.gym.Gym;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GymApiService {
    Long save(SaveGymRequest request);
    FindGymByIdResponse findById(Long id);
    List<Gym> findAll();
    Long update(Long id, UpdateGymRequest request);
    void delete(Long id);
}
