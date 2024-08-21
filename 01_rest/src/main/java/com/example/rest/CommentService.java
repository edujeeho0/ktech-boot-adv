package com.example.rest;

import com.example.rest.dto.CommentDto;
import com.example.rest.entity.Article;
import com.example.rest.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // CREATE
    public CommentDto create(Long articleId, CommentDto dto) {
        Optional<Article> optionalArticle =
                articleRepository.findById(articleId);
        // Article이 실제로 존재하는지 확인한다.
        if (optionalArticle.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Comment newComment = new Comment();
        newComment.setContent(dto.getContent());
        newComment.setWriter(dto.getWriter());
        newComment.setArticle(optionalArticle.get());
        return CommentDto
                .fromEntity(commentRepository.save(newComment));
    }

}
