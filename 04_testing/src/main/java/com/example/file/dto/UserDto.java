package com.example.file.dto;

import com.example.file.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {
    private Long id;
    @Setter
    private String username;
    @Setter
    private String password;
    @Setter
    private String profileImgUrl;

    public static UserDto fromEntity(User entity) {
        UserDto dto = new UserDto();
        dto.id = entity.getId();
        dto.username = entity.getUsername();
        dto.password = "(HIDDEN)";
        dto.profileImgUrl = entity.getProfileImgUrl();
        return dto;
    }
}
