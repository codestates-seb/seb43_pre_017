package com.homunculus.preproject.evaluation.answer.mapper;

import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerResponseDto;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;

public interface EvaluationAnswerMapper {
    EvaluationAnswer evaluationAnswerPostDtoToEvaluationAnswer(EvaluationAnswerDto.Post evaluationDtoPost);

    EvaluationAnswerResponseDto evaluationAnswerToEvaluationAnswerResponseDto(EvaluationAnswer evaluation);
}
