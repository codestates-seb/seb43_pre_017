package com.homunculus.preproject.evaluation.answer.mapper;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerResponseDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerSimpleResponseDto;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvaluationAnswerMapper {
    @Mapping(source = "evaluationScore", target = "evaluationAnswerScore")
    default EvaluationAnswer evaluationAnswerPostDtoToEvaluationAnswer(EvaluationAnswerDto.Post evaluationDtoPost) {
        EvaluationAnswer result = new EvaluationAnswer();

        Answer answer = new Answer();
        answer.setAnswerId(evaluationDtoPost.getAnswerId());
        result.setAnswer(answer);

        result.setEvaluationAnswerStatus(
                EvaluationAnswer.EvaluationAnswerStatus.valueOf(
                        evaluationDtoPost.getEvaluationScore()
                ));

        return result;
    }

    default EvaluationAnswerSimpleResponseDto evaluationAnswerToevaluationAnswerSimpleResponseDto(EvaluationAnswer evaluationAnswer,
                                                                                                  EvaluationAnswerSimpleResponseMessages evaluationAnswerSimpleResponseMessages) {
        EvaluationAnswerSimpleResponseDto responseDto = new EvaluationAnswerSimpleResponseDto();
        responseDto.setMessage(evaluationAnswerSimpleResponseMessages.getMessage());
        responseDto.setAnswerId(evaluationAnswer.getAnswer().getAnswerId());
        responseDto.setEvaluationId(evaluationAnswer.getEvaluationAnswerId());

        return responseDto;
    }
}
