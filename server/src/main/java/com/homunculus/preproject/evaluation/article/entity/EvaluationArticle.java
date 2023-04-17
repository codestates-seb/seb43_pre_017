package com.homunculus.preproject.evaluation.article.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.user.entity.User;
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

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EvaluationArticleStatus evaluationAnswerStatus = EvaluationArticleStatus.EVALUATION_ARTICLE_NOTHING;

    public enum EvaluationArticleStatus {
        EVALUATION_ARTICLE_NOTHING("0"),
        EVALUATION_ARTICLE_LIKE("+1"),
        EVALUATION_ARTICLE_DISLIKE("-1");

        private final @Getter String status;

        EvaluationArticleStatus(String status) {
            this.status = status;
        }
    }
}
