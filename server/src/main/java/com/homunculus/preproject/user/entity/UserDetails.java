package com.homunculus.preproject.user.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
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
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailsId;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "userDetails")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "userDetails")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "userDetails")
    private List<CommentAnswer> commentAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "userDetails")
    private List<EvaluationAnswer> evaluationAnswers = new ArrayList<>();
}
