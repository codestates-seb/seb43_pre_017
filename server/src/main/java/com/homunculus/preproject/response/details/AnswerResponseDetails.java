package com.homunculus.preproject.response.details;

import com.homunculus.preproject.answer.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class AnswerResponseDetails {
    private Long id;
    private String content;
    private List<CommentAnswerResponseDetails> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;    // todo : 필드명 주의

    private Answer.AnswerStatus status;
    public String getStatus() { return status.getStatus(); }
}
