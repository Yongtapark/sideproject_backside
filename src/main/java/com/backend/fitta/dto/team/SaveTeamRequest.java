package com.backend.fitta.dto.team;

import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.member.Member;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SaveTeamRequest {
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Staff> staffs =new ArrayList<>();

}
