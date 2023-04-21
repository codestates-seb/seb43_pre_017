package com.homunculus.preproject.evaluation.article.mapper;

import lombok.Getter;

public enum EvaluationArticleSimpleResponseMessages {
    EVALUATION_ARTICLE_MESSAGE_POST("평가를 등록했습니다."),
    EVALUATION_ARTICLE_MESSAGE_DELETE("평가를 삭제했습니다.");

    @Getter
    private final String message;

    EvaluationArticleSimpleResponseMessages(String message) {
        this.message = message;
    }
}
