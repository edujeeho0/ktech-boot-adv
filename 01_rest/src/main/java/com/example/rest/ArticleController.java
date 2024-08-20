package com.example.rest;

import com.example.rest.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
POST /articles -> CREATE
GET /articles -> READ
GET /articles/{id} -> READ ONE
PUT /articles/{id} -> UPDATE
DELETE /articles/{id} -> DELETE
 */
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    // CREATE
    @PostMapping
    public ArticleDto create(
            @RequestBody
            ArticleDto dto
    ) {
        return service.create(dto);
    }

    // READ ALL
    @GetMapping
    public List<ArticleDto> readAll() {
        return service.readAll();
    }
}
