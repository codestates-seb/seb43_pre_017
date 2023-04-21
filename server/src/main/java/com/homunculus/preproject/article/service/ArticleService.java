package com.homunculus.preproject.article.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    public Article createArticle(Article article) {

        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        Article findArticle = findVerifiedArticle(article.getArticleId());

        Optional.ofNullable(article.getTitle())
                .ifPresent(title -> findArticle.setTitle(title));
        Optional.ofNullable(article.getContent())
                .ifPresent(content -> findArticle.setContent(content));


        return articleRepository.save(findArticle);
    }

    public Article findArticle(Long articleId) {
        return findVerifiedArticle(articleId);
    }

    public Page<Article> findArticles(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("articleId").descending());

        Page<Article> articlePage = articleRepository.findAll(pageable);

        return articlePage;
    }

    public Article deleteArticle(Long articleId) {
        Article findArticle = findVerifiedArticle(articleId);

        // 특정 질문 정보 삭제
        articleRepository.delete(findArticle);
        return findArticle;
    }

    // 이미 등록된 질문인지 검증
    public Article findVerifiedArticle(long articleId) {
        Optional<Article> optionalArticle =
                articleRepository.findById(articleId);
        Article findArticle =
                optionalArticle.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND));
        return findArticle;
    }
}
