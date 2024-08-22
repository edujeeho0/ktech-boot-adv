package com.example.file;

import com.example.file.dto.ErrorDto;
import com.example.file.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public UserDto create(
            @RequestBody
            UserDto dto
    ) {
        return service.create(dto);
    }

    @GetMapping("/{username}")
    public UserDto read(
            @PathVariable("username")
            String username
    ) {
        return service.readByUsername(username);
    }

    @PutMapping("/{userId}/image")
    public UserDto updateImage(
            @PathVariable("userId")
            Long userId,
            @RequestParam("image")
            MultipartFile image
    ) {
        return service.updateProfileImg(userId, image);
    }

    // 사용자 오류가 발생할 때 왜 오류가 났는지를 서버에 기록하고 싶다.
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
