package ru.skypro.homework.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<?> notFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> unauthorizedException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<?> forbiddenException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}