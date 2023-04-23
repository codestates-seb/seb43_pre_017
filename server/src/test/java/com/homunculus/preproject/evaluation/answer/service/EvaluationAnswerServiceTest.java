package com.homunculus.preproject.evaluation.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
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
class EvaluationAnswerServiceTest {
    @Mock
    AnswerRepository answerRepository;

    @InjectMocks
    EvaluationAnswerService evaluationAnswerService;

    @Test
    @DisplayName("답변글 추천 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createEvaluationAnswer() {
        // given
        final Long answerId = 1L;
        final EvaluationAnswer.EvaluationAnswerStatus status =
              EvaluationAnswer.EvaluationAnswerStatus.EVALUATION_ANSWER_LIKE;

        final Integer evaluationScore = 300;
        final String email = "email@gmail.com";

        EvaluationAnswer evaluationAnswer = createDummyEvaluationAnswer(answerId, email, status, evaluationScore);
        given(answerRepository.findById(anyLong())).willReturn(Optional.of(evaluationAnswer.getAnswer()));
        given(answerRepository.save(evaluationAnswer.getAnswer())).willReturn(new Answer());

        // when, then
        assertDoesNotThrow(() ->
                evaluationAnswerService.createEvaluationAnswer(evaluationAnswer));
    }

    @Test
    @DisplayName("답변글 추천 - 인증 실패")
    @WithMockUser(username = "notEmail@gmail.com", roles = "USER")
    void createEvaluationAnswer_authentication_Fail() {
        // given
        final Long answerId = 1L;
        final EvaluationAnswer.EvaluationAnswerStatus status =
              EvaluationAnswer.EvaluationAnswerStatus.EVALUATION_ANSWER_LIKE;

        final Integer evaluationScore = 300;
        final String email = "email@gmail.com";

        EvaluationAnswer evaluationAnswer = createDummyEvaluationAnswer(answerId, email, status, evaluationScore);
        given(answerRepository.findById(anyLong())).willReturn(Optional.of(evaluationAnswer.getAnswer()));
        given(answerRepository.save(evaluationAnswer.getAnswer())).willReturn(new Answer());

        // when, then
        assertThrows(BusinessLogicException.class, () ->
                evaluationAnswerService.createEvaluationAnswer(evaluationAnswer));
    }

    @Test
    @DisplayName("답변글 추천 - 인증정보없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createEvaluationAnswer_nullAuthentication_Fail() {
        // given
        final Long answerId = 1L;
        final EvaluationAnswer.EvaluationAnswerStatus status =
              EvaluationAnswer.EvaluationAnswerStatus.EVALUATION_ANSWER_LIKE;

        final Integer evaluationScore = 300;

        EvaluationAnswer evaluationAnswer = createDummyEvaluationAnswer(answerId, null, status, evaluationScore);
        given(answerRepository.findById(anyLong())).willReturn(Optional.of(evaluationAnswer.getAnswer()));
        given(answerRepository.save(evaluationAnswer.getAnswer())).willReturn(new Answer());

        // when, then
        assertThrows(NullPointerException.class, () ->
                evaluationAnswerService.createEvaluationAnswer(evaluationAnswer));
    }

    @Test
    @DisplayName("답변글 추천 - 답변글 없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createEvaluationAnswer_NotExistAnswer_Fail() {
        // given
        final EvaluationAnswer.EvaluationAnswerStatus status =
              EvaluationAnswer.EvaluationAnswerStatus.EVALUATION_ANSWER_LIKE;

        final Integer evaluationScore = 300;
        final String email = "email@gmail.com";

        EvaluationAnswer evaluationAnswer = createDummyEvaluationAnswer(null, email, status, evaluationScore);
        given(answerRepository.findById(anyLong())).willReturn(Optional.of(evaluationAnswer.getAnswer()));
        given(answerRepository.save(evaluationAnswer.getAnswer())).willReturn(new Answer());

        // when, then
        assertThrows(BusinessLogicException.class, () ->
                evaluationAnswerService.createEvaluationAnswer(evaluationAnswer));
    }

    private static EvaluationAnswer createDummyEvaluationAnswer(Long answerId, String email,
                                                                EvaluationAnswer.EvaluationAnswerStatus status,
                                                                Integer evaluationScore) {
        EvaluationAnswer evaluationAnswer = new EvaluationAnswer();

        Answer answer = new Answer();
        {
            answer.setAnswerId(answerId);
            answer.setEvaluationScore(evaluationScore);

            Member member = new Member();
            member.setEmail(email);
            answer.setMember(member);

            evaluationAnswer.setAnswer(answer);
        }
        evaluationAnswer.setEvaluationAnswerStatus(status);

        return evaluationAnswer;
    }
}