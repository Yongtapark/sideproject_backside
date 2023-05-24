package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;

import java.util.List;

public interface OwnerApiService {
    /**
     * 기본 CRUD API
     */
    BasicOwnerInfo findById(Long id);
    Result<List<BasicOwnerInfo>> findAll();
    Long save(BasicOwnerInfo basicOwnerInfo);
    BasicOwnerInfo update(Long id, BasicOwnerInfo updatedOwnerInfo);




}
