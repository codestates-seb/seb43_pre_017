package com.homunculus.preproject.article.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import com.homunculus.preproject.popularity.entity.Popularity;
import com.homunculus.preproject.user.entity.User;
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
public class Article extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long viewCount;

    @OneToOne
    private Popularity popularity;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "article")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<EvaluationArticle> evaluationArticles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ArticleStatus articleStatus = ArticleStatus.ARTICLE_REGISTRY;

    public enum ArticleStatus {
        ARTICLE_REGISTRY("등록상태"),
        ARTICLE_DELETE("삭제상태");

        private @Getter String status;

        ArticleStatus(String status) {
            this.status = status;
        }
    }
}
