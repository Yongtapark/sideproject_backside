package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.GymProfileInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.repository.gym.GymSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GymApiService {
    Long save(SaveGymRequest request);
    BasicGymInfo findById(Long id);

    Long update(Long id, UpdateGymRequest request, List<MultipartFile> images) throws IOException;
    Result<List<BasicGymInfo>> findAll();


    void delete(Long id);

    void saveOwnerGym(long gymId, long ownerId);
    Page<GymProfileInfo> findAll (Pageable pageable);
    Page<GymProfileInfo> findSearch (GymSearchCond cond, Pageable pageable);
}
