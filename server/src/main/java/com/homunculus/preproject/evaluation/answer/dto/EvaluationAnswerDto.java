package com.homunculus.preproject.evaluation.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EvaluationAnswerDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long answerId;
    }
}
