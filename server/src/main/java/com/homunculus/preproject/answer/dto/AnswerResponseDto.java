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
    private Integer messageCount;
    private Long articleId;

    private List<Answers> answers;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Answers {
        private Long id;
        private String content;
        private Integer evaluationScore;
        private Boolean isAccepted;
        private Member member;
        private Count count;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Member {
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
