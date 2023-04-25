package com.homunculus.preproject.comment.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.comment.article.repository.CommentArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.service.MemberService;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentArticleService {

    private final CommentArticleRepository commentArticleRepository;
    private final MemberService memberService;

    public CommentArticle createCommentArticle(CommentArticle comment) {

        checkAllowedMember(comment, true);

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

    public void checkAllowedMember (CommentArticle commentArticle) {
        checkAllowedMember(commentArticle, false);
    }
    public void checkAllowedMember (CommentArticle commentArticle, boolean isCommentArticlePost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);

        Object principal = authentication.getPrincipal();
        if (principal == null)
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);

        UserDetails userDetails = (UserDetails) principal;
        final String email = userDetails.getUsername();

        if ( !isCommentArticlePost ) {
            if (!commentArticle.getMember().getEmail().equals(email)) {
                throw new BusinessLogicException(ExceptionCode.COMMENT_MEMBER_NOT_ALLOWED);
            }
        }

        Member member = memberService.findVerifiedMemberByEmail(email);
        commentArticle.setMember(member);
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
