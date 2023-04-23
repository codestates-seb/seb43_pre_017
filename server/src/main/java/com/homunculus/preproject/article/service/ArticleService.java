package com.homunculus.preproject.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    public static void checkAllowedMember (Article article) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        if (connectedUser == null)
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);

        // todo : role 추가 시 권한에 따른 등록 방식 추가해야함

        if ( !article.getMember().getEmail().equals(connectedUser.getUsername()) ) {
            throw new BusinessLogicException(ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED);
        }
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
