package com.homunculus.preproject.evaluation.answer.mapper;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerSimpleResponseDto;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationAnswerMapper {

    default EvaluationAnswer evaluationAnswerPostDtoToEvaluationAnswer(EvaluationAnswerDto.Post evaluationDtoPost) {
        EvaluationAnswer result = new EvaluationAnswer();

        Answer answer = new Answer();
        answer.setAnswerId(evaluationDtoPost.getAnswerId());
        result.setAnswer(answer);

        for( EvaluationAnswer.EvaluationAnswerStatus status : EvaluationAnswer.EvaluationAnswerStatus.values() ) {
            if( evaluationDtoPost.getEvaluationScore().equals(status.getStatus()) )
                result.setEvaluationAnswerStatus(status);
        }

        return result;
    }

    default EvaluationAnswerSimpleResponseDto evaluationAnswerToevaluationAnswerSimpleResponseDto(EvaluationAnswer evaluationAnswer,
                                                                                                  EvaluationAnswerSimpleResponseMessages evaluationAnswerSimpleResponseMessages) {
        EvaluationAnswerSimpleResponseDto responseDto = new EvaluationAnswerSimpleResponseDto();
        responseDto.setMessage(evaluationAnswerSimpleResponseMessages.getMessage());
        responseDto.setAnswerId(evaluationAnswer.getAnswer().getAnswerId());
        responseDto.setEvaluationId(evaluationAnswer.getEvaluationAnswerId());
        responseDto.setEvaluationScore(evaluationAnswer.getAnswer().getEvaluationScore());

        return responseDto;
    }
}
