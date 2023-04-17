package com.homunculus.preproject.comment.answer.dto;

import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.response.details.CommentAnswerResponseDetails;

import java.util.List;

public class CommentAnswerResponseDto {
    private String message;
    private Integer messageCount;
    List<CommentAnswerResponseDetails> comments;

    private CommentAnswer.CommentAnswerStatus status;
    public String getStatus() { return status.getStatus(); }
}
