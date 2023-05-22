package com.backend.fitta.dto.team;

import com.backend.fitta.entity.gym.Staff;
import lombok.Getter;

import java.util.List;


@Getter
public class FindTeamByIdResponse {

    private String name;
    private List<MemberTeamResponse> members;
    private List<Staff> staffs;

    public FindTeamByIdResponse(String name, List<MemberTeamResponse> members, List<Staff> staffs) {
        this.name = name;
        this.members = members;
        this.staffs = staffs;
    }
}