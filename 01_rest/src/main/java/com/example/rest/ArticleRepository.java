package com.example.rest;

import com.example.rest.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // LIMIT: 결과의 갯수를 한정짓는 SQL 절
    // OFFSET: 앞쪽의 데이터를 건너뛰는 SQL 절

    // Top20 -> LIMIT 20
    List<Article> findTop20ByOrderById();
    List<Article> findTop20ByIdGreaterThanOrderById(Long id);
}
