package com.example.file;

import com.example.file.dto.UserDto;
import com.example.file.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Optional<User> optionalUser =
                repository.findById(id);
        if (optionalUser.isEmpty()) {
            System.out.println("username not exists");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        String profileDir = "media/" + id + "/";  // media/{userId}/
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        String originalFilename = image.getOriginalFilename();
        String[] filenameSplit = originalFilename.split("\\.");
        String extension = filenameSplit[filenameSplit.length - 1];

        String uploadPath = profileDir + "profile." + extension;
        try {
            image.transferTo(Path.of(uploadPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String reqPath = "/static/" + id + "/profile." + extension;
        User target = optionalUser.get();
        target.setProfileImgUrl(reqPath);

        return UserDto.fromEntity(repository.save(target));
    }
}
