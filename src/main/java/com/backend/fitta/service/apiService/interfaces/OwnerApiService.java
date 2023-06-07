package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.gym.OwnerAllGymInfoResponse;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;
import com.backend.fitta.dto.owner.UpdateOwnerRequest;

import java.util.List;

public interface OwnerApiService {
    /**
     * 기본 CRUD API
     */
    Long save(SignUpOwnerRequest request);

    BasicOwnerInfo findById(Long id);

    Result<List<BasicOwnerInfo>> findAll();

    Long update(Long id, UpdateOwnerRequest request);


    void deleteOwner(Long id);


    /**
     * 관리자페이지
     */

    OwnerAllGymInfoResponse ownerAllGymInfo(Long OwnerId);


}
