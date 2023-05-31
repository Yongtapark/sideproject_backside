package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Team;
import com.backend.fitta.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BasicMemberInfo {

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

    public BasicMemberInfo(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.birthday = member.getBirthday();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();
        this.gender = member.getGender();
        this.height = member.getHeight();
        this.weight = member.getWeight();
        this.occupation = member.getOccupation();
        this.note = member.getNote();
        this.team = member.getTeam();
        this.gym = member.getGym();
    }
}
