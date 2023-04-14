package com.homunculus.preproject.article.service;

import com.homunculus.preproject.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    public Article createArticle(Article article) {

        return null;
    }

    public Article updateArticle(Article article) {

        return null;
    }

    public Article findArticle(Long articleId) {

        return null;
    }

    public Page<Article> findArticles(int page, int size) {

        return null;
    }

    public void deleteArticle(Long articleId) {

        return;
    }
}
