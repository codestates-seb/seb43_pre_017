package com.homunculus.preproject.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.article.utils.ArticleServiceUtils;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.utils.AuthenticationUtils;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthenticationUtils authenticationUtils;
    private final CustomBeanUtils customBeanUtils;

    public Article createArticle(Article article) {

        // 로그인 한 유저인지만 체크
        boolean isPostMethod = true;
        article.setMember(
                authenticationUtils.findMemberWithCheckAllowed(
                        article.getMember(), isPostMethod,
                        ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED)
        );

        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        Article findArticle = findVerifiedArticle(article.getArticleId());
        findArticle.setMember(
            authenticationUtils.findMemberWithCheckAllowed(
                    findArticle.getMember(), false,
                    ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED)
        );

        Optional.ofNullable(article.getTitle()).ifPresent(findArticle::setTitle);
        Optional.ofNullable(article.getContent()).ifPresent(findArticle::setContent);

        return articleRepository.save(findArticle);
    }

    @Transactional(readOnly = true)
    public Article findArticle(Long articleId) {
        Article findArticle = findVerifiedArticle(articleId);

        // 무한 증가를 위해서 간단하게 구현
        findArticle.setViewCount(findArticle.getViewCount()+1);
        articleRepository.save(findArticle);

        return findArticle;
    }

    @Transactional(readOnly = true)
    public Page<Article> findArticles(int page, int size, String typeForSorting) {

        String orderBy = ArticleServiceUtils.checkValidTypeOfOrderByField(typeForSorting);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());

        return articleRepository.findAll(pageable);
    }

    public Article deleteArticle(Long articleId) {
        Article findArticle = findVerifiedArticle(articleId);

        authenticationUtils.findMemberWithCheckAllowed(
                findArticle.getMember(), false,
                ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED);

        // 특정 질문 정보 삭제
        articleRepository.deleteById(findArticle.getArticleId());
        return findArticle;
    }


    // 이미 등록된 질문인지 검증
    @Transactional(readOnly = true)
    public Article findVerifiedArticle(long articleId) {
        Optional<Article> optionalArticle =
                articleRepository.findById(articleId);
        Article findArticle =
                optionalArticle.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND));
        return findArticle;
    }
}


