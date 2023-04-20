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
    private Integer evaluationScore;

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
        private Count count;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Count {
            private Integer comments;    // todo: 이 질문글에 달린 댓글 갯수 카운팅을 매퍼에서 구현해야함
            private Integer answers;    // todo: 이 질문글에 달린 답변글 갯수 카운팅을 매퍼에서 구현해야함
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class User {
        private Long id;
        private String name;
    }
}
