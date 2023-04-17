package com.homunculus.preproject.comment.answer.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;
import org.springframework.lang.Nullable;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentAnswerDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long answerId;
        private String content;
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
