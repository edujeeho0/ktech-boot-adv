package com.example.file;

import com.example.file.dto.UserDto;
import com.example.file.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    // 이 userRepository는 Mock 객체이다.
    @Mock
    private UserRepository userRepository;

    // 이 userService가 필요로 하는 UserRepository는
    // Mock을 사용하겠다.
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("UserDto로 사용자 생성")
    public void testCreateUser() {
        // given
        // username을 정의
        String username = "edujeeho";
        // userRepository가 반환할 User 객체
        User userOut = new User();
        userOut.setUsername(username);
        // userRepository의 save 메서드가 호출되면
        // userOut이 반환되는 모습을 흉내내게 한다.
        when(userRepository.save(any()))
                .thenReturn(userOut);

        // when
        // UserDto를 service에 전달한다.
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        UserDto result = userService.create(userDto);

        // then
        // 반환된 UserDto의 username이 원래의 username과 일치한다.
        assertEquals(username, result.getUsername());
    }

    @Test
    @DisplayName("username으로 UserDto 반환")
    public void testReadUserByUsername() {
        // given
        // userRepository가 반환할 User 준비
        String username = "edujeeho";
        User userOut = new User();
        userOut.setUsername(username);
        // userRepository.findByUsername의 기능을 Mock한다.
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(userOut));

        // when
        // username을 readByUsername에 전달
        UserDto result = userService.readByUsername(username);

        // then
        // result의 username이 username인지 확인
        assertEquals(username, result.getUsername());
    }
}
