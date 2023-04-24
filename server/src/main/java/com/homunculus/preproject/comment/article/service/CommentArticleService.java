package com.homunculus.preproject.comment.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.comment.article.repository.CommentArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentArticleService {

    private final CommentArticleRepository commentArticleRepository;

    public CommentArticle createCommentArticle(CommentArticle comment) {

        return commentArticleRepository.save(comment);
    }

    public CommentArticle updateCommentArticle(CommentArticle comment) {
        CommentArticle findComment = findVerifiedArticle(comment);

        checkAllowedMember(findComment);

        return CustomBeanUtils.copyNonNullProperties(comment, findComment);
    }

    public CommentArticle deleteCommentArticle(Long articleId, Long commentId) {
        CommentArticle deletedComment = findVerifiedCommentArticle(articleId, commentId);

        checkAllowedMember(deletedComment);

        commentArticleRepository.deleteById(commentId);

        return deletedComment;
    }

    @Transactional(readOnly = true)
    public Page<CommentArticle> findCommentArticles(Long articleId, Integer page, Integer size) {
        return commentArticleRepository.findCommentArticlesByArticleArticleId(
                articleId,
                PageRequest.of(page, size, Sort.by("commentAnswerId"))
        );
    }

    public static void checkAllowedMember (CommentArticle commentArticle) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User connectedUser = (User) authentication.getPrincipal();
        if (connectedUser == null)
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);
        if ( !commentArticle.getMember().getEmail().equals(connectedUser.getUsername()) ) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_MEMBER_NOT_ALLOWED);
        }
    }

    private CommentArticle findVerifiedCommentArticle(Long articleId, Long commentId) {
        CommentArticle comment = new CommentArticle();
        comment.setCommentArticleId(commentId);

        Article article = new Article();
        article.setArticleId(articleId);
        comment.setArticle(article);

        return findVerifiedArticle(comment);
    }

    @Transactional(readOnly = true)
    public CommentArticle findVerifiedArticle(CommentArticle comment) {
        Optional<CommentArticle> optionalComment = commentArticleRepository.findById(comment.getCommentArticleId());

        CommentArticle findComment = optionalComment.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND)
        );

        if(!Objects.equals(findComment.getArticle().getArticleId(), comment.getArticle().getArticleId()))
            throw new BusinessLogicException(ExceptionCode.COMMENT_ARTICLE_NOT_MATCHED);

        return findComment;
    }
}
