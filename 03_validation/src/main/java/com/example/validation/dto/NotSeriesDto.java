package com.example.validation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotSeriesDto {
    // null이 아니어야 한다.
    @NotNull
    private String notNull;
    // size 또는 length가 0이 아니어야 한다.
    @NotEmpty
    private String notEmpty;
    // 문자열(String) 대상으로, 공백을 제외한 문자가 존재해야 한다.
    @NotBlank
    private String notBlank;
}
