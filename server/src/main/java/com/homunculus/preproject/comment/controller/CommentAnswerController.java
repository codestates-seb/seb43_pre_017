package com.homunculus.preproject.comment.controller;


import com.homunculus.preproject.comment.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.entity.CommentAnswer;
import com.homunculus.preproject.comment.mapper.CommentAnswerMapper;
import com.homunculus.preproject.comment.service.CommentAnswerService;
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
public class CommentAnswerController {
    private static final String COMMENT_DEFAULT_URL = "/api/answer";
    private static final String COMMENT_DEFAULT_URL_DETAIL = "/comment";



    private final CommentAnswerService commentAnswerService;
    private final CommentAnswerMapper mapper;

    @PostMapping(COMMENT_DEFAULT_URL + "/{answerId}" + COMMENT_DEFAULT_URL_DETAIL)
            // ("/answer/{answerId}/comment")
    public ResponseEntity postCommentAnswer(@Valid @RequestBody CommentAnswerDto.Post commentAnswerDtoPost,
                                      @PathVariable("answerId") @Positive Long answerId) {
        CommentAnswer commentAnswer = commentAnswerService.createCommentAnswer(mapper.commentAnswerPostDtoToCommentAnswer(commentAnswerDtoPost), answerId);

        CommentAnswerResponseDto responseDto = mapper.commentAnswerToCommentAnswerResponseDto(commentAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // todo : 미연님 !!!!
    @PatchMapping(COMMENT_DEFAULT_URL + "/{answerId}" + COMMENT_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity patchCommentAnswer(@Valid @RequestBody CommentAnswerDto.Patch commentAnswerDtoPatch,
                                       @PathVariable("answerId") @Positive Long answerId,
                                       @PathVariable("commentId") @Positive Long commentId) {
        commentAnswerDtoPatch.setCommentId(commentId);
        CommentAnswer commentAnswer = mapper.commentAnswerPatchDtoToCommentAnswer(commentAnswerDtoPatch);
        CommentAnswer updatedCommentAnswer = commentAnswerService.updateCommentAnswer(commentAnswer, answerId);

        CommentAnswerResponseDto responseDto = mapper.commentAnswerToCommentAnswerResponseDto(updatedCommentAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
