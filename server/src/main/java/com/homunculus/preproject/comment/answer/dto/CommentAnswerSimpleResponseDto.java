package com.homunculus.preproject.comment.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentAnswerSimpleResponseDto {
    private Long answerId;
    private Long commentId;
    private String message;
}
