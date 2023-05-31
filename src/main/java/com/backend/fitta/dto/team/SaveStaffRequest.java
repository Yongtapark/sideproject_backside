package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import com.backend.fitta.entity.enums.Grade;
import com.backend.fitta.entity.gym.Gym;
import com.backend.fitta.entity.gym.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SaveStaffRequest {
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private Gender gender;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
    @NotNull
    private Grade grade;
    private Team team;
    private Gym gym;
}


