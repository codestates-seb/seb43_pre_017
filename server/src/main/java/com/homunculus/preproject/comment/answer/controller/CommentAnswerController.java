package com.homunculus.preproject.comment.answer.controller;


import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerMapper;
import com.homunculus.preproject.comment.answer.service.CommentAnswerService;
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
    private static final String COMMENT_DEFAULT_URL = "/api/answer";
    private static final String COMMENT_DEFAULT_URL_DETAIL = "/comment";



    private final CommentAnswerService commentAnswerService;
    private final CommentAnswerMapper mapper;

    @PostMapping(COMMENT_DEFAULT_URL + "/{answerId}" + COMMENT_DEFAULT_URL_DETAIL)
    public ResponseEntity postCommentAnswer(@Valid @RequestBody CommentAnswerDto.Post commentAnswerDtoPost,
                                      @PathVariable("answerId") @Positive Long answerId) {
        commentAnswerDtoPost.setAnswerId(answerId);
        CommentAnswer commentAnswer = commentAnswerService.createCommentAnswer(mapper.commentAnswerPostDtoToCommentAnswer(commentAnswerDtoPost));

        CommentAnswerResponseDto responseDto = mapper.commentAnswerToCommentAnswerResponseDto(commentAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(COMMENT_DEFAULT_URL + "/{answerId}" + COMMENT_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity patchCommentAnswer(@Valid @RequestBody CommentAnswerDto.Patch commentAnswerDtoPatch,
                                       @PathVariable("answerId") @Positive Long answerId,
                                       @PathVariable("commentId") @Positive Long commentId) {
        commentAnswerDtoPatch.setCommentId(commentId);
        commentAnswerDtoPatch.setAnswerId(answerId);
        CommentAnswer commentAnswer = mapper.commentAnswerPatchDtoToCommentAnswer(commentAnswerDtoPatch);
        CommentAnswer updatedCommentAnswer = commentAnswerService.updateCommentAnswer(commentAnswer);

        CommentAnswerResponseDto responseDto = mapper.commentAnswerToCommentAnswerResponseDto(updatedCommentAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(COMMENT_DEFAULT_URL + "/{answerId}" + COMMENT_DEFAULT_URL_DETAIL)
    public ResponseEntity getAllCommentAnswers(@PathVariable("answerId") @Positive Long answerId,
                                        @RequestParam("page") @Positive Integer page,
                                        @RequestParam("size") @Positive Integer size) {
        Page<CommentAnswer> pageCommentAnswers = commentAnswerService.findCommentAnswers(answerId,page - 1, size);
        List<CommentAnswer> commentAnswers = pageCommentAnswers.getContent();

        return new ResponseEntity<>(
                mapper.commentsAnswerToCommentAnswerResponseDtos(commentAnswers),
                HttpStatus.OK);
    }

    @DeleteMapping(COMMENT_DEFAULT_URL + "/{answerId}" + COMMENT_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity deleteCommentAnswer(@PathVariable("answerId") @Positive Long answerId,
                                              @PathVariable("commentId") @Positive Long commentId) {
        commentAnswerService.deleteCommentAnswer(answerId, commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
