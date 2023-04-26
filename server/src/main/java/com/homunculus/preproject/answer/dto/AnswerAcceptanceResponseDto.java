package com.homunculus.preproject.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnswerAcceptanceResponseDto {
    private Long articleId;
    private Long answerId;
    private Boolean isAccepted;
    private String message;
}

