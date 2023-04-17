package com.homunculus.preproject.comment.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.comment.entity.CommentAnswer;
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

    public Page<CommentAnswer> findCommentsAnswer(Integer page, Integer size) {return null;
    }
}
