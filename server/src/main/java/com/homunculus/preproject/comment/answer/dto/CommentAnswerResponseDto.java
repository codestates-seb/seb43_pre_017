package com.homunculus.preproject.comment.answer.dto;

import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.response.details.CommentAnswerResponseDetails;

import java.time.LocalDateTime;
import java.util.List;

public class CommentAnswerResponseDto {
    private String message;
    private Integer messageCount;
    private List<Comments> comments;

    private CommentAnswer.CommentAnswerStatus status;
    public String getStatus() { return status.getStatus(); }

    public static class Comments {
        private Long id;
        private String content;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;    // todo : 필드명 주의

        private CommentAnswer.CommentAnswerStatus status;
        public String getStatus() { return status.getStatus(); }
    }
}
