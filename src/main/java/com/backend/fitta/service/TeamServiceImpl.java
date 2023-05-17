package com.backend.fitta.service;

import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.repository.TeamRepository;
import com.backend.fitta.service.interfaces.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    @Override
    public Long save(Team team) {
        return teamRepository.save(team).getId();
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(()->new TeamNotFoundException());
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Long update(Long id, Team team) {
        Team findTeam = findById(id);
        findTeam.changeTeamInfo(team.getName());
        return findTeam.getId();
    }

    @Override
    public void delete(Long id) {
        Team findTeam = findById(id);
        teamRepository.delete(findTeam);
    }
}
