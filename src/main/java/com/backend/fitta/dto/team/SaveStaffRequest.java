package com.backend.fitta.dto.team;

import com.backend.fitta.entity.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

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
    @Nullable
    @Schema(nullable = true)
    private Long teamId;
    @Nullable
    @Schema(nullable = true)
    private Long gymId;
}


