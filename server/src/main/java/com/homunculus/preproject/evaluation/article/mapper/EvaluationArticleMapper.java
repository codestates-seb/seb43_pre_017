package com.homunculus.preproject.evaluation.article.mapper;

import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleResponseDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleSimpleResponseDto;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvaluationArticleMapper {
    @Mapping(source = "evaluationScore", target = "evaluationArticleScore")
    EvaluationArticle evaluationArticlePostDtoToEvaluationArticle(EvaluationArticleDto.Post evaluationDtoPost);

    default EvaluationArticleSimpleResponseDto evaluationArticleToEvaluationArticleSimpleResponseDto(EvaluationArticle evaluationArticle,
                                                                                                     EvaluationArticleSimpleResponseMessages evaluationArticleSimpleResponseMessages) {
        EvaluationArticleSimpleResponseDto responseDto = new EvaluationArticleSimpleResponseDto();
        responseDto.setMessage(evaluationArticleSimpleResponseMessages.getMessage());
        responseDto.setArticleId(evaluationArticle.getArticle().getArticleId());
        responseDto.setEvaluationId(evaluationArticle.getEvaluationId());

        return responseDto;
    }

}
