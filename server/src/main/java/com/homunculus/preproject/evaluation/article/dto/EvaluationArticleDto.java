package com.homunculus.preproject.evaluation.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EvaluationArticleDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long articleId;
    }
}
