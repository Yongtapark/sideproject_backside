package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface GymApiService {
    Long save(SaveGymRequest request);
    BasicGymInfo findById(Long id);
    Result<List<BasicGymInfo>> findAll();
    Long update(Long id, UpdateGymRequest request);
    void delete(Long id);

    void saveOwnerGym(long gymId, long ownerId);
/*    Page<BasicGymInfo> findAll (Pageable pageable);*/
}
