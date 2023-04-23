package com.homunculus.preproject.evaluation.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationAnswerService {

    private final AnswerRepository answerRepository;

    public EvaluationAnswer createEvaluationAnswer(EvaluationAnswer evaluationAnswer) {

        Answer findAnswer = findVerifiedEvaluationAnswer(evaluationAnswer.getAnswer().getAnswerId());
        findAnswer.addEvaluationScore(evaluationAnswer.getEvaluationAnswerStatus());
        answerRepository.save(findAnswer);

        return evaluationAnswer;
    }

    public EvaluationAnswer deleteEvaluationAnswer(Long answerId, Long evaluationId) {
        return null;
    }

    private static void checkAllowedMember (Answer answer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        if (connectedUser == null)
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);
        if ( !answer.getMember().getEmail().equals(connectedUser.getUsername()) ) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_MEMBER_NOT_ALLOWED);
        }
    }

    private Answer findVerifiedEvaluationAnswer(Long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );

        checkAllowedMember(findAnswer);

        return findAnswer;
    }

}
