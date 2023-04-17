package com.homunculus.preproject.comment.article.dto;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public class CommentArticleDto {

    @Getter
    public static class Post {
        @NotNull
        private String content;
    }

    @Getter
    public static class Patch {
        @Nullable
        private String content;
    }
}
