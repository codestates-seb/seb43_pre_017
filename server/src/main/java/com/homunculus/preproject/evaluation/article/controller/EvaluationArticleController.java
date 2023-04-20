package com.homunculus.preproject.evaluation.article.controller;

import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleResponseDto;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import com.homunculus.preproject.evaluation.article.mapper.EvaluationArticleMapper;
import com.homunculus.preproject.evaluation.article.service.EvaluationArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@Validated
public class EvaluationArticleController {
    private static final String EVALUATION_ARTICLE_DEFAULT_URL = "/api/article";
    private static final String EVALUATION_ARTICLE_DEFAULT_URL_DETAIL = "/evaluation";

    private final EvaluationArticleService evaluationArticleService;
    private final EvaluationArticleMapper mapper;

    @PostMapping(EVALUATION_ARTICLE_DEFAULT_URL + "/{articleId}" + EVALUATION_ARTICLE_DEFAULT_URL_DETAIL)
    public ResponseEntity postEvaluationArticle(@Valid @RequestBody EvaluationArticleDto.Post evaluationArticleDtoPost,
                                               @PathVariable("articleId") @Positive Long articleId) {
        evaluationArticleDtoPost.setArticleId(articleId);
        EvaluationArticle evaluationArticle = evaluationArticleService.createEvaluationArticle(mapper.evaluationArticlePostDtoToEvaluationArticle(evaluationArticleDtoPost));

        EvaluationArticleResponseDto responseDto = mapper.evaluationArticleToEvaluationArticleResponseDto(evaluationArticle);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(EVALUATION_ARTICLE_DEFAULT_URL + "/{articleId}" + EVALUATION_ARTICLE_DEFAULT_URL_DETAIL + "/{evaluationId}")
    public ResponseEntity deleteEvaluationArticle(@PathVariable("articleId") @Positive Long articleId,
                                                 @PathVariable("evaluationId") @Positive Long evaluationId) {
        evaluationArticleService.deleteEvaluationArticle(articleId, evaluationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
