package com.backend.fitta.dto.team;

import com.backend.fitta.entity.gym.Staff;
import com.backend.fitta.entity.member.Member;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Getter
public class FindTeamByIdResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "team")
    private List<Staff> staffs =new ArrayList<>();
}
