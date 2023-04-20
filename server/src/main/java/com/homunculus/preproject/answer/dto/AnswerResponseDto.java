package com.homunculus.preproject.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class AnswerResponseDto {
    private String message;
    private Integer messageCount;    // todo : FE 와 상의 필요
    private Long articleId;

    private List<Answers> answers;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Answers {
        private Long id;
        private String content;
        private User user;
        private Count count;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;    // todo : 필드명 주의

        @Getter
        @Setter
        @NoArgsConstructor
        public static class User {
            private Long id;
            private String name;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Count {
            private Integer comments;
        }
    }
}
