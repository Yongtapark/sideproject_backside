package com.backend.fitta.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
        super("스케줄을 찾을 수 없습니다.");
    }

}
