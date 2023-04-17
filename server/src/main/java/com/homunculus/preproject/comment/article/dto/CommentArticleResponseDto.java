package com.homunculus.preproject.comment.article.dto;

import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.response.details.CommentArticleResponseDetails;

import java.util.List;

public class CommentArticleResponseDto {
    private String message;
    private Integer messageCount;
    List<CommentArticleResponseDetails> comments;

    private CommentArticle.CommentArticleStatus status;
    public String getStatus() { return status.getStatus(); }
}
