package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SimpleMemberInfo {
    private Long id;
    private String email;
    private String name;
    private LocalDate birthdate;
    private String phoneNumber;
    private String address;
    private Gender gender;


    public SimpleMemberInfo(Member member) {
        this.id=member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.birthdate = member.getBirthdate();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();
        this.gender = member.getGender();
    }
}
