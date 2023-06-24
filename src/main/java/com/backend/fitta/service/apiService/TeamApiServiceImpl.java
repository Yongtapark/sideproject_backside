package com.backend.fitta.service.apiService;

import com.backend.fitta.dto.Result;
import com.backend.fitta.dto.team.BasicTeamInfo;
import com.backend.fitta.dto.team.SaveTeamRequest;
import com.backend.fitta.dto.team.UpdateTeamRequest;
import com.backend.fitta.entity.staff.Staff;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.exception.StaffNotFoundException;
import com.backend.fitta.exception.TeamNotFoundException;
import com.backend.fitta.repository.staff.StaffRepository;
import com.backend.fitta.repository.team.TeamRepository;
import com.backend.fitta.service.apiService.interfaces.TeamApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TeamApiServiceImpl implements TeamApiService {
    private final TeamRepository teamRepository;
    private final StaffRepository staffRepository;
    @Override
    public Long save(SaveTeamRequest request) {
        Staff staff = staffRepository.findById(request.getStaffId()).orElseThrow(() -> new StaffNotFoundException());
        Team team = new Team(request.getName(),staff);
        return teamRepository.save(team).getId();
    }


    @Override
    public BasicTeamInfo findById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        return new BasicTeamInfo(team);
    }

    @Override
    public Result<List<BasicTeamInfo>> findAll() {
        List<Team> all = teamRepository.findAll();
        List<BasicTeamInfo> collect = all.stream()
                .map(T -> new BasicTeamInfo(T))
                .collect(Collectors.toList());
        return new Result(collect);
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
