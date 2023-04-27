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
    private Long articleId;

    private List<Comments> comments;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Comments {
        private Long id;
        private String content;
        private Member member;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Member {
            private Long id;
            private String name;
        }
    }
}
