package com.example.file;

import com.example.file.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {
    // HTTP 요청을 보내고, 응답을 받는것을
    // Mocking하는 객체
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User 생성 통합 테스트")
    public void whenPostUserDto_thenReturnJson() throws Exception {
        // 요청을 보내기 위한 데이터를 준비한다.
        String username = "edujeeho";
        String password = "password";
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);

        // HTTP 요청을 보낸다.
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userDto)))
                .andExpectAll(
                        // 성공 status code 반환
                        status().is2xxSuccessful(),
                        // 응답은 JSON이 왔을 것이다.
                        content().contentType(MediaType.APPLICATION_JSON),
                        // 돌아온 응답의 username이 보낸 username과 동일하다.
                        /*
                        {
                            "username": "",
                            "password": "",
                            "profileImg": ""
                        }
                         */
                        jsonPath("$.username", is(username)),
                        jsonPath("$.id", notNullValue())
                )
        ;

        // DB에 실제로 존재하는지?
        assertTrue(userRepository.existsByUsername(username));
    }

    private byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
