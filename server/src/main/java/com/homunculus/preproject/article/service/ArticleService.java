package com.homunculus.preproject.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.article.utils.ArticleServiceUtils;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.repository.MemberRepository;
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


@Transactional
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final AuthenticationUtils authenticationUtils;

    public Article createArticle(Article article) {

        boolean isPostMethod = true;
        article.setMember(
                authenticationUtils.findMemberWithCheckAllowed(
                        article.getMember(), isPostMethod,
                        ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED)
        );

        Member member = article.getMember();

        if (member.getEmail() != null) { // member가 이미 DB에 저장되어 있는지 확인
            Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
            if (optionalMember.isPresent()) {
                // 이미 저장된 member를 사용
                article.setMember(optionalMember.get());
            } else {
                // member가 DB에 없으면 새로 저장
                article.setMember(memberRepository.save(member));
            }
        } else {
            // member email이 없으면 새로 저장
            article.setMember(memberRepository.save(member));
        }

        return articleRepository.save(article);

    }

    public Article updateArticle(Article article) {
        Article findArticle = findVerifiedArticle(article.getArticleId());
        findArticle.setMember(
                authenticationUtils.findMemberWithCheckAllowed(
                        findArticle.getMember(), false,
                        ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED)
        );

        // Member 객체를 새로 저장
        Member member = memberRepository.save(findArticle.getMember());
        findArticle.setMember(member);

        CustomBeanUtils.copyNonNullProperties(article, findArticle);

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
    public Article findVerifiedArticle(long articleId) {
        Optional<Article> optionalArticle =
                articleRepository.findById(articleId);
        Article findArticle =
                optionalArticle.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND));
        return findArticle;
    }
}


