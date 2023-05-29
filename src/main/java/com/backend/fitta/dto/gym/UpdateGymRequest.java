package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateGymRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
    private GenderDivision genderDivision;
}
