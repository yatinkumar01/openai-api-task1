package com.example.codeConverter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetails> appExceptionHandler(AppException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handles NullPointerException and returns a ResponseEntity with error details and HTTP status BAD_REQUEST.
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetails> exceptionHandler2(NullPointerException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    // Handles NoHandlerFoundException and returns a ResponseEntity with error details and HTTP status BAD_REQUEST.
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionHandler4(NoHandlerFoundException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    // Handles MethodArgumentNotValidException and returns a ResponseEntity with error details and HTTP status NOT_FOUND.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException customerException) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), "Validation Error", customerException.getBindingResult().getFieldError().getDefaultMessage()), HttpStatus.NOT_FOUND);
    }

    // Handles any other unhandled exceptions and returns a ResponseEntity with error details and HTTP status BAD_REQUEST.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler3(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

