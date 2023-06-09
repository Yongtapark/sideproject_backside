package com.backend.fitta.service.interfaces;

import com.backend.fitta.dto.team.BasicTeamInfo;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.entity.gym.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    Long save(SaveTeamRequest request);
    Optional<BasicTeamInfo> findById(Long id);
    List<Team> findAll();
    Long updateTeam(Long id, UpdateTeamRequest request);
    void deleteTeam(Long id);
}
