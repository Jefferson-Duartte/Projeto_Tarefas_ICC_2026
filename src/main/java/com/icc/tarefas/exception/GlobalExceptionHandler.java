package com.icc.tarefas.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class GlobalExceptionHandler {
    private ResponseEntity<ExceptionResponse> buildResponseEntity(Exception exception, HttpServletRequest request, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getRequestURI(),
                status.value()
        );

        return ResponseEntity.status(status).body(response);

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleGenericException(Exception exception, HttpServletRequest request) {
        return buildResponseEntity(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        return buildResponseEntity(exception, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        String errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                errors,
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessException(
            IllegalArgumentException exception,
            HttpServletRequest request) {

        return buildResponseEntity(exception, request, HttpStatus.BAD_REQUEST);
    }
}
