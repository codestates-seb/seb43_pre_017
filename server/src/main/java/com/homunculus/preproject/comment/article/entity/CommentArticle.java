package com.homunculus.preproject.comment.article.entity;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CommentArticle extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentArticleId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
    private CommentArticleStatus commentArticleStatus = CommentArticleStatus.COMMENT_ARTICLE_REGISTRY;

    public enum CommentArticleStatus {
        COMMENT_ARTICLE_REGISTRY("등록상태"),
        COMMENT_ARTICLE_DELETE("삭제상태");

        private @Getter String status;

        CommentArticleStatus(String status) {
            this.status = status;
        }
    }
}
