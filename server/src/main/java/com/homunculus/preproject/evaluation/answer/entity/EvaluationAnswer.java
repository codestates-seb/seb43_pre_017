package com.homunculus.preproject.evaluation.answer.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class EvaluationAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EvaluationAnswerStatus evaluationAnswerStatus = EvaluationAnswerStatus.EVALUATION_ANSWER_NOTHING;

    public enum EvaluationAnswerStatus {
        EVALUATION_ANSWER_NOTHING("0"),
        EVALUATION_ANSWER_LIKE("+1"),
        EVALUATION_ANSWER_DISLIKE("-1");

        private final @Getter String status;

        EvaluationAnswerStatus(String status) {
            this.status = status;
        }
    }
}
