package com.homunculus.preproject.evaluation.article.mapper;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleDto;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleSimpleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationArticleMapper {

    default EvaluationArticle evaluationArticlePostDtoToEvaluationArticle(EvaluationArticleDto.Post evaluationDtoPost) {
        EvaluationArticle result = new EvaluationArticle();

        Article article = new Article();
        article.setArticleId(evaluationDtoPost.getArticleId());
        result.setArticle(article);

        for( EvaluationArticle.EvaluationArticleStatus status : EvaluationArticle.EvaluationArticleStatus.values() ) {
            if( evaluationDtoPost.getEvaluationScore().equals(status.getStatus()) )
                result.setEvaluationArticleStatus(status);
        }

        return result;
    }

    default EvaluationArticleSimpleResponseDto evaluationArticleToEvaluationArticleSimpleResponseDto(EvaluationArticle evaluationArticle,
                                                                                                     EvaluationArticleSimpleResponseMessages evaluationArticleSimpleResponseMessages) {
        EvaluationArticleSimpleResponseDto responseDto = new EvaluationArticleSimpleResponseDto();
        responseDto.setMessage(evaluationArticleSimpleResponseMessages.getMessage());
        responseDto.setArticleId(evaluationArticle.getArticle().getArticleId());
        responseDto.setEvaluationId(evaluationArticle.getEvaluationId());
        responseDto.setEvaluationScore(evaluationArticle.getArticle().getEvaluationScore());

        return responseDto;
    }
}
