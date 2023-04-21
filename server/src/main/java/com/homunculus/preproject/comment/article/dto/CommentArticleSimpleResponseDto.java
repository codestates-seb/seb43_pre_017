package com.homunculus.preproject.comment.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentArticleSimpleResponseDto {
    private Long id;
    private String message;
}
