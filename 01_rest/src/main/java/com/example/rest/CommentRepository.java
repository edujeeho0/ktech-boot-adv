package com.example.rest;

import com.example.rest.entity.Article;
import com.example.rest.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle(Article article);
    Page<Comment> findAllByArticle(Article article, Pageable pageable);
}
