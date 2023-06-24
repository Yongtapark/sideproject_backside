package com.backend.fitta.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateScheduleRequest {
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;
    @NotNull
    private LocalDate date;
    private Long staffId;
    public UpdateScheduleRequest(String startTime, String endTime, LocalDate date,Long staffId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.staffId = staffId;
    }
}
