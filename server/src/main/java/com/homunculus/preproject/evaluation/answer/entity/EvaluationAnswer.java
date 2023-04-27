package com.homunculus.preproject.evaluation.answer.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.member.entity.Member;
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
    private Long evaluationAnswerId;
    private String evaluationAnswerScore;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
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

    public void addEvaluationScore(EvaluationAnswerStatus status) {
        Answer answer = this.getAnswer();
        int additionalScore = Integer.parseInt(status.getStatus());
        int score = answer.getEvaluationScore() + additionalScore;
        answer.setEvaluationScore(score);
    }
}
