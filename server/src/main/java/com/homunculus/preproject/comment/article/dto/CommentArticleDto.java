package com.homunculus.preproject.comment.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentArticleDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long articleId;
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long articleId;
        private Long commentId;
        private String content;
    }
}
