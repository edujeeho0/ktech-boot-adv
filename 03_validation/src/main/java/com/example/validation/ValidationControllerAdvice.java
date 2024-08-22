package com.example.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationError(
            MethodArgumentNotValidException exception
    ) {
        Map<String, Object> errors = new HashMap<>();
        List<FieldError> fieldErrors =
                exception.getFieldErrors();
        for (FieldError error: fieldErrors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        }
        return errors;
    }
}
