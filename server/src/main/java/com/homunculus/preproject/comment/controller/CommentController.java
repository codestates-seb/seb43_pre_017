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
    private static final String COMMENT_DEFAULT_URL = "/api/article";
    private static final String COMMENT_DEFAULT_URL_DETAIL = "/comment";
    private static final String COMMENT_DEFAULT_URL_2 = "/api/answer";



    private final CommentService commentService;
    private final CommentMapper mapper;

    @PostMapping(COMMENT_DEFAULT_URL_2 + "{answerId}" + COMMENT_DEFAULT_URL_DETAIL)
            // ("/answer/{answerId}/comment")
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post commentDtoPost,
                                      @PathVariable("answerId") @Positive Long answerId) {
        Comment comment = commentService.createComment(mapper.commentPostDtoToComment(commentDtoPost), answerId);

        CommentResponseDto responseDto = mapper.commentToCommentResponseDto(comment);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // todo : 미연님 !!!!
    @PatchMapping(COMMENT_DEFAULT_URL_2 + "{answerId}" + COMMENT_DEFAULT_URL_DETAIL + "{commentId")
    public ResponseEntity patchComment(@Valid @RequestBody CommentDto.Patch commentDtoPatch,
                                       @PathVariable("answerId") @Positive Long answerId,
                                       @PathVariable("commentId") @Positive Long commentId) {
        // fixme: Patch 작업중
        //commentDtoPatch.
        return null;
    }

}
