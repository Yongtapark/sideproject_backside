package com.backend.fitta.dto.schedule;

import com.backend.fitta.entity.gym.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BasicScheduleInfo {

    private String startTime;
    private String endTime;
    private LocalDate date;
    private String staffName;

    public BasicScheduleInfo(Schedule schedule) {
        this.startTime = schedule.getStartTime();
        this.endTime = schedule.getEndTime();
        this.date = schedule.getDate();
        if (schedule.getStaff() != null) {
            this.staffName = schedule.getStaff().getName();
        }
    }
}
