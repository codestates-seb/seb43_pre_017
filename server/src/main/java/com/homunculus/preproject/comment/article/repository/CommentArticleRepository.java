package com.homunculus.preproject.comment.article.repository;

import com.homunculus.preproject.comment.article.entity.CommentArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentArticleRepository extends JpaRepository<CommentArticle, Long> {
    Optional<CommentArticle> findById(Long commentArticleId);

    Page<CommentArticle> findCommentArticlesByArticleArticleId(Long articleId, Pageable pageable);
}
