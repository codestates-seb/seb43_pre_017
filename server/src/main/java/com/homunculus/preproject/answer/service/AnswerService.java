package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer);

        checkAllowedMember(findAnswer);

        return CustomBeanUtils.copyNonNullProperties(answer, findAnswer);
    }

    public Page<Answer> findAnswers(Long articleId, Integer page, Integer size) {
        return answerRepository.findAnswersByArticleArticleId(
                articleId,
                PageRequest.of(page, size, Sort.by("answerId"))
        );
    }

    public Answer deleteAnswer(Long articleId, Long answerId) {
        // 유효성 확인
        Answer deletedAnswer = findVerifiedAnswer(articleId, answerId);

        // 삭제 권한 확인
        checkAllowedMember(deletedAnswer);

        answerRepository.deleteById(answerId);

        return deletedAnswer;
    }

    public static void checkAllowedMember (Answer answer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        if (connectedUser == null)
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);
        if ( !answer.getMember().getEmail().equals(connectedUser.getUsername()) ) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED);
        }
    }

    private Answer findVerifiedAnswer(Long articleId, Long answerId) {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);

        Article article = new Article();
        article.setArticleId(articleId);
        answer.setArticle(article);

        return findVerifiedAnswer(answer);
    }

    public Answer findVerifiedAnswer(Answer answer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answer.getAnswerId());

        Answer findAnswer = optionalAnswer.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );

        if(!Objects.equals(findAnswer.getArticle().getArticleId(), answer.getArticle().getArticleId()))
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_ACCEPTABLE);

        return findAnswer;
    }
}
