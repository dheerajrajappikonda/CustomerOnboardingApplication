package com.customeronboarding.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles @Valid body validation errors (like for DTO fields)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handles validation issues for @RequestParam, @PathVariable, etc.
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put("param", violation.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handles invalid date format errors specifically
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> handleDateParseException(DateTimeParseException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("dob", "Invalid date format. Please use yyyy-MM-dd.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handles malformed JSON or invalid type conversion
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidJson(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException ife) {
            String field = ife.getPath().isEmpty() ? "unknown" : ife.getPath().get(0).getFieldName();
            error.put("field", field);
            error.put("error", "Invalid value: " + ife.getValue() + ". Expected type: " + ife.getTargetType().getSimpleName());
        } else {
            error.put("error", "Malformed JSON request or invalid data format.");
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handles all other uncaught exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        ex.printStackTrace(); // logs stack trace for debugging
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
