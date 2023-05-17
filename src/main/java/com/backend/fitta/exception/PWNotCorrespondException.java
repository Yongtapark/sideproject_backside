package com.backend.fitta.exception;

public class PWNotCorrespondException extends RuntimeException{
    public PWNotCorrespondException() {
        super();
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
