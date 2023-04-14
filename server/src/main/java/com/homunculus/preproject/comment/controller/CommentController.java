package com.homunculus.preproject.comment.controller;


import com.homunculus.preproject.comment.dto.CommentDto;
import com.homunculus.preproject.comment.dto.CommentResponseDto;
import com.homunculus.preproject.comment.entity.Comment;
import com.homunculus.preproject.comment.mapper.CommentMapper;
import com.homunculus.preproject.comment.service.CommentService;
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
    private final CommentMapper mapper;

    @PostMapping("/answer/{answer-id}/comment")
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post commentDtoPost,
                                      @PathVariable("answer-id") @Positive Long answerId) {
        Comment comment = commentService.createComment(mapper.commentPostDtoToComment(commentDtoPost), answerId);

        CommentResponseDto responseDto = mapper.commentToCommentResponseDto(comment);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // todo : 미연님 !!!!

}
