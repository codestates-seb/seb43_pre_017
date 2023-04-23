package com.homunculus.preproject.evaluation.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EvaluationAnswerSimpleResponseDto {
    private Long answerId;
    private Long evaluationId;
    private String message;
    private Integer evaluationScore;
}
