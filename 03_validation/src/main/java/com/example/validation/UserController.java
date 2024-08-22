package com.example.validation;

import com.example.validation.dto.NotSeriesDto;
import com.example.validation.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
    @PostMapping
    public Map<String, String> create(
            @Valid
            @RequestBody
            UserDto dto
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "200");
        response.put("message", "OK");
        return response;
    }

    @PostMapping("not")
    public Map<String, String> testNot(
            @Valid
            @RequestBody
            NotSeriesDto dto
    )  {
        Map<String, String> response = new HashMap<>();
        response.put("status", "200");
        response.put("message", "OK");
        return response;
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, Object> handleValidationError(
//            MethodArgumentNotValidException exception
//    ) {
//        Map<String, Object> errors = new HashMap<>();
//        List<FieldError> fieldErrors =
//                exception.getFieldErrors();
//        for (FieldError error: fieldErrors) {
//            String field = error.getField();
//            String message = error.getDefaultMessage();
//            errors.put(field, message);
//        }
//        return errors;
//    }
}
