package com.example.validation.dto;

import com.example.validation.constraints.annotations.EmailBlackList;
import com.example.validation.constraints.annotations.EmailWhiteList;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "username is blank!!!!!")
    @Size(min = 8, message = "username length min: 8")
    private String username;

//    @Email
//    @EmailWhiteList
    @EmailBlackList(
            blackList = {"malware.good"}
    )
    private String email;

    @Min(value = 14, message = "must be over 14")
    private Integer age;
    // "YYYY-MM-DD"
    @Future
    private LocalDate validUntil;

    // TODO
    // 이 day에 들어가는 데이터가
    // "mon", "tue", "wed", "thu", "fri", "sat", "sun"
    // 중 하나가 될 수 있도록 annotation을 만들어 봅시다
    private String day;
}
