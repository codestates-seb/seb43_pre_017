package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.utils.CustomBeanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.hamcrest.Matchers.anything;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(AnswerService.class)
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
        answer.setAnswerId(answerId);
        answer.setContent(content);

        Article article = new Article();
        article.setArticleId(articleId);
        answer.setArticle(article);
        given(answerRepository.save(any())).willReturn(answer);

        //when
        Answer createdAnswer = answerService.createAnswer(answer);

        //then
        assertEquals(answerId, createdAnswer.getAnswerId());
        assertEquals(content, createdAnswer.getContent());
        assertEquals(articleId, createdAnswer.getArticle().getArticleId());
    }

    @Test
    @DisplayName("답변글 수정 테스트")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void updateAnswerTest() {
        //given
        final Long articleId = 1L;
        final Long answerId = 1L;

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
        given(answerRepository.findById(anyLong())).willReturn(Optional.of(answer));

        given(answerService.findVerifiedAnswer(updateAnswer)).willReturn(answer);
        given(CustomBeanUtils.copyNonNullProperties(any(Answer.class), any(Answer.class))).willReturn((Answer) anything());

        //when
        Answer updatedAnswer = answerService.updateAnswer(updateAnswer);

        //then
        assertEquals(updatedAnswer.getAnswerId(), updateAnswer.getAnswerId());
        assertEquals(updatedAnswer.getContent(), updateAnswer.getContent());
        assertEquals(updatedAnswer.getArticle().getArticleId(), updateAnswer.getArticle().getArticleId());
    }

    @Test
    void findAnswers() {
    }

    @Test
    void deleteAnswer() {
    }
}