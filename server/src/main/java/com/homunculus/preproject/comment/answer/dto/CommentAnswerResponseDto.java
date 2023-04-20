package com.homunculus.preproject.comment.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentAnswerResponseDto {
    private String message;
    private Integer messageCount;
    private List<Comments> comments;

    public static class Comments {
        private Long id;
        private String content;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
