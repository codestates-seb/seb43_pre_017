package com.homunculus.preproject.comment.answer.controller;


import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerSimpleResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerMapper;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerSimpleResponseMessages;
import com.homunculus.preproject.comment.answer.service.CommentAnswerService;
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
public class CommentAnswerController {
    private static final String COMMENT_ANSWER_DEFAULT_URL = "/api/answer";
    private static final String COMMENT_ANSWER_DEFAULT_URL_DETAIL = "/comment";
    private static final String COMMENT_ANSWER_ALL_MAPPING_URL = "/comments";

    private final CommentAnswerService commentAnswerService;
    private final CommentAnswerMapper mapper;

    @PostMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity postCommentAnswer(@Valid @RequestBody CommentAnswerDto.Post commentAnswerDtoPost,
                                            @PathVariable("answerId") @Positive Long answerId) {
        commentAnswerDtoPost.setAnswerId(answerId);
        CommentAnswer createdCommentAnswer = commentAnswerService.createCommentAnswer(mapper.commentAnswerPostDtoToCommentAnswer(commentAnswerDtoPost));

        return new ResponseEntity<>(
                mapper.commentAnswerToCommentAnswerSimpleResponseDto(createdCommentAnswer,
                        CommentAnswerSimpleResponseMessages.COMMENT_ANSWER_MESSAGE_POST),
                HttpStatus.CREATED);
    }

    @PatchMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity patchCommentAnswer(@Valid @RequestBody CommentAnswerDto.Patch commentAnswerDtoPatch,
                                             @PathVariable("answerId") @Positive Long answerId,
                                             @PathVariable("commentId") @Positive Long commentId) {
        commentAnswerDtoPatch.setCommentId(commentId);
        commentAnswerDtoPatch.setAnswerId(answerId);
        CommentAnswer commentAnswer = mapper.commentAnswerPatchDtoToCommentAnswer(commentAnswerDtoPatch);
        CommentAnswer updatedCommentAnswer = commentAnswerService.updateCommentAnswer(commentAnswer);

        return new ResponseEntity<>(
                mapper.commentAnswerToCommentAnswerSimpleResponseDto(updatedCommentAnswer,
                        CommentAnswerSimpleResponseMessages.COMMENT_ANSWER_MESSAGE_PATCH),
                HttpStatus.OK);
    }

    @GetMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_ALL_MAPPING_URL)
    public ResponseEntity getAllCommentAnswers(@PathVariable("answerId") @Positive Long answerId,
                                               @RequestParam("page") @Positive Integer page,
                                               @RequestParam("size") @Positive Integer size) {
        Page<CommentAnswer> pageCommentAnswers = commentAnswerService.findCommentAnswers(answerId,page - 1, size);
        List<CommentAnswer> commentAnswers = pageCommentAnswers.getContent();

        return new ResponseEntity<>(
                mapper.commentAnswersToCommentAnswerResponseDto(answerId, commentAnswers),
                HttpStatus.OK);
    }

    @DeleteMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity deleteCommentAnswer(@PathVariable("answerId") @Positive Long answerId,
                                              @PathVariable("commentId") @Positive Long commentId) {
        CommentAnswer deletedCommentAnswer = commentAnswerService.deleteCommentAnswer(answerId, commentId);

        return new ResponseEntity<>(
                mapper.commentAnswerToCommentAnswerSimpleResponseDto(deletedCommentAnswer,
                        CommentAnswerSimpleResponseMessages.COMMENT_ANSWER_MESSAGE_DELETE),
                HttpStatus.NO_CONTENT);
    }
}
