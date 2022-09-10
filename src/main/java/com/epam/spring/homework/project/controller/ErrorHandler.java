package com.epam.spring.homework.project.controller;

import com.epam.spring.homework.project.exception.ReportNotFoundException;
import com.epam.spring.homework.project.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ReportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleReportNotFoundException(ReportNotFoundException ex) {
        log.error("handleReportNotFoundException: exception message {}", ex.getMessage(), ex);
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleUserNotFoundException(UserNotFoundException ex) {
        log.error("handleUserNotFoundException: exception message {}", ex.getMessage(), ex);
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentIsNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentIsNotValidException: exception message {}", ex.getMessage(), ex);
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new Error(err.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
