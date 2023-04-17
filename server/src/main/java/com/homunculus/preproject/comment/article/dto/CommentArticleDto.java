package com.homunculus.preproject.comment.article.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class CommentArticleDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        private String content;
        private Long articleId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long commentId;
        private Long answerId;
        private String content;
    }
}
