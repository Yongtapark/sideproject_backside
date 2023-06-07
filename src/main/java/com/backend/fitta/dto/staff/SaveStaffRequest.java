package com.backend.fitta.dto.staff;

import com.backend.fitta.entity.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveStaffRequest {
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthdate;
    @NotNull
    private Gender gender;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
    @Nullable
    @Schema(nullable = true)
    private Long teamId;
    @Nullable
    @Schema(nullable = true)
    private Long gymId;
}


