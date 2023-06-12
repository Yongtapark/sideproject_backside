package com.backend.fitta.dto.gym;

import com.backend.fitta.entity.enums.GenderDivision;
import com.backend.fitta.entity.gym.Owner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SaveGymRequest {

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
    @NotNull
    private Long ownerId;
    @NotNull
    private String businessIdentificationNumber;
}
