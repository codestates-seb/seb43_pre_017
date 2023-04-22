package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.utils.CustomBeanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.Matchers.anything;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;

    @Test
    @DisplayName("답변글 생성 테스트")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createAnswerTest() {
        //given
        final Long articleId = 1L;
        final Long answerId = 1L;
        final String content = "답변의 내용";
        Answer answer = new Answer();
        {
            answer.setAnswerId(answerId);
            answer.setContent(content);

            Article article = new Article();
            article.setArticleId(articleId);
            answer.setArticle(article);
        }
        given(answerRepository.save(answer)).willReturn(answer);

        //when, then
        assertDoesNotThrow(() -> answerService.createAnswer(answer));
    }

    @Test
    @DisplayName("답변글 수정 테스트 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void updateAnswerTest() {
        //given
        final Long articleId = 1L;
        final Long answerId = 1L;
        final String email = "email@gmail.com";

        Answer answer = new Answer();
        {
            final String content = "답변의 내용";

            answer.setAnswerId(answerId);
            answer.setContent(content);

            Member member = new Member();
            member.setEmail(email);
            answer.setMember(member);

            Article article = new Article();
            article.setArticleId(articleId);
            answer.setArticle(article);
        }

        final String updateContent = "변경된 답변의 내용";
        Answer updateAnswer = new Answer();
        {
            updateAnswer.setAnswerId(answerId);
            updateAnswer.setContent(updateContent);

            Member member = new Member();
            member.setEmail(email);
            updateAnswer.setMember(member);

            Article article = new Article();
            article.setArticleId(articleId);
            updateAnswer.setArticle(article);
        }

        given(answerRepository.save(answer)).willReturn(new Answer());
        given(answerRepository.findById(answer.getAnswerId())).willReturn(Optional.of(answer));

        //when
        Answer updatedAnswer = answerService.updateAnswer(updateAnswer);

        //then
        assertEquals(updatedAnswer.getAnswerId(), updateAnswer.getAnswerId());
        assertEquals(updatedAnswer.getContent(), updateAnswer.getContent());
        assertEquals(updatedAnswer.getArticle().getArticleId(), updateAnswer.getArticle().getArticleId());
    }

    @Test
    @DisplayName("답변글 수정 테스트 - 인증 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void updateAnswerTest_authentication_Fail() {
        //given
        final Long articleId = 1L;
        final Long answerId = 1L;
        final String email = "notEmail@gmail.com";

        Answer answer = new Answer();
        {
            final String content = "답변의 내용";

            answer.setAnswerId(answerId);
            answer.setContent(content);

            Member member = new Member();
            member.setEmail(email);
            answer.setMember(member);

            Article article = new Article();
            article.setArticleId(articleId);
            answer.setArticle(article);
        }

        final String updateContent = "변경된 답변의 내용";
        Answer updateAnswer = new Answer();
        {
            updateAnswer.setAnswerId(answerId);
            updateAnswer.setContent(updateContent);

            Member member = new Member();
            member.setEmail(email);
            updateAnswer.setMember(member);

            Article article = new Article();
            article.setArticleId(articleId);
            updateAnswer.setArticle(article);
        }

        given(answerRepository.save(answer)).willReturn(new Answer());
        given(answerRepository.findById(answer.getAnswerId())).willReturn(Optional.of(answer));

        //when, then
        assertThrows(BusinessLogicException.class ,() -> answerService.updateAnswer(updateAnswer));
    }

    @Test
    @DisplayName("답변글 수정 테스트 - 인증정보 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void updateAnswerTest_invalidMemberAuthentication_Fail() {
        //given
        final Long articleId = 1L;
        final Long answerId = 1L;
        final String email = "email@gmail.com";

        Answer answer = new Answer();
        {
            final String content = "답변의 내용";

            answer.setAnswerId(answerId);
            answer.setContent(content);

            Article article = new Article();
            article.setArticleId(articleId);
            answer.setArticle(article);
        }

        final String updateContent = "변경된 답변의 내용";
        Answer updateAnswer = new Answer();
        {
            updateAnswer.setAnswerId(answerId);
            updateAnswer.setContent(updateContent);

            Article article = new Article();
            article.setArticleId(articleId);
            updateAnswer.setArticle(article);
        }

        given(answerRepository.save(answer)).willReturn(new Answer());
        given(answerRepository.findById(answer.getAnswerId())).willReturn(Optional.of(answer));

        //when, then
        assertThrows(BusinessLogicException.class ,() -> answerService.updateAnswer(updateAnswer));
    }

    @Test
    void findAnswers() {
    }

    @Test
    void deleteAnswer() {
    }
}