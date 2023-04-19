package com.homunculus.preproject.answer.controller;

import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.dto.AnswerSimpleResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class AnswerController {

    private static final String ANSWER_DEFAULT_URL = "/api/article";
    private static final String ANSWER_DEFAULT_URL_DETAIL = "/answer";

    private static final String ANSWER_ALL_MAPPING_URL_DETAIL = "/answers";


    private final AnswerService answerService;
    private final AnswerMapper mapper;


    private enum AnswerSingleResponseMessages {
        ANSWER_MESSAGE_POST("답변을 등록했습니다."),
        ANSWER_MESSAGE_PATCH("답변을 수정했습니다."),
        ANSWER_MESSAGE_DELETE("답변을 삭제했습니다.");

        @Getter
        private final String message;

        AnswerSingleResponseMessages(String message) {
            this.message = message;
        }
    }

    public static AnswerSimpleResponseDto createAnswerSimpleResponseDto(AnswerSingleResponseMessages answerSingleResponseMessages) {
        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(answerSingleResponseMessages.getMessage());

        return responseDto;
    }

    @PostMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity postAnswer (@PathVariable("articleId") @Positive Long articleId,
                                      @Valid @RequestBody AnswerDto.Post answerDtoPost) {
        answerDtoPost.setArticleId(articleId);
        Answer answer = mapper.answerPostDtoToAnswer(answerDtoPost);
        answerService.createAnswer(answer);

        AnswerSimpleResponseDto responseDto = createAnswerSimpleResponseDto(AnswerSingleResponseMessages.ANSWER_MESSAGE_POST);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("articleId") @Positive Long articleId,
                                      @PathVariable("answerId") @Positive Long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerDtoPatch) {
        answerDtoPatch.setArticleId(articleId);
        answerDtoPatch.setAnswerId(answerId);
        Answer answer = mapper.answerPatchDtoToAnswer(answerDtoPatch);
        answerService.updateAnswer(answer);

        return new ResponseEntity<>(
                createAnswerSimpleResponseDto(AnswerSingleResponseMessages.ANSWER_MESSAGE_PATCH),
                HttpStatus.OK
        );
    }

    @GetMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_ALL_MAPPING_URL_DETAIL)
    public ResponseEntity getAllAnswers(@PathVariable("articleId") @Positive Long articleId,
                                        @RequestParam("page") @Positive Integer page,
                                        @RequestParam("size") @Positive Integer size) {
        Page<Answer> pageAnswers = answerService.findAnswers(articleId, page - 1, size);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(
                mapper.answersToAnswerResponseDtos(answers),
                HttpStatus.OK);
    }

    @DeleteMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("articleId") @Positive Long articleId,
                                       @PathVariable("answerId") @Positive Long answerId) {

        answerService.deleteAnswer(articleId, answerId);

        return new ResponseEntity<>(
                createAnswerSimpleResponseDto(AnswerSingleResponseMessages.ANSWER_MESSAGE_DELETE),
                HttpStatus.NO_CONTENT
        );
    }
}
