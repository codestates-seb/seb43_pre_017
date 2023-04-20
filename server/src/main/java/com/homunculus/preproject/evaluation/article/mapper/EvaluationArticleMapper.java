package com.homunculus.preproject.evaluation.article.mapper;

import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleResponseDto;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationArticleMapper {
    EvaluationArticle evaluationArticlePostDtoToEvaluationArticle(EvaluationArticleDto.Post evaluationDtoPost);

    EvaluationArticleResponseDto evaluationArticleToEvaluationArticleResponseDto(EvaluationArticle evaluation);
}
