package com.homunculus.preproject.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDetailsDto {
    private String message;
    private Article article;
    private Member member;

    private Count count;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Article {
        private Long id;
        private String title;
        private String content;
        private Integer evaluationScore;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Member {
        private Long id;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Count {
        private Integer comment;
        private Integer answer;
    }
}
