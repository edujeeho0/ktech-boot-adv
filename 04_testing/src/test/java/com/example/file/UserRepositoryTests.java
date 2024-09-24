package com.example.file;

import com.example.file.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// JPA를 테스트하고 싶으니 Repository들을 Bean으로 만들어 주세요.
@DataJpaTest
public class UserRepositoryTests {
    // 아래 속성은 Bean 객체를 쓰고 싶습니다.
    @Autowired
    private UserRepository userRepository;

    // 사용자를 추가하는 테스트
    @Test
    @DisplayName("새로운 User 추가")
    public void testCreateUser() {
        // given - 내가 만들고자 하는 User 객체를 만들어둔다.
        String username = "edujeeho";
        User user = new User();
        user.setUsername(username);

        // when - userRepository를 이용해 user를 저장한다.
        User result = userRepository.save(user);

        // then - 결과를 확인한다.
        assertEquals(username, result.getUsername());
        assertNotNull(result.getId());
    }

    // 중복된 username을 가진 사용자 추가 실패
    @Test
    @DisplayName("새로운 User 추가 실패 (username 중복)")
    public void testCreateUserFail() {
        // given - 어떤 username을 가진 사용자를 준비한다.
        String username = "alex";
        User alex = new User();
        alex.setUsername(username);
        userRepository.save(alex);

        // when - 같은 username을 가진 사용자를 준비한다.
        User newUser = new User();
        newUser.setUsername(username);

        // then - 저장할때 실패(예외가 발생)한다.
        assertThrows(Exception.class, () -> userRepository.save(newUser));
    }

    // username을 바탕으로 사용자 검색 성공
    @Test
    @DisplayName("username으로 존재하는 사용자 조회")
    public void testReadUser() {
        // given - 특정 username을 가진 사용자가 DB에 있다.
        String username = "edujeeho0";
        User givenUser = new User();
        givenUser.setUsername(username);
        userRepository.save(givenUser);

        // when - username으로 사용자를 검색했을 때
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // then
        // 데이터가 실제로 존재하고 (isPresent())
        assertTrue(optionalUser.isPresent());
        // 그 데이터의 username이 내가 전달한 username과 일치해야 한다.
        assertEquals(username, optionalUser.get().getUsername());
    }
}
