package com.homunculus.preproject.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ArticleSimpleResponseDto {
    private Long articleId;
    private String message;
}
