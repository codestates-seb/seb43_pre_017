package com.homunculus.preproject.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.service.MemberService;
import com.homunculus.preproject.response.ErrorResponse;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    private enum TypeOfGetAll {
        ARTICLE_GET_TYPE_EVALUATION("평가순", "evaluationScore"),
        ARTICLE_GET_TYPE_CREATED_AT("최신순", "createdAt"),
        ARTICLE_GET_TYPE_VIEW_COUNT("조회순", "viewCount"),
        ARTICLE_GET_TYPE_ID("기본", "articleId"),
        ;

        private @Getter String type;
        private @Getter String orderBy;

        TypeOfGetAll(String type, String orderBy) {
            this.type = type;
            this.orderBy = orderBy;
        }
    }

    private String checkValidTypeOfOrderByField(String typeForSorting) {
        if( typeForSorting == null )
            typeForSorting = "기본";

        TypeOfGetAll[] types = TypeOfGetAll.values();
        int index = -1;
        for (int i = 0; i < types.length; i++) {
            if (types[i].getType().equals(typeForSorting)) {
                index = i;
                break;
            }
        }

        if( index == -1 )
            throw new BusinessLogicException(ExceptionCode.REQUESTED_RANGE_NOT_SATISFIABLE);

        String orderBy = types[index].getOrderBy();
        return orderBy;
    }

    public Article createArticle(Article article) {

//         로그인 한 유저인지만 체크
        boolean isPostArticle = true;
        checkAllowedMember(article, isPostArticle);

        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        Article findArticle = findVerifiedArticle(article.getArticleId());
        checkAllowedMember(findArticle);

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

        String orderBy = checkValidTypeOfOrderByField(typeForSorting);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());
        Page<Article> articlePage = articleRepository.findAll(pageable);

        return articlePage;
    }

    public Article deleteArticle(Long articleId) {
        Article findArticle = findVerifiedArticle(articleId);

        checkAllowedMember(findArticle);

        // 특정 질문 정보 삭제
        articleRepository.delete(findArticle);
        return findArticle;
    }

    public void checkAllowedMember(Article article) {
        // 기존에 사용하고 있던 방식은 기존방식: 전부 Post 가 아님으로 설정(false)
        checkAllowedMember(article, false);
    }

    public void checkAllowedMember(Article article, boolean isPostArticle) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);
        }

        UserDetails userDetails = (UserDetails) principal;

        // todo : role 추가 시 권한에 따른 등록 방식 추가해야함

        // post 가 아니라면 작성자가 맞는지 체크
        if (!isPostArticle) {
            if (!article.getMember().getEmail().equals(userDetails.getUsername())) {
                throw new BusinessLogicException(ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED);
            }
        }

        String email = userDetails.getUsername();
        Member member = memberService.findVerifiedMemberByEmail(email);
        article.setMember(member);
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
