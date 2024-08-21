package com.example.file;

import com.example.file.dto.UserDto;
import com.example.file.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    // CREATE
    public UserDto create(UserDto dto) {
        if (repository.existsByUsername(dto.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword());
        return UserDto.fromEntity(repository.save(newUser));
    }

    // READ (BY username)
    public UserDto readByUsername(String username) {
        Optional<User> optionalUser =
                repository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return UserDto.fromEntity(optionalUser.get());
    }

    // UPDATE
    public UserDto updateProfileImg(Long id, MultipartFile image) {
        // 1. 유저가 존재하는지 확인한다.

        // 2. 파일의 업로드 위치를 결정한다.
        // 추천: media/{userId}/profile.png|jpeg

        // 3. 업로드에 성공하면, 이미지 URL을 Entity에 저장한다.

        // 4. User Entity를 DTO로 변환해서 반환한다.

        // TODO
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
