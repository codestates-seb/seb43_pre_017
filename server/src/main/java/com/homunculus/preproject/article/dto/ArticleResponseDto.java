package com.homunculus.preproject.article.dto;

import com.homunculus.preproject.article.entity.Article;
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
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User user;
        private Integer answerCount;    // todo: 이 질문글에 달린 답변글 갯수 카운팅을 매퍼에서 구현해야함

        private Article.ArticleStatus status;
        public String getStatus() { return status.getStatus(); }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class User {
        private Long id;
        private String name;
    }
}
