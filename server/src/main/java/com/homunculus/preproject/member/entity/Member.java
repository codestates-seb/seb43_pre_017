package com.homunculus.preproject.member.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 300, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentArticle> commentArticles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentAnswer> commentAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EvaluationArticle> evaluationArticles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EvaluationAnswer> evaluationAnswers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public enum MemberRole {
        ROLE_USER,
        ROLE_ADMIN

    }

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public enum MemberStatus {
        MEMBER_ACTIVE("활동 상태"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        private final @Getter String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
