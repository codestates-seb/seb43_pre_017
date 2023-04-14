package com.homunculus.preproject.question.dto;

import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class QuestionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long questionId;

        @NotSpace
        private String title;

        @NotSpace
        private String content;
    }

}
