package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateGymRequest {
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String phoneNumber;
    @NotBlank
    @NotNull
    private String address;
    @NotNull
    private GenderDivision genderDivision;
}
