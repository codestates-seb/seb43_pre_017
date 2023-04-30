package com.homunculus.preproject.evaluation.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.answer.service.AnswerService;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EvaluationAnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerService answerService;

    public EvaluationAnswer createEvaluationAnswer(EvaluationAnswer evaluationAnswer) {

        evaluationAnswer.setAnswer(
            answerService.findVerifiedAnswer(
                    evaluationAnswer.getAnswer(), false)
        );

        evaluationAnswer.addEvaluationScore();
        answerRepository.save(evaluationAnswer.getAnswer());

        return evaluationAnswer;
    }

    public EvaluationAnswer deleteEvaluationAnswer(Long answerId, Long evaluationId) {
        return null;
    }

    private EvaluationAnswer findVerifiedEvaluationAnswer(EvaluationAnswer evaluationAnswer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(evaluationAnswer.getAnswer().getAnswerId());
        Answer findAnswer = optionalAnswer.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );

        checkAllowedMember();

        EvaluationAnswer result = evaluationAnswer;
        result.setAnswer(findAnswer);

        return result;
    }

    public static void checkAllowedMember () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        if (connectedUser == null)
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);

        // todo : role 추가 시 권한에 따른 등록 방식 추가해야함
    }
}
