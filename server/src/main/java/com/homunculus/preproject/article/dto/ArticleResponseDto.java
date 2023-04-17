package com.homunculus.preproject.article.dto;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.response.details.AnswerResponseDetails;
import com.homunculus.preproject.response.details.ArticleResponseDetails;
import com.homunculus.preproject.response.details.CommentArticleResponseDetails;
import com.homunculus.preproject.response.details.UserResponseDetails;

import java.util.List;

public class ArticleResponseDto {
    private String message;
    private ArticleResponseDetails article;
    private UserResponseDetails user;
    private List<CommentArticleResponseDetails> comments;
    private List<AnswerResponseDetails> answers;

    private Article.ArticleStatus status;
    public String getStatus() { return status.getStatus(); }
}
