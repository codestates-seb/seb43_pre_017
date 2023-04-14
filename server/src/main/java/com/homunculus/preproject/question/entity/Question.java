package com.homunculus.preproject.question.entity;

import com.homunculus.preproject.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long viewCount;


    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_1;

    public enum QuestionStatus {
        QUESTION_1("1"),
        QUESTION_2("2"),
        QUESTION_3("3");

        private @Getter String status;

        QuestionStatus(String status) {
            this.status = status;
        }
    }
}
