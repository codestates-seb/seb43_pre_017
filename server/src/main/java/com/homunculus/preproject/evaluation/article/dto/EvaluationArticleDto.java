package com.homunculus.preproject.evaluation.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

public class EvaluationArticleDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long articleId;

        @Pattern(regexp = "^(?:-1|0|\\+1)$", message = "평가는 문자열 타입의 \"-1\"과 \"0\"과 \"+1\" 중 하나여야 합니다.")
        private String evaluationScore;
    }
}
