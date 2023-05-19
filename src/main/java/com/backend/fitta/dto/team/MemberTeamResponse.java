package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    public MemberTeamResponse(String email, String name, LocalDate birthday, String phoneNumber, String address, Gender gender) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MemberTeamResponse{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                '}';
    }
}
