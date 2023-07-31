package com.wagondepot.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchCustomerException.class)
    public ResponseEntity<?> handleNoSuchCustomerException(NoSuchCustomerException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("status", HttpStatus.NOT_FOUND.name());
        errors.put("massage", ex.getMessage());
        errors.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(Exception ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("status", HttpStatus.I_AM_A_TEAPOT.name());
        errors.put("massage", ex.getMessage());
        errors.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errors);
    }
}
