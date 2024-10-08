package com.example.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

public class ReactiveClient {
    public void testGetWebClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader("foo", "bar")
                .defaultRequest(request -> request
                        .header("bar", "baz"))
                .defaultStatusHandler(
                        status -> status.isError(),
                        response -> {
                            throw new ResponseStatusException(response.statusCode());
                        }
                )
                .build();

        ArticleDto response = webClient
                // 요청의 Method를 결정
                .get()
                // 요청 보낼 URL 결정
                .uri("/articles/1")
                // 요청 전송후
                .retrieve()
                // 응답 처리 방식 결정
                .bodyToMono(ArticleDto.class)
                .block();
    }
}
