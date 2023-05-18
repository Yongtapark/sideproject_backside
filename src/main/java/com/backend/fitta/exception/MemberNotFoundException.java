package com.backend.fitta.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException() {
        super("회원이 존재하지 않습니다.");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }

}
