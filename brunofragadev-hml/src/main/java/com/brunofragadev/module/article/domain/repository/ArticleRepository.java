package com.brunofragadev.module.article.domain.repository;

import com.brunofragadev.module.article.domain.entity.ArticleStatus;
import com.brunofragadev.module.article.domain.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    boolean existsBySlug(String slug);
    Optional<Article> findBySlug(String slug);
    List<Article> findAllByStatus(ArticleStatus status);
    List<Article> findAll();
    Optional<Article> findById(Long id);
}