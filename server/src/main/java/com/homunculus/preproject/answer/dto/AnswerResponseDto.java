package com.homunculus.preproject.answer.dto;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.response.details.AnswerResponseDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class AnswerResponseDto {
    private String message;
    private Integer messageCount;    // todo : FE 와 상의 필요
    private List<AnswerResponseDetails> answers;

    private Answer.AnswerStatus status;

    public String getStatus() {
        return status.getStatus();
    }
}
