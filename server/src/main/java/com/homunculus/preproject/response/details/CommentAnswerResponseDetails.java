package com.homunculus.preproject.response.details;

import com.homunculus.preproject.comment.answer.entity.CommentAnswer;

import java.time.LocalDateTime;

public class CommentAnswerResponseDetails {
    private Long id;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;    // todo : 필드명 주의

    private CommentAnswer.CommentAnswerStatus status;
    public String getStatus() { return status.getStatus(); }
}
