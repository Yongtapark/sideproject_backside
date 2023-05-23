package com.backend.fitta.dto.team;

import lombok.Getter;

import java.util.List;


@Getter
public class FindTeamByIdResponse {

    private String name;
    private List<MemberTeamResponse> members;
    private List<StaffTeamResponse> staffs;

    public FindTeamByIdResponse(String name, List<MemberTeamResponse> members, List<StaffTeamResponse> staffs) {
        this.name = name;
        this.members = members;
        this.staffs = staffs;
    }
}