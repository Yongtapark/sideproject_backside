package com.backend.fitta.exception;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException() {
        super("팀을 찾을 수 없습니다.");
    }
}
