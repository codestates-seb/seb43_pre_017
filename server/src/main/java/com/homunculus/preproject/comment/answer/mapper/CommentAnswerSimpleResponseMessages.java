package com.homunculus.preproject.comment.answer.mapper;

import lombok.Getter;

public enum CommentAnswerSimpleResponseMessages {
    COMMENT_ANSWER_MESSAGE_POST("댓글을 등록했습니다."),
    COMMENT_ANSWER_MESSAGE_PATCH("댓글을 수정했습니다."),
    COMMENT_ANSWER_MESSAGE_DELETE("댓글을 삭제했습니다."),
    COMMENT_ANSWER_MESSAGE_GET("댓글을 조회했습니다.");

    @Getter
    private final String message;

    CommentAnswerSimpleResponseMessages(String message) {
        this.message = message;
    }
}
