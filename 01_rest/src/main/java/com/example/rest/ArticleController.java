package com.example.rest;

import com.example.rest.dto.ArticleDto;
import com.example.rest.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
//    @GetMapping
//    public List<ArticleDto> readAll() {
//        return service.readAll();
//    }
    @GetMapping
    public Page<ArticleDto> readAll(
            @RequestParam(value = "page", defaultValue = "0")
            Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "20")
            Integer pageSize
    ) {
        return service.readAll(pageNumber, pageSize);
    }

    // READ ONE
    @GetMapping("{id}")
    public ArticleDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return service.readOne(id);
    }

    // UPDATE
    @PutMapping("{id}")
    public ArticleDto update(
            @PathVariable("id")
            Long id,
            @RequestBody
            ArticleDto dto
    ) {
        return service.update(id, dto);
    }

    // DELETE
    @DeleteMapping("{id}")
    // @ResponseStatus 를 사용하면
    // 기본 응답 코드를 설정할 수 있다.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id")
            Long id
    ) {
        service.delete(id);
    }

    @GetMapping("paged")
    // /articles/paged?page=0&size=10
    public Page<Article> readAllPaged(
            @RequestParam("page")
            Integer pageNumber,
            @RequestParam("size")
            Integer pageSize
    ) {
        return service.readPagedArticle(pageNumber, pageSize);
    }
}
