package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Grade;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StaffTeamResponse {
    private String name;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private Grade grade;
    private LocalDate birthday;

    @QueryProjection
    public StaffTeamResponse(String name, LocalDate birthday, Gender gender, String phoneNumber, String address, Grade grade) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.grade = grade;
    }


}
