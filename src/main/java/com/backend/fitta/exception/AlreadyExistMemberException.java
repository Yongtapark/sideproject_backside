package com.backend.fitta.exception;

public class AlreadyExistMemberException extends RuntimeException{
    public AlreadyExistMemberException() {
        super();
    }

    public AlreadyExistMemberException(String message) {
        super(message);
    }

    public AlreadyExistMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistMemberException(Throwable cause) {
        super(cause);
    }
}
