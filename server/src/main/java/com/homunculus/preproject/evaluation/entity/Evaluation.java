package com.homunculus.preproject.evaluation.entity;

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
public class Evaluation {
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
    private EvaluationStatus evaluationStatus = EvaluationStatus.EVALUATION_NOTHING;

    public enum EvaluationStatus {
        EVALUATION_NOTHING("0"),
        EVALUATION_LIKE("+1"),
        EVALUATION_DISLIKE("-1");

        private final @Getter String status;

        EvaluationStatus(String status) {
            this.status = status;
        }
    }
}
