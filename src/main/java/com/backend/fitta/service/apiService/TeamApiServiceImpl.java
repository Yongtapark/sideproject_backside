package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.team.*;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.repository.MemberRepository;
import com.backend.fitta.repository.StaffRepository;
import com.backend.fitta.repository.TeamRepository;
import com.backend.fitta.service.apiService.interfaces.TeamApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class TeamApiServiceImpl implements TeamApiService {
    private final TeamRepository teamRepository;
    private final StaffRepository staffRepository;
    private final MemberRepository memberRepository;
    @Override
    public Long save(SaveTeamRequest request) {
        Team team = new Team(request.getName());
        return teamRepository.save(team).getId();
    }


    @Override
    public FindTeamByIdResponse findById(Long id) {
        List<MemberTeamResponse> memberList = memberRepository.searchTeamMemberList(id);
        List<StaffTeamResponse> staffList = staffRepository.searchTeamStaffList(id);
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        return new FindTeamByIdResponse(team.getName(), memberList, staffList);
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
