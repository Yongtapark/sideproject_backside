package com.backend.fitta.exception;

public class PWNotCorrespondException extends RuntimeException{
    public PWNotCorrespondException() {
        super("비밀번호가 일치하지 않습니다.");
    }

    public PWNotCorrespondException(String message) {
        super(message);
    }

    public PWNotCorrespondException(String message, Throwable cause) {
        super(message, cause);
    }

    public PWNotCorrespondException(Throwable cause) {
        super(cause);
    }
}
