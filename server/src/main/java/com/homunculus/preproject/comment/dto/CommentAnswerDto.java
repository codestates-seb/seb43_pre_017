package com.homunculus.preproject.comment.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class CommentAnswerDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        @NotSpace
        private Long commentId;
        private String content;
    }
}
