package com.example.rest;

import com.example.rest.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
POST /articles/{articleId}/comments
GET /articles/{articleId}/comments
PUT /articles/{articleId}/comments/{commentId}
DELETE /articles/{articleId}/comments/{commentId}
 */
@RestController
@RequestMapping("/articles/{articleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    // CREATE
    @PostMapping
    public CommentDto create(
            @PathVariable("articleId")
            Long articleId,
            @RequestBody
            CommentDto dto
    ) {
        return service.create(articleId, dto);
    }

    // READ ALL
    @GetMapping
    public List<CommentDto> readAll(
            @PathVariable("articleId")
            Long articleId
    ) {
        return service.readAll(articleId);
    }

    @PutMapping("{commentId}")
    public CommentDto update(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("commentId")
            Long commentId,
            @RequestBody
            CommentDto dto
    ) {
        return service.update(articleId, commentId, dto);
    }

    @DeleteMapping("{commentId}")
    public void delete(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("commentId")
            Long commentId
    ) {
        service.delete(articleId, commentId);
    }
}
