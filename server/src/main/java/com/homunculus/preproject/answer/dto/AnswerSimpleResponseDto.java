package com.homunculus.preproject.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AnswerSimpleResponseDto {
    private Long articleId;
    private Long answerId;
    private String message;
}
