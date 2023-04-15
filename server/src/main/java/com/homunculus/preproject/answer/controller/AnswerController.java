package com.homunculus.preproject.answer.controller;

import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.nio.file.Path;
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


    @PostMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity postAnswer (@PathVariable("articleId") @Positive Long articleId,
                                      @Valid @RequestBody AnswerDto.Post answerDtoPost) {
        Answer answer = mapper.answerPostDtoToAnswer(answerDtoPost);
        Answer createdAnswer = answerService.createAnswer(answer, articleId);

        AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(createdAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("articleId") @Positive Long articleId,
                                      @PathVariable("answerId") @Positive Long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerDtoPatch) {
        answerDtoPatch.setAnswerId(answerId);
        Answer answer = mapper.answerPatchDtoToAnswer(answerDtoPatch);
        Answer updatedAnswer = answerService.updateAnswer(answer, articleId);

        AnswerResponseDto responseDto = mapper.answerToAnswerResponseDto(updatedAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_ALL_MAPPING_URL_DETAIL)
    public ResponseEntity getAllAnswers(@PathVariable("articleId") @Positive Long articleId,
                                        @RequestParam("page") @Positive Integer page,
                                        @RequestParam("size") @Positive Integer size) {
        Page<Answer> pageAnswers = answerService.findAnswers(page - 1, size);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.answersToAnswerResponseDtos(answers), pageAnswers),
                HttpStatus.OK);
    }

    @DeleteMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("articleId") @Positive Long articleId,
                                       @PathVariable("answerId") @Positive Long answerId) {

        answerService.deleteAnswer(articleId, answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
