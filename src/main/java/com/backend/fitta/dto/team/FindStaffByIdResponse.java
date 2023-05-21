package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Grade;
import com.backend.fitta.entity.gym.Staff;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class FindStaffByIdResponse {
    private Long id;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private Grade grade;
}

