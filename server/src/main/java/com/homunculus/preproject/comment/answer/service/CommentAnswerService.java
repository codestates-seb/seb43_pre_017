package com.homunculus.preproject.comment.answer.service;

import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CommentAnswerService {


    public CommentAnswer createCommentAnswer(CommentAnswer comment, Long answerId) {
        return null;
    }

    public CommentAnswer updateCommentAnswer(CommentAnswer comment, Long commentId) {
        return null;
    }

    public void deleteCommentAnswer(Long answerId, Long commentId) {
    }

    public Page<CommentAnswer> findCommentAnswers(Integer page, Integer size) {return null;
    }
}
