package com.homunculus.preproject.answer.dto;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.response.details.AnswerResponseDetails;
import com.homunculus.preproject.response.details.CommentAnswerResponseDetails;

import java.time.LocalDateTime;
import java.util.List;

public class AnswerResponseDto {
    private String message;
    private Integer messageCount;    // todo : FE 와 상의 필요
    private List<Answers> answers;
    private Answer.AnswerStatus status;

    public String getStatus() {
        return status.getStatus();
    }

    public static class Answers {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;    // todo : 필드명 주의

        private Answer.AnswerStatus status;
        public String getStatus() { return status.getStatus(); }
    }
}
