package com.homunculus.preproject.comment.answer.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class CommentAnswerDto {

    @Getter
    public static class Post {
        @NotBlank
        private String content;
    }

    @Getter
    public static class Patch {
        @NotSpace
        private String content;
    }
}
