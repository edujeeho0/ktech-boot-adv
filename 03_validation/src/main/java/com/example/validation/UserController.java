package com.example.validation;

import com.example.validation.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
    @PostMapping
    public Map<String, String> create(
            @RequestBody
            UserDto dto
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "200");
        response.put("message", "OK");
        return response;
    }
}
