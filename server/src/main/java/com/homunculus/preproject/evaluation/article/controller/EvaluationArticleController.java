package com.homunculus.preproject.evaluation.article.controller;

import com.homunculus.preproject.evaluation.answer.controller.EvaluationAnswerController;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerSimpleResponseDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleResponseDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleSimpleResponseDto;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import com.homunculus.preproject.evaluation.article.mapper.EvaluationArticleMapper;
import com.homunculus.preproject.evaluation.article.mapper.EvaluationArticleSimpleResponseMessages;
import com.homunculus.preproject.evaluation.article.service.EvaluationArticleService;
import lombok.Getter;
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
        EvaluationArticle createdEvaluationArticle = evaluationArticleService.createEvaluationArticle(
                mapper.evaluationArticlePostDtoToEvaluationArticle(evaluationArticleDtoPost));

        return new ResponseEntity<>(
                mapper.evaluationArticleToEvaluationArticleSimpleResponseDto(createdEvaluationArticle,
                        EvaluationArticleSimpleResponseMessages.EVALUATION_ARTICLE_MESSAGE_POST),
                HttpStatus.CREATED);
    }

    @DeleteMapping(EVALUATION_ARTICLE_DEFAULT_URL + "/{articleId}" + EVALUATION_ARTICLE_DEFAULT_URL_DETAIL + "/{evaluationId}")
    public ResponseEntity deleteEvaluationArticle(@PathVariable("articleId") @Positive Long articleId,
                                                 @PathVariable("evaluationId") @Positive Long evaluationId) {
        EvaluationArticle deletedEvaluationArticle = evaluationArticleService.deleteEvaluationArticle(articleId, evaluationId);

        return new ResponseEntity<>(
                mapper.evaluationArticleToEvaluationArticleSimpleResponseDto(deletedEvaluationArticle,
                        EvaluationArticleSimpleResponseMessages.EVALUATION_ARTICLE_MESSAGE_DELETE),
                HttpStatus.NO_CONTENT);
    }
}
