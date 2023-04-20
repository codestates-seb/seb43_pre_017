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
    private User user;

    private List<Comments> comments;
    private List<Answers> answers;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Article {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class User {
        private Long id;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Comments {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Answers {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private List<Comments> comments;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Comments {
            private Long id;
            private String content;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
        }
    }
}
