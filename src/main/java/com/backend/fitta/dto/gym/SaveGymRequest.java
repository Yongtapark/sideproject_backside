package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Owner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaveGymRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
    private GenderDivision genderDivision;
    @NotNull
    private Long ownerId;
}
