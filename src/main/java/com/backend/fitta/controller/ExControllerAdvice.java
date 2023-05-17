package com.backend.fitta.controller;

import com.backend.fitta.exception.AlreadyExistMemberException;
import com.backend.fitta.exception.ErrorResult;
import com.backend.fitta.exception.MemberNotFoundException;
import com.backend.fitta.exception.PWNotCorrespondException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult illegalExHandle(MethodArgumentNotValidException e) {
        return new ErrorResult(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        return new ErrorResult(e.getMessage());
    }

    @ExceptionHandler({MemberNotFoundException.class, AlreadyExistMemberException.class, PWNotCorrespondException.class})
    public ResponseEntity<ErrorResult> handleMemberNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(e.getMessage()));
    }
}
