package com.backend.fitta.exception;

public class StaffNotFoundException extends RuntimeException{
    public StaffNotFoundException() {
        super("직원을 찾을 수 없습니다.");
    }
}
