package com.homunculus.preproject.answer.entity;

import com.homunculus.preproject.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_REGISTRY;

    public enum AnswerStatus {
        ANSWER_REGISTRY("등록상태"),
        ANSWER_DELETE("삭제상태");

        @Getter
        private String status;

        AnswerStatus(String status) {
            this.status = status;
        }
    }
}
