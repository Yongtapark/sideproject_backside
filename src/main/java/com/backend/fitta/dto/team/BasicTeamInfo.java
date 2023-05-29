package com.backend.fitta.dto.team;

import com.backend.fitta.entity.gym.Team;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class BasicTeamInfo {

    private String name;
    private List<MemberTeamResponse> members;
    private List<StaffTeamResponse> staffs;

    public BasicTeamInfo(Team team) {
        this.name = team.getName();
        this.members = team.getMembers().stream()
                .map(member -> new MemberTeamResponse(member))
                .collect(Collectors.toList());

        this.staffs = staffs;
    }
}