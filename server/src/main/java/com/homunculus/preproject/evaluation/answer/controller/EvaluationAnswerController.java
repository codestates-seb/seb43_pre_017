package com.homunculus.preproject.evaluation.answer.controller;

import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerSimpleResponseDto;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.evaluation.answer.mapper.EvaluationAnswerMapper;
import com.homunculus.preproject.evaluation.answer.mapper.EvaluationAnswerSimpleResponseMessages;
import com.homunculus.preproject.evaluation.answer.service.EvaluationAnswerService;
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
public class EvaluationAnswerController {
    private static final String EVALUATION_ANSWER_DEFAULT_URL = "/api/answer";
    private static final String EVALUATION_ANSWER_DEFAULT_URL_DETAIL = "/evaluation";

    private final EvaluationAnswerService evaluationAnswerService;
    private final EvaluationAnswerMapper mapper;


    @PostMapping(EVALUATION_ANSWER_DEFAULT_URL + "/{answerId}" + EVALUATION_ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity postEvaluationAnswer(@Valid @RequestBody EvaluationAnswerDto.Post evaluationAnswerDtoPost,
                                               @PathVariable("answerId") @Positive Long answerId) {
        evaluationAnswerDtoPost.setAnswerId(answerId);
        EvaluationAnswer createdEvaluationAnswer = evaluationAnswerService.createEvaluationAnswer(
                mapper.evaluationAnswerPostDtoToEvaluationAnswer(evaluationAnswerDtoPost));

        return new ResponseEntity<>(
                mapper.evaluationAnswerToevaluationAnswerSimpleResponseDto(createdEvaluationAnswer,
                        EvaluationAnswerSimpleResponseMessages.EVALUATION_ANSWER_MESSAGE_POST),
                HttpStatus.CREATED);
    }

    @DeleteMapping(EVALUATION_ANSWER_DEFAULT_URL + "/{answerId}" + EVALUATION_ANSWER_DEFAULT_URL_DETAIL + "/{evaluationId}")
    public ResponseEntity deleteEvaluationAnswer(@PathVariable("answerId") @Positive Long answerId,
                                                 @PathVariable("evaluationId") @Positive Long evaluationId) {
        EvaluationAnswer deletedEvaluationAnswer = evaluationAnswerService.deleteEvaluationAnswer(answerId, evaluationId);

        return new ResponseEntity<>(
                mapper.evaluationAnswerToevaluationAnswerSimpleResponseDto(deletedEvaluationAnswer,
                        EvaluationAnswerSimpleResponseMessages.EVALUATION_ANSWER_MESSAGE_DELETE),
                HttpStatus.NO_CONTENT);
    }
}
