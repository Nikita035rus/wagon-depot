//package com.wagondepot.exception;
//
//import com.example.tuktuk.service.MailService;
//import io.sentry.Sentry;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//@RequiredArgsConstructor
//public class ExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<?> handleNullPointerException(Exception ex) {
//        mailService.send("tishkas.joggers@gmail.com", "NullPointerException", ex.getMessage());
//        Sentry.captureException(ex);
//        Map<String, String> errors = new LinkedHashMap<>();
//        errors.put("status", HttpStatus.I_AM_A_TEAPOT.name());
//        errors.put("massage", ex.getMessage());
//        errors.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errors);
//    }
//    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleException(Exception ex) {
//        mailService.send("tishkas.joggers@gmail.com", "Exception", ex.getMessage());
//        Sentry.captureException(ex);
//        Map<String, String> errors = new LinkedHashMap<>();
//        errors.put("status", HttpStatus.BAD_REQUEST.name());
//        errors.put("massage", ex.getMessage());
//        errors.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errors);
//    }
//}
