package com.homunculus.preproject.comment.answer.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentAnswerDto {

    @Getter
    public static class Post {
        @NotNull
        private String content;
    }

    @Getter
    public static class Patch {
        private Long commentId;

        @Nullable
        private String content;
    }
}
