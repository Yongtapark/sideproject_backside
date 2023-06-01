package com.backend.fitta.dto.schedule;

import com.backend.fitta.entity.gym.Staff;
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
    private Staff staff;
    public UpdateScheduleRequest(String startTime, String endTime, LocalDate date,Staff staff) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.staff = staff;
    }
}
