package com.backend.fitta.service.apiService.interfaces;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.team.BasicTeamInfo;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.entity.gym.Team;

import java.util.List;

public interface TeamApiService {
    Long save(SaveTeamRequest request);
    BasicTeamInfo findById(Long id);

    Result<List<BasicTeamInfo>> findAll();
    Long updateTeam(Long id, UpdateTeamRequest request);
    void deleteTeam(Long id);
}
