package com.homunculus.preproject.evaluation.answer.mapper;

import lombok.Getter;

public  enum EvaluationAnswerSimpleResponseMessages {
    EVALUATION_ANSWER_MESSAGE_POST("평가를 등록했습니다."),
    EVALUATION_ANSWER_MESSAGE_DELETE("평가를 삭제했습니다.");

    @Getter
    private final String message;

    EvaluationAnswerSimpleResponseMessages(String message) {
        this.message = message;
    }
}
