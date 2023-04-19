package com.homunculus.preproject.response.details;

import com.homunculus.preproject.answer.entity.Answer;

import java.time.LocalDateTime;
import java.util.List;

public class AnswerResponseDetails {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;    // todo : 필드명 주의

    private Answer.AnswerStatus status;
    public String getStatus() { return status.getStatus(); }

}
