package com.homunculus.preproject.article.entity;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.popularity.entity.Popularity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
