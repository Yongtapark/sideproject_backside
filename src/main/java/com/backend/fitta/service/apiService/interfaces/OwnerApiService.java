package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.owner.BasicOwnerInfo;

public interface OwnerApiService {
    /**
     * 기본 CRUD API
     */
    BasicOwnerInfo findById(Long id);
    Result<BasicOwnerInfo> findAll();
    Long save(BasicOwnerInfo basicOwnerInfo);
    void delete(Long id);

    //update 구현필요




}
