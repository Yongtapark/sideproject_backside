package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MemberTeamResponse {
    private String email;
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private String address;
    private Gender gender;

    @QueryProjection
    public MemberTeamResponse(String email, String name, Gender gender, String address, String phoneNumber, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }


    public MemberTeamResponse(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.birthday = member.getBirthday();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();
        this.gender = member.getGender();
    }
}
