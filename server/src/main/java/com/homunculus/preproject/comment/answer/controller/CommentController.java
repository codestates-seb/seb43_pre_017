package com.homunculus.preproject.comment.answer.controller;


import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerMapper;
import com.homunculus.preproject.comment.answer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class CommentController {

    private final CommentService commentService;
    private final CommentAnswerMapper mapper;

    @PostMapping("/answer/{answer-id}/comment")
    public ResponseEntity postComment(@Valid @RequestBody CommentAnswerDto.Post commentDtoPost,
                                      @PathVariable("answer-id") @Positive Long answerId) {
        CommentAnswer commentAnswer = commentService.createComment(mapper.commentPostDtoToComment(commentDtoPost), answerId);

        CommentAnswerResponseDto responseDto = mapper.commentToCommentResponseDto(commentAnswer);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // todo : 미연님 !!!!

}
