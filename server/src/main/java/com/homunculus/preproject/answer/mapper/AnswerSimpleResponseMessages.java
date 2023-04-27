package com.homunculus.preproject.answer.mapper;

import lombok.Getter;

public enum AnswerSimpleResponseMessages {
    ANSWER_MESSAGE_POST("답변을 등록했습니다."),
    ANSWER_MESSAGE_ACCEPTED("답변을 채택했습니다."),
    ANSWER_MESSAGE_PATCH("답변을 수정했습니다."),
    ANSWER_MESSAGE_DELETE("답변을 삭제했습니다.");

    @Getter
    private final String message;

    AnswerSimpleResponseMessages(String message) {
        this.message = message;
    }
}