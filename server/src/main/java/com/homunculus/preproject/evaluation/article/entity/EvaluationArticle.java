package com.homunculus.preproject.evaluation.article.entity;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.entity.MemberDetails;
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
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBERDETAILS_ID")
    private MemberDetails memberDetails;

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
