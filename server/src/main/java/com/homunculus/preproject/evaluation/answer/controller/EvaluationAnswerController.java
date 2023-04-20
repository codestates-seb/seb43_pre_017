package com.homunculus.preproject.evaluation.answer.controller;

import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerResponseDto;
import com.homunculus.preproject.evaluation.answer.entity.EvaluationAnswer;
import com.homunculus.preproject.evaluation.answer.mapper.EvaluationAnswerMapper;
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
        EvaluationAnswer evaluationAnswer = evaluationAnswerService.createEvaluationAnswer(mapper.evaluationAnswerPostDtoToEvaluationAnswer(evaluationAnswerDtoPost));

        EvaluationAnswerResponseDto responseDto = mapper.evaluationAnswerToEvaluationAnswerResponseDto(evaluationAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(EVALUATION_ANSWER_DEFAULT_URL + "/{answerId}" + EVALUATION_ANSWER_DEFAULT_URL_DETAIL + "/{evaluationId}")
    public ResponseEntity deleteEvaluationAnswer(@PathVariable("answerId") @Positive Long answerId,
                                                 @PathVariable("evaluationId") @Positive Long evaluationId) {
        evaluationAnswerService.deleteEvaluationAnswer(answerId, evaluationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
