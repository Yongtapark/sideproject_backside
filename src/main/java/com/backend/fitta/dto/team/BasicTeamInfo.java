package com.backend.fitta.dto.team;

import com.backend.fitta.entity.gym.Team;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class BasicTeamInfo {

    private String name;
    private List<SimpleMemberInfo> members;
    private Long staffId;

    public BasicTeamInfo(Team team) {
        this.name = team.getName();
        this.members = team.getMembers().stream()
                .map(member -> new SimpleMemberInfo(member))
                .collect(Collectors.toList());
        this.staffId=team.getStaff().getId();
    }
}