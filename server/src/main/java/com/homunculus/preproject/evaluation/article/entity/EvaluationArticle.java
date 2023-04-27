package com.homunculus.preproject.evaluation.article.entity;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class EvaluationArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;
    private String evaluationArticleScore;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
    private EvaluationArticleStatus evaluationArticleStatus = EvaluationArticleStatus.EVALUATION_ARTICLE_NOTHING;

    public enum EvaluationArticleStatus {
        EVALUATION_ARTICLE_NOTHING("0"),
        EVALUATION_ARTICLE_LIKE("+1"),
        EVALUATION_ARTICLE_DISLIKE("-1");

        private final @Getter String status;

        EvaluationArticleStatus(String status) {
            this.status = status;
        }
    }

    public void addEvaluationScore(EvaluationArticle.EvaluationArticleStatus status) {
        Article article = this.getArticle();
        int additionalScore = Integer.parseInt(status.getStatus());
        int score = article.getEvaluationScore() + additionalScore;
        article.setEvaluationScore(score);
    }
}
