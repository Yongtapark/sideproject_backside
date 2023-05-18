package com.backend.fitta.exception;

public class GymNotFoundException extends RuntimeException{
    public GymNotFoundException() {
        super("체육관을 찾을 수 없습니다.");
    }
}
