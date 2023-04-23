package com.homunculus.preproject.answer.entity;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.entity.MemberDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer evaluationScore = 0;

    @Column(nullable = false)
    private Boolean accepted = false;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERDETAILS_ID")
    private MemberDetails memberDetails;

    @OneToMany(mappedBy = "answer")
    private List<CommentAnswer> commentAnswers = new ArrayList<>();

    @OneToOne
    private EvaluationAnswer evaluationAnswer;

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

    public void addEvaluationScore(EvaluationAnswer.EvaluationAnswerStatus status) {
        int additionalScore = Integer.parseInt(status.getStatus());
        int score = this.getEvaluationScore() + additionalScore;
        this.setEvaluationScore(score);
    }
}
