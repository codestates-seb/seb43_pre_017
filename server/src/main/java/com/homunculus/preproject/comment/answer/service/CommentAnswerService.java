package com.homunculus.preproject.comment.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.service.AnswerService;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.repository.CommentAnswerRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.utils.AuthenticationUtils;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentAnswerService {

    private final CommentAnswerRepository commentAnswerRepository;
    private final AuthenticationUtils authenticationUtils;
    private final AnswerService answerService;

    public CommentAnswer createCommentAnswer(CommentAnswer comment) {

        comment.setMember(
                authenticationUtils.findMemberWithCheckAllowed(
                        comment.getMember(), true,
                        ExceptionCode.COMMENT_MEMBER_NOT_ALLOWED)
        );

        comment.setAnswer(
                answerService.findVerifiedAnswer(
                        comment.getAnswer(), false)
        );

        return commentAnswerRepository.save(comment);
    }

    public CommentAnswer updateCommentAnswer(CommentAnswer comment) {
        CommentAnswer findComment = findVerifiedAnswer(comment);

        comment.setMember(
                authenticationUtils.findMemberWithCheckAllowed(
                        findComment.getMember(), false,
                        ExceptionCode.COMMENT_MEMBER_NOT_ALLOWED)
        );

        return CustomBeanUtils.copyNonNullProperties(comment, findComment);
    }

    public CommentAnswer deleteCommentAnswer(Long answerId, Long commentId) {
        CommentAnswer deletedComment = findVerifiedCommentAnswer(answerId, commentId);

        authenticationUtils.findMemberWithCheckAllowed(
                deletedComment.getMember(), false,
                ExceptionCode.COMMENT_MEMBER_NOT_ALLOWED);

        commentAnswerRepository.deleteById(commentId);

        return deletedComment;
    }

    @Transactional(readOnly = true)
    public Page<CommentAnswer> findCommentAnswers(Long answerId, Integer page, Integer size) {
        return commentAnswerRepository.findCommentAnswersByAnswerAnswerId(
                answerId,
                PageRequest.of(page, size, Sort.by("commentAnswerId").descending())
        );
    }

    private CommentAnswer findVerifiedCommentAnswer(Long answerId, Long commentId) {
        CommentAnswer comment = new CommentAnswer();
        comment.setCommentAnswerId(commentId);

        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        comment.setAnswer(answer);

        return findVerifiedAnswer(comment);
    }

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
