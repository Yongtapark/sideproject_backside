package com.backend.fitta.dto.team;

import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.member.Member;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SaveTeamRequest {
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Staff> staffs =new ArrayList<>();

    public SaveTeamRequest(String name) {
        this.name = name;
    }

}
