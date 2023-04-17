package com.homunculus.preproject.comment.article.dto;

import com.homunculus.preproject.response.details.CommentArticleResponseDetails;

import java.util.List;

public class CommentArticleResponseDto {
    private String message;
    private Integer messageCount;
    List<CommentArticleResponseDetails> comments;

}
