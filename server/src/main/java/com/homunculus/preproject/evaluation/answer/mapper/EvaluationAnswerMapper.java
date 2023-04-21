package com.homunculus.preproject.evaluation.answer.mapper;

import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerResponseDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerSimpleResponseDto;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvaluationAnswerMapper {
    @Mapping(source = "evaluationScore", target = "evaluationAnswerScore")
    EvaluationAnswer evaluationAnswerPostDtoToEvaluationAnswer(EvaluationAnswerDto.Post evaluationDtoPost);

    default EvaluationAnswerSimpleResponseDto evaluationAnswerToevaluationAnswerSimpleResponseDto(EvaluationAnswer evaluationAnswer,
                                                                                                  EvaluationAnswerSimpleResponseMessages evaluationAnswerSimpleResponseMessages) {
        EvaluationAnswerSimpleResponseDto responseDto = new EvaluationAnswerSimpleResponseDto();
        responseDto.setMessage(evaluationAnswerSimpleResponseMessages.getMessage());
        responseDto.setAnswerId(evaluationAnswer.getAnswer().getAnswerId());
        responseDto.setEvaluationId(evaluationAnswer.getEvaluationId());

        return responseDto;
    }
}
