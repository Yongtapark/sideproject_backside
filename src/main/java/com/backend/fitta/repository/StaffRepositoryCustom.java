package com.backend.fitta.repository;

import com.backend.fitta.dto.team.StaffTeamResponse;

import java.util.List;

public interface StaffRepositoryCustom {
    List<StaffTeamResponse> searchStaffList(Long teamId);
}
