package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class BlockingClient {
    public void testGet() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();

        ArticleDto response = restClient
                .get()
                .uri("/articles/1")
                .retrieve()
                .body(ArticleDto.class);
        log.info(response.toString());
    }
}
