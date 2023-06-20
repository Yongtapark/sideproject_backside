package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.GymProfileInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GymApiService {
    Long save(SaveGymRequest request, List<MultipartFile> images) throws IOException;
    BasicGymInfo findById(Long id);
    Result<List<BasicGymInfo>> findAll();
    Long update(Long id, UpdateGymRequest request, List<MultipartFile> images) throws IOException;
    void delete(Long id);

    void saveOwnerGym(long gymId, long ownerId);
    Page<GymProfileInfo> findAll (Pageable pageable);
}
