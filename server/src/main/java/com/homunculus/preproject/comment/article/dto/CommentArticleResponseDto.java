package com.homunculus.preproject.comment.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentArticleResponseDto {
    private String message;
    private Integer messageCount;
    private List<Comments> comments;

    public static class Comments {
        private Long id;
        private Long content;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
