package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.BasicGymInfo;
import com.backend.fitta.dto.gym.GymProfileInfo;
import com.backend.fitta.dto.gym.SaveGymRequest;
import com.backend.fitta.dto.gym.UpdateGymRequest;
import com.backend.fitta.dto.member.SignUpRequest;
import com.backend.fitta.dto.program.ProgramInfo;
import com.backend.fitta.dto.program.SignUpProgram;
import com.backend.fitta.entity.gym.Program;
import com.backend.fitta.repository.gym.GymSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GymApiService {
    Long save(SaveGymRequest request,MultipartFile profileImage, MultipartFile backgroundImage) throws IOException;
    BasicGymInfo findById(Long id);
    void delete(Long id);

    void saveOwnerGym(long gymId, long ownerId);
    Page<GymProfileInfo> findAll (Pageable pageable);
    Page<GymProfileInfo> findSearch (GymSearchCond cond, Pageable pageable);

    Long update(Long gymId, UpdateGymRequest request, MultipartFile profileImage, MultipartFile backgroundImage) throws IOException;

    void createClasses(SignUpProgram programInfo);

}