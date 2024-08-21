package com.example.rest;

import com.example.rest.dto.ArticleDto;
import com.example.rest.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // READ ONE
    public ArticleDto readOne(Long id) {
        Optional<Article> optionalArticle = repository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            return ArticleDto.fromEntity(article);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // UPDATE
    public ArticleDto update(Long id, ArticleDto dto) {
        Optional<Article> optionalArticle = repository.findById(id);
        if (optionalArticle.isPresent()) {
            Article target = optionalArticle.get();
            target.setTitle(dto.getTitle());
            target.setContent(dto.getContent());
            target.setWriter(dto.getWriter());
            return ArticleDto.fromEntity(repository.save(target));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // DELETE
    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // READ PAGED
    public Page<Article> readPagedArticle(
            Integer pageNumber,
            Integer pageSize
    ) {
        Pageable pageable =
                PageRequest.of(pageNumber, pageSize);
        Page<Article> articles = repository.findAll(pageable);
        return articles;
    }
}
