package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    public Answer createAnswer(Answer answer) {
        return null;
    }

    public Answer updateAnswer(Answer answer) {
        return null;
    }

    public Page<Answer> findAnswers(Long articleId, Integer page, Integer size) {
        return null;
    }

    public Answer deleteAnswer(Long articleId, Long answerId) { return null;  }
}
