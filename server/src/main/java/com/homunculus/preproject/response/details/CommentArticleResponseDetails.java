package com.homunculus.preproject.response.details;

import com.homunculus.preproject.comment.article.entity.CommentArticle;

import java.time.LocalDateTime;

public class CommentArticleResponseDetails {
    private Long id;
    private Long content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;    // todo : 필드명 주의

    private CommentArticle.CommentArticleStatus status;
    public String getStatus() { return status.getStatus(); }
}
