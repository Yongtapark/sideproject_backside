package com.backend.fitta.exception;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException() {
        super("오너를 찾을 수 없습니다.");
    }
}
