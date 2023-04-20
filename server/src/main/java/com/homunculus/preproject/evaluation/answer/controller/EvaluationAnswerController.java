package com.homunculus.preproject.evaluation.answer.controller;

import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerDto;
import com.homunculus.preproject.evaluation.answer.dto.EvaluationAnswerSimpleResponseDto;
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

    private enum EvaluationAnswerSimpleResponseMessages {
        EVALUATION_ANSWER_MESSAGE_POST("평가를 등록했습니다."),
        EVALUATION_ANSWER_MESSAGE_DELETE("평가를 삭제했습니다.");

        @Getter
        private final String message;

        EvaluationAnswerSimpleResponseMessages(String message) {
            this.message = message;
        }
    }

    public static EvaluationAnswerSimpleResponseDto createEvaluationAnswerSimpleResponseDto(
                  EvaluationAnswerSimpleResponseMessages evaluationAnswerSimpleResponseMessages) {
        EvaluationAnswerSimpleResponseDto responseDto = new EvaluationAnswerSimpleResponseDto();
        responseDto.setMessage(evaluationAnswerSimpleResponseMessages.getMessage());

        return responseDto;
    }


    @PostMapping(EVALUATION_ANSWER_DEFAULT_URL + "/{answerId}" + EVALUATION_ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity postEvaluationAnswer(@Valid @RequestBody EvaluationAnswerDto.Post evaluationAnswerDtoPost,
                                               @PathVariable("answerId") @Positive Long answerId) {
        evaluationAnswerDtoPost.setAnswerId(answerId);
        evaluationAnswerService.createEvaluationAnswer(mapper.evaluationAnswerPostDtoToEvaluationAnswer(evaluationAnswerDtoPost));

        EvaluationAnswerSimpleResponseDto responseDto = createEvaluationAnswerSimpleResponseDto(EvaluationAnswerSimpleResponseMessages.EVALUATION_ANSWER_MESSAGE_POST);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping(EVALUATION_ANSWER_DEFAULT_URL + "/{answerId}" + EVALUATION_ANSWER_DEFAULT_URL_DETAIL + "/{evaluationId}")
    public ResponseEntity deleteEvaluationAnswer(@PathVariable("answerId") @Positive Long answerId,
                                                 @PathVariable("evaluationId") @Positive Long evaluationId) {
        evaluationAnswerService.deleteEvaluationAnswer(answerId, evaluationId);

        EvaluationAnswerSimpleResponseDto responseDto = createEvaluationAnswerSimpleResponseDto(EvaluationAnswerSimpleResponseMessages.EVALUATION_ANSWER_MESSAGE_DELETE);
        return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
    }
}
