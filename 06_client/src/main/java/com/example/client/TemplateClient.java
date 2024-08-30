package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TemplateClient {
    private final RestTemplate restTemplate;
    public TemplateClient(RestTemplateBuilder templateBuilder) {
        restTemplate = templateBuilder
                .rootUri("http://localhost:8081")
                .build();
    }

    public void testGet() {
        RestTemplate restTemplate = new RestTemplate();
        ArticleDto response = restTemplate.getForObject(
                "http://localhost:8081/articles/1",
                ArticleDto.class
        );
        log.info(response.toString());

        ResponseEntity<ArticleDto> responseEntity = this.restTemplate.getForEntity(
                "/articles/1",
                ArticleDto.class
        );
        log.info(responseEntity.getStatusCode().toString());
        log.info(responseEntity.getHeaders().toString());
        log.info(responseEntity.getBody().toString());
    }

    public void testPost() {
        ArticleDto newArticle = new ArticleDto();
        newArticle.setTitle("article from restTemplate");
        newArticle.setContent("article content from restTemplate");
        newArticle.setWriter("writer");

        ArticleDto response = restTemplate.postForObject(
                "/articles",
                newArticle,
                ArticleDto.class
        );
        log.info(response.toString());
    }

    public void testPut() {
        ArticleDto updateArticle = new ArticleDto();
        updateArticle.setTitle("updated");
        updateArticle.setContent("updated content");
        ResponseEntity<ArticleDto> response = restTemplate.exchange(
                "/articles/1",
                HttpMethod.PUT,
                new HttpEntity<>(updateArticle),
                ArticleDto.class
        );
        log.info(response.toString());
    }

    public void testDelete() {
        ResponseEntity<Void> response = restTemplate.exchange(
                "/articles/10",
                HttpMethod.DELETE,
                null,
                Void.class
        );
        log.info("status code: {}", response.getStatusCode());
    }
}
