package com.homunculus.preproject.article.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        // 업데이트를 하기 위해서 DB에 해당 정보가 있는지 확인(수정이니까 해당정보가 있는지 알아야함)
        // 만일 없다면 예외처리 있으면 수정 내용반영
        Article findArticle = findVerifiedArticle(article.getArticleId());

        // title , content 내용 업데이트
        Optional.ofNullable(article.getTitle())
                .ifPresent(title -> findArticle.setTitle(title));
        Optional.ofNullable(article.getContent())
                .ifPresent(content -> findArticle.setContent(content));

        // Article 정보 업데이트
        return articleRepository.save(findArticle);
    }

    public Article findArticle(Long articleId) {
        return findVerifiedArticle(articleId);
    }

    public Page<Article> findArticles(int page, int size) {

        return null;
    }

    public void deleteArticle(Long articleId) {

        return;
    }
    public Article findVerifiedArticle(long articleId) {
        Optional<Article> optionalArticle =
                articleRepository.findById(articleId);
        Article findArticle =
                optionalArticle.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND));
        return findArticle;
    }
}
