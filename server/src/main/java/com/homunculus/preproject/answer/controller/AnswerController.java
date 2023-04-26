package com.homunculus.preproject.answer.controller;

import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.mapper.AnswerSimpleResponseMessages;
import com.homunculus.preproject.answer.service.AnswerService;
import com.homunculus.preproject.article.service.ArticleService;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.service.MemberService;
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
    private final MemberService memberService;
    private final ArticleService articleService;
    private final AnswerMapper mapper;


    @PostMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity postAnswer (@PathVariable("articleId") @Positive Long articleId,
                                      @Valid @RequestBody AnswerDto.Post answerDtoPost) {
        answerDtoPost.setArticleId(articleId);
        Answer answer = mapper.answerPostDtoToAnswer(answerDtoPost, memberService, articleService);
        Answer createdAnswer = answerService.createAnswer(answer);

        return new ResponseEntity<>(
                mapper.answerToAnswerSimpleResponseDto(createdAnswer, AnswerSimpleResponseMessages.ANSWER_MESSAGE_POST),
                HttpStatus.CREATED);
    }

    @PostMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}/acceptance")
    public ResponseEntity postAnswerAccepted (@PathVariable("articleId") @Positive Long articleId,
                                              @PathVariable("answerId") @Positive Long answerId) {
        Answer acceptedAnswer = answerService.acceptAnswer(articleId, answerId);

        return new ResponseEntity<>(
                mapper.answerToAnswerAcceptanceResponseDto(acceptedAnswer, AnswerSimpleResponseMessages.ANSWER_MESSAGE_ACCEPTED),
                HttpStatus.CREATED);
    }

    @PatchMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable("articleId") @Positive Long articleId,
                                      @PathVariable("answerId") @Positive Long answerId,
                                      @Valid @RequestBody AnswerDto.Patch answerDtoPatch) {
        answerDtoPatch.setArticleId(articleId);
        answerDtoPatch.setAnswerId(answerId);
        Answer answer = mapper.answerPatchDtoToAnswer(answerDtoPatch, memberService, articleService);
        Answer updatedAnswer = answerService.updateAnswer(answer);

        if (answerDtoPatch.getAnswerId() != answerId) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
        }

        return new ResponseEntity<>(
                mapper.answerToAnswerSimpleResponseDto(updatedAnswer, AnswerSimpleResponseMessages.ANSWER_MESSAGE_PATCH),
                HttpStatus.OK);
    }

    @GetMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}")
    public ResponseEntity getAnswer(@PathVariable("articleId") @Positive Long articleId,
                                    @PathVariable("answerId") @Positive Long answerId) {

        Answer answer = answerService.findVerifiedAnswer(answerId);

        return new ResponseEntity<>(
                mapper.answerToAnswerResponseDto(answer),
                HttpStatus.OK);
    }

    @GetMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_ALL_MAPPING_URL_DETAIL)
    public ResponseEntity getAllAnswers(@PathVariable("articleId") @Positive Long articleId,
                                        @RequestParam("page") @Positive Integer page,
                                        @RequestParam("size") @Positive Integer size) {
        Page<Answer> pageAnswers = answerService.findAnswers(articleId, page - 1, size);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(
                mapper.answersToAnswerResponseDto(articleId, answers),
                HttpStatus.OK);
    }

    @DeleteMapping(ANSWER_DEFAULT_URL + "/{articleId}" + ANSWER_DEFAULT_URL_DETAIL + "/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("articleId") @Positive Long articleId,
                                       @PathVariable("answerId") @Positive Long answerId) {

        Answer deletedAnswer = answerService.deleteAnswer(articleId, answerId);

        return new ResponseEntity<>(
                mapper.answerToAnswerSimpleResponseDto(deletedAnswer, AnswerSimpleResponseMessages.ANSWER_MESSAGE_DELETE),
                HttpStatus.NO_CONTENT
        );
    }
}
