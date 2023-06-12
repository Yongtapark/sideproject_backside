package com.backend.fitta.dto.member;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Role;
import com.backend.fitta.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BasicMemberInfo {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDate birthdate;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private Long height;
    private Long weight;
    private String occupation;
    private String note;
    private String teamName;
    private String gymName;
    private Role role;

    public BasicMemberInfo(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.birthdate = member.getBirthdate();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();
        this.gender = member.getGender();
        this.height = member.getHeight();
        this.weight = member.getWeight();
        this.occupation = member.getOccupation();
        this.note = member.getNote();
        if(member.getTeam()!=null){
            this.teamName = member.getTeam().getName();
        }
        if (member.getGym()!=null){
            this.gymName = member.getGym().getName();
        }
        this.role = member.getRole();
    }
}
