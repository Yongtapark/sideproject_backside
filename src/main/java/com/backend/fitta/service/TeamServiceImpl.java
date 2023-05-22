package com.backend.fitta.service;

import com.backend.fitta.dto.team.FindTeamByIdResponse;
import com.backend.fitta.dto.team.MemberTeamResponse;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.repository.MemberRepository;
import com.backend.fitta.repository.TeamRepository;
import com.backend.fitta.service.interfaces.TeamApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamServiceImpl implements TeamApiService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    @Override
    public Long save(SaveTeamRequest request) {
        Team team = new Team(request.getName());
        return teamRepository.save(team).getId();
    }


    @Override
    public FindTeamByIdResponse findById(Long id) {
        List<MemberTeamResponse> result = memberRepository.search(id);
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        return new FindTeamByIdResponse(team.getName(), result, null);
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
