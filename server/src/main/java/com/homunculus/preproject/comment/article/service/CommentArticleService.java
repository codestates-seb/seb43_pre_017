package com.homunculus.preproject.comment.article.service;

import com.homunculus.preproject.comment.article.entity.CommentArticle;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CommentArticleService {


    public CommentArticle createCommentArticle(CommentArticle comment) {
        return null;
    }

    public CommentArticle updateCommentArticle(CommentArticle comment) {
        return null;
    }

    public void deleteCommentArticle(Long answerId, Long commentId) {
    }

    public Page<CommentArticle> findCommentArticles(Long articleId, Integer page, Integer size) {return null;
    }
}
