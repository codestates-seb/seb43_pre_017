package com.homunculus.preproject.evaluation.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.article.service.ArticleService;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationArticleService {

    private final ArticleRepository articleRepository;

    public EvaluationArticle createEvaluationArticle(EvaluationArticle evaluationArticle) {

        EvaluationArticle findEvaluationArticle = findVerifiedEvaluationArticle(evaluationArticle);
        findEvaluationArticle.addEvaluationScore(evaluationArticle.getEvaluationArticleStatus());
        articleRepository.save(findEvaluationArticle.getArticle());

        return evaluationArticle;
    }

    public EvaluationArticle deleteEvaluationArticle(Long articleId, Long evaluationId) {
        return null;
    }

    private EvaluationArticle findVerifiedEvaluationArticle(EvaluationArticle evaluationArticle) {
        Optional<Article> optionalArticle = articleRepository.findById(evaluationArticle.getArticle().getArticleId());
        Article findArticle = optionalArticle.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );

        ArticleService.checkAllowedMember(findArticle);

        EvaluationArticle result = evaluationArticle;
        result.setArticle(findArticle);

        return result;
    }

}
