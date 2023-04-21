package com.homunculus.preproject.comment.article.mapper;

import lombok.Getter;

public enum CommentArticleSimpleResponseMessages {
    COMMENT_ARTICLE_MESSAGE_POST("댓글을 등록했습니다."),
    COMMENT_ARTICLE_MESSAGE_PATCH("댓글을 수정했습니다."),
    COMMENT_ARTICLE_MESSAGE_DELETE("댓글을 삭제했습니다.");

    @Getter
    private final String message;

    CommentArticleSimpleResponseMessages(String message) {
        this.message = message;
    }
}
