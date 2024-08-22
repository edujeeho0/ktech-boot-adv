package com.example.file;

import com.example.file.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalControllerAdvice {
    // ExceptionHandler는 해당 컨트롤러 내부에서만 동작한다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgument(
            final IllegalArgumentException exception
    ) {
        System.out.println(exception.getMessage());
        ErrorDto dto = new ErrorDto();
        dto.setMessage(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(dto);
    }
}
