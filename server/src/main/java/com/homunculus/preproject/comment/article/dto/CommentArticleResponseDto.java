package com.homunculus.preproject.comment.article.dto;

import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.response.details.CommentArticleResponseDetails;

import java.time.LocalDateTime;
import java.util.List;

public class CommentArticleResponseDto {
    private String message;
    private Integer messageCount;
    private List<Comments> comments;

    private CommentArticle.CommentArticleStatus status;
    public String getStatus() { return status.getStatus(); }

    public static class Comments {
        private Long id;
        private Long content;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;    // todo : 필드명 주의

        private CommentArticle.CommentArticleStatus status;
        public String getStatus() { return status.getStatus(); }
    }

}
