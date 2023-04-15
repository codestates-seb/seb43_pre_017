package com.homunculus.preproject.answer.controller;

import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import com.homunculus.preproject.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@Validated
public class AnswerController {

    private static final String ANSWER_DEFAULT_URL = "/api/article";
    private static final String ANSWER_DEFAULT_URL_DETAIL = "/answer";

    private static final String ANSWER_ALL_MAPPING_URL = "/api/article";
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
}
