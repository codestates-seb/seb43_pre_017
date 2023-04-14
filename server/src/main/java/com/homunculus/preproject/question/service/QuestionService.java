package com.homunculus.preproject.question.service;

import com.homunculus.preproject.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    public Question createQuestion(Question question) {

        return null;
    }

    public Question updateQuestion(Question question) {

        return null;
    }

    public Question findQuestion(Long questionId) {

        return null;
    }

    public Page<Question> findQuestions(int i, int size) {

        return null;
    }

    public void deleteQuestion(Long questionId) {

        return;
    }
}
