package com.backend.fitta.exception;

public class ProgramNotFoundException extends RuntimeException{
    public ProgramNotFoundException() {
        super("프로그램이 존재하지 않습니다.");
    }

    public ProgramNotFoundException(String message) {
        super(message);
    }

    public ProgramNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgramNotFoundException(Throwable cause) {
        super(cause);
    }

}
