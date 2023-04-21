package com.homunculus.preproject.member.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
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
public class MemberDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberDetailsId;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "memberDetails")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "memberDetails")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "memberDetails")
    private List<CommentArticle> commentArticles = new ArrayList<>();

    @OneToMany(mappedBy = "memberDetails")
    private List<CommentAnswer> commentAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "memberDetails")
    private List<EvaluationArticle> evaluationArticles = new ArrayList<>();

    @OneToMany(mappedBy = "memberDetails")
    private List<EvaluationAnswer> evaluationAnswers = new ArrayList<>();
}
