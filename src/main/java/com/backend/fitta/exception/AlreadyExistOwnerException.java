package com.backend.fitta.exception;

public class AlreadyExistOwnerException extends RuntimeException{
    public AlreadyExistOwnerException() {
        super("이미 존재하는 아이디입니다.");
    }

    public AlreadyExistOwnerException(String message) {
        super(message);
    }

    public AlreadyExistOwnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistOwnerException(Throwable cause) {
        super(cause);
    }
}
