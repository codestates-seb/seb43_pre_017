package com.homunculus.preproject.article.mapper;

import lombok.Getter;

public enum ArticleSimpleResponseMessages {
    ARTICLE_MESSAGE_POST("질문을 등록했습니다."),
    ARTICLE_MESSAGE_PATCH("질문을 수정했습니다."),
    ARTICLE_MESSAGE_DELETE("질문을 삭제했습니다.");

    @Getter
    private final String message;

    ArticleSimpleResponseMessages(String message) {
        this.message = message;
    }
}