package com.homunculus.preproject.article.repository;

import com.homunculus.preproject.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
