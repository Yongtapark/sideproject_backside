package com.backend.fitta.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SaveScheduleRequest {

    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @NotNull
    private LocalDate date;

    @NotNull
    private Long staffId;

}
