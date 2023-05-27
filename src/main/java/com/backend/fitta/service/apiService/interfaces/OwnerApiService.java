package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;
import com.backend.fitta.dto.owner.SignUpOwnerRequest;

import java.util.List;

public interface OwnerApiService {
    /**
     * 기본 CRUD API
     */
    Long save(SignUpOwnerRequest request);

    BasicOwnerInfo findById(Long id);

    Result<List<BasicOwnerInfo>> findAll();

    BasicOwnerInfo update(Long id, BasicOwnerInfo updatedOwnerInfo);


}
