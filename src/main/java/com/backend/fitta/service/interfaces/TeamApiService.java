package com.backend.fitta.service.interfaces;

import com.backend.fitta.dto.team.FindTeamByIdResponse;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.gym.Team;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TeamApiService {
    Long save(SaveTeamRequest request);
    FindTeamByIdResponse findById(Long id);

    List<Team> findAll();
    Long updateTeam(Long id, UpdateTeamRequest request);
    void deleteTeam(Long id);
}
