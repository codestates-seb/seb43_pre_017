package com.homunculus.preproject.comment.answer.service;

import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CommentAnswerService {


    public CommentAnswer createCommentAnswer(CommentAnswer comment) {
        return null;
    }

    public CommentAnswer updateCommentAnswer(CommentAnswer comment) {
        return null;
    }

    public CommentAnswer deleteCommentAnswer(Long answerId, Long commentId) { return null; }

    public Page<CommentAnswer> findCommentAnswers(Long answerId, Integer page, Integer size) {return null; }
}
