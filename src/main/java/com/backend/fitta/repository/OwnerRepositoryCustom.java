package com.backend.fitta.repository;

import com.backend.fitta.dto.owner.GymOwnerResponse;

import java.util.List;

public interface OwnerRepositoryCustom {
    List<GymOwnerResponse> searchOwnerGymList(Long teamId);
}
