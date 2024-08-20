package com.example.rest;

import com.example.rest.dto.ArticleDto;
import com.example.rest.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    // CREATE
    public ArticleDto create(ArticleDto dto) {
        Article newArticle = new Article();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setWriter(dto.getWriter());

        newArticle = repository.save(newArticle);
        return ArticleDto.fromEntity(newArticle);
//        return ArticleDto.fromEntity(repository.save(newArticle));
    }

    // READ ALL
    public List<ArticleDto> readAll() {
        List<ArticleDto> articleList = new ArrayList<>();
        for (Article article : repository.findAll()) {
            articleList.add(ArticleDto
                    .fromEntity(article));
        }
        return articleList;
    }
}
