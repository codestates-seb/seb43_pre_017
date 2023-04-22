package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        return CustomBeanUtils.copyNonNullProperties(answer, findAnswer);
    }

    public Page<Answer> findAnswers(Long articleId, Integer page, Integer size) {
        return answerRepository.findAnswersByArticleArticleId(
                articleId,
                PageRequest.of(page, size, Sort.by("answerId"))
        );
    }

    public Answer deleteAnswer(Long articleId, Long answerId) {
        Answer deletedAnswer = findVerifiedAnswer(answerId);
        if( deletedAnswer.getArticle().getArticleId() != articleId )
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_ACCEPTABLE);

        CustomBeanUtils.checkAllowedMember(deletedAnswer, ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED);

        return null;
    }

    private Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswerByAnswerId(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );

        return findAnswer;
    }
}
