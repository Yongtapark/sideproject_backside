package com.backend.fitta.repository;

import com.backend.fitta.dto.gym.StaffGymResponse;
import com.backend.fitta.dto.team.StaffTeamResponse;

import java.util.List;

public interface StaffRepositoryCustom {
    List<StaffTeamResponse> searchTeamStaffList(Long teamId);
    List<StaffGymResponse> searchGymStaffList(Long gymId);
}
