package com.homunculus.preproject.comment.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class CommentDto {

    @Getter
    public static class Post {
        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }

    @Getter
    public static class Patch {
        @NotSpace
        private String title;

        @NotSpace
        private String content;
    }
}
