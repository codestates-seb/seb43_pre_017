package com.homunculus.preproject.evaluation.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
class EvaluationArticleServiceTest {
    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    EvaluationArticleService evaluationArticleService;

    @Test
    @DisplayName("답변글 추천 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createEvaluationArticle() {
        // given
        final Long articleId = 1L;
        final EvaluationArticle.EvaluationArticleStatus status =
                EvaluationArticle.EvaluationArticleStatus.EVALUATION_ARTICLE_LIKE;

        final Integer evaluationScore = 300;
        final String email = "email@gmail.com";

        EvaluationArticle evaluationArticle = createDummyEvaluationArticle(articleId, email, status, evaluationScore);
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(evaluationArticle.getArticle()));
        given(articleRepository.save(evaluationArticle.getArticle())).willReturn(new Article());

        // when, then
        assertDoesNotThrow(() ->
                evaluationArticleService.createEvaluationArticle(evaluationArticle));
    }

    @Test
    @DisplayName("답변글 추천 - 인증 실패")
    @WithMockUser(username = "notEmail@gmail.com", roles = "USER")
    void createEvaluationArticle_authentication_Fail() {
        // given
        final Long articleId = 1L;
        final EvaluationArticle.EvaluationArticleStatus status =
                EvaluationArticle.EvaluationArticleStatus.EVALUATION_ANSWER_LIKE;

        final Integer evaluationScore = 300;
        final String email = "email@gmail.com";

        EvaluationArticle evaluationArticle = createDummyEvaluationArticle(articleId, email, status, evaluationScore);
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(evaluationArticle.getArticle()));
        given(articleRepository.save(evaluationArticle.getArticle())).willReturn(new Article());

        // when, then
        assertThrows(BusinessLogicException.class, () ->
                evaluationArticleService.createEvaluationArticle(evaluationArticle));
    }

    @Test
    @DisplayName("답변글 추천 - 인증정보없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createEvaluationArticle_nullAuthentication_Fail() {
        // given
        final Long articleId = 1L;
        final EvaluationArticle.EvaluationArticleStatus status =
                EvaluationArticle.EvaluationArticleStatus.EVALUATION_ANSWER_LIKE;

        final Integer evaluationScore = 300;

        EvaluationArticle evaluationArticle = createDummyEvaluationArticle(articleId, null, status, evaluationScore);
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(evaluationArticle.getArticle()));
        given(articleRepository.save(evaluationArticle.getArticle())).willReturn(new Article());

        // when, then
        assertThrows(NullPointerException.class, () ->
                evaluationArticleService.createEvaluationArticle(evaluationArticle));
    }

    @Test
    @DisplayName("답변글 추천 - 답변글 없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createEvaluationArticle_NotExistArticle_Fail() {
        // given
        final Long articleId = 1L;
        final EvaluationArticle.EvaluationArticleStatus status =
                EvaluationArticle.EvaluationArticleStatus.EVALUATION_ANSWER_LIKE;

        final Integer evaluationScore = 300;
        final String email = "email@gmail.com";

        EvaluationArticle evaluationArticle = createDummyEvaluationArticle(null, email, status, evaluationScore);
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(evaluationArticle.getArticle()));
        given(articleRepository.save(evaluationArticle.getArticle())).willReturn(new Article());

        // when, then
        assertThrows(BusinessLogicException.class, () ->
                evaluationArticleService.createEvaluationArticle(evaluationArticle));
    }

    private static EvaluationArticle createDummyEvaluationArticle(Long articleId, String email,
                                                                EvaluationArticle.EvaluationArticleStatus status,
                                                                Integer evaluationScore) {
        EvaluationArticle evaluationArticle = new EvaluationArticle();

        Article article = new Article();
        {
            article.setArticleId(articleId);
            article.setEvaluationScore(evaluationScore);

            Member member = new Member();
            member.setEmail(email);
            article.setMember(member);

            evaluationArticle.setArticle(article);
        }
        evaluationArticle.setEvaluationArticleStatus(status);

        return evaluationArticle;
    }
}