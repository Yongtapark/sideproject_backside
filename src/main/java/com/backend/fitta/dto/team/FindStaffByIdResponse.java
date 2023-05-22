package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FindStaffByIdResponse {
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private Grade grade;
}

