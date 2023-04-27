package com.homunculus.preproject.evaluation.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EvaluationArticleSimpleResponseDto {
    private Long articleId;
    private Long evaluationId;
    private String message;
    private Integer evaluationScore;
}
