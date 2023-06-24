package com.backend.fitta.dto.staff;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class UpdateStaffRequest {
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthdate;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
}

