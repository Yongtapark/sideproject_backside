package com.backend.fitta.exception;

public class RegistrationsNotFoundException extends RuntimeException{
    public RegistrationsNotFoundException() {
        super("등록정보가 존재하지 않습니다.");
    }

    public RegistrationsNotFoundException(String message) {
        super(message);
    }

    public RegistrationsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationsNotFoundException(Throwable cause) {
        super(cause);
    }

}
