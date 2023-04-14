package com.homunculus.preproject.article.entity;

import com.homunculus.preproject.audit.Auditable;
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


    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ArticleStatus articleStatus = ArticleStatus.ARTICLE_1;

    public enum ArticleStatus {
        ARTICLE_1("1"),
        ARTICLE_2("2"),
        ARTICLE_3("3");

        private @Getter String status;

        ArticleStatus(String status) {
            this.status = status;
        }
    }
}
