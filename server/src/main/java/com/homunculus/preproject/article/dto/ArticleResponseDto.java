package com.homunculus.preproject.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ArticleResponseDto {
    private String message;
    private Integer messageCount;

    private List<Articles> articles;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Articles {
        private Long id;
        private String title;
        private String content;
        private Integer evaluationScore;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Member member;
        private Count count;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Count {
            private Integer comments;
            private Integer answers;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Member {
        private Long id;
        private String name;
    }
}
