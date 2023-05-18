package com.backend.fitta.service;

import com.backend.fitta.dto.team.FindTeamByIdResponse;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.repository.TeamRepository;
import com.backend.fitta.service.interfaces.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    @Override
    public Long save(SaveTeamRequest request) {
        Team team = new Team(request.getName());
        return teamRepository.save(team).getId();
    }

    @Override
    public Optional<FindTeamByIdResponse> findById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        return Optional.of(new FindTeamByIdResponse(team.getId(), team.getName(), team.getMembers(), team.getStaffs()));
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Long updateTeam(Long id, UpdateTeamRequest request) {
        Team findTeam = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        findTeam.changeTeamInfo(request.getName());
        return findTeam.getId();
    }

    @Override
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        teamRepository.delete(team);
    }
}
