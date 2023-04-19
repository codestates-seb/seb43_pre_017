package com.homunculus.preproject.article.dto;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.response.details.*;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDto {
    private String message;
    private ArticleResponseDetails article;
    private UserResponseDetails user;
    private List<Comments> comments;
    private List<Answers> answers;

    private Article.ArticleStatus status;
    public String getStatus() { return status.getStatus(); }

    public static class Comments {
        private Long id;
        private Long content;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;    // todo : 필드명 주의

        private CommentArticle.CommentArticleStatus status;
        public String getStatus() { return status.getStatus(); }
    }
    public static class Answers {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;    // todo : 필드명 주의

        private Answer.AnswerStatus status;
        public String getStatus() { return status.getStatus(); }
    }
}
