package com.homunculus.preproject.answer.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnswerDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long articleId;

        @NotNull
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long articleId;
        private Long answerId;

        @Nullable
        private String content;
    }
}
