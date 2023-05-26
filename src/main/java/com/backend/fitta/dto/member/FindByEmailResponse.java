package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class FindByEmailResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;
    private String password;
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private Long height;
    private Long weight;
    private String occupation;
    private String note;
    private Team team;
    private Gym gym;

    ///private String name;
    //    private List<MemberTeamResponse> members;
    //    private List<StaffTeamResponse> staffs;
    //
    //    public FindTeamByIdResponse(String name, List<MemberTeamResponse> members, List<StaffTeamResponse> staffs) {
    //        this.name = name;
    //        this.members = members;
    //        this.staffs = staffs;
    //    }
}
