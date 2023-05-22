package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Grade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class UpdateStaffRequest {
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthday;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
    @NotNull
    private Grade grade;
}
