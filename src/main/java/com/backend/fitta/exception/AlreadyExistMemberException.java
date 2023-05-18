package com.backend.fitta.exception;

public class AlreadyExistMemberException extends RuntimeException{
    public AlreadyExistMemberException() {
        super("이미 존재하는 아이디입니다.");
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
