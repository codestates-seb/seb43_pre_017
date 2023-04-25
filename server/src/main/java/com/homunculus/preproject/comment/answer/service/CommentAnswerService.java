package com.homunculus.preproject.comment.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.repository.CommentAnswerRepository;
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
public class CommentAnswerService {

    private final CommentAnswerRepository commentAnswerRepository;
    private final MemberService memberService;

    public CommentAnswer createCommentAnswer(CommentAnswer comment) {

        comment.setMember(
            checkAllowedMember(comment, true)
        );

        return commentAnswerRepository.save(comment);
    }

    public CommentAnswer updateCommentAnswer(CommentAnswer comment) {
        CommentAnswer findComment = findVerifiedAnswer(comment);

        findComment.setMember(
            checkAllowedMember(findComment, false)
        );

        return CustomBeanUtils.copyNonNullProperties(comment, findComment);
    }

    public CommentAnswer deleteCommentAnswer(Long answerId, Long commentId) {
        CommentAnswer deletedComment = findVerifiedCommentAnswer(answerId, commentId);

        checkAllowedMember(deletedComment, false);

        commentAnswerRepository.deleteById(commentId);

        return deletedComment;
    }

    @Transactional(readOnly = true)
    public Page<CommentAnswer> findCommentAnswers(Long answerId, Integer page, Integer size) {
        return commentAnswerRepository.findCommentAnswersByAnswerAnswerId(
                answerId,
                PageRequest.of(page, size, Sort.by("commentAnswerId"))
        );
    }

    public Member checkAllowedMember (CommentAnswer commentAnswer, boolean isCommentAnswerPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);

        Object principal = authentication.getPrincipal();
        final String email = principal.toString();

        if ( !isCommentAnswerPost ) {
            if (!commentAnswer.getMember().getEmail().equals(email)) {
                throw new BusinessLogicException(ExceptionCode.COMMENT_MEMBER_NOT_ALLOWED);
            }
        }

        return memberService.findVerifiedMemberByEmail(email);
    }

    private CommentAnswer findVerifiedCommentAnswer(Long answerId, Long commentId) {
        CommentAnswer comment = new CommentAnswer();
        comment.setCommentAnswerId(commentId);

        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        comment.setAnswer(answer);

        return findVerifiedAnswer(comment);
    }

    @Transactional(readOnly = true)
    public CommentAnswer findVerifiedAnswer(CommentAnswer comment) {
        Optional<CommentAnswer> optionalComment = commentAnswerRepository.findById(comment.getCommentAnswerId());

        CommentAnswer findComment = optionalComment.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND)
        );

        if(!Objects.equals(findComment.getAnswer().getAnswerId(), comment.getAnswer().getAnswerId()))
            throw new BusinessLogicException(ExceptionCode.COMMENT_ANSWER_NOT_MATCHED);

        return findComment;
    }

}
