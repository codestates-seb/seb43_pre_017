package com.homunculus.preproject.comment.answer.controller;


import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerSimpleResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerMapper;
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

    private final CommentAnswerService commentAnswerService;
    private final CommentAnswerMapper mapper;

    private enum CommentAnswerSimpleResponseMessages {
        COMMENT_ANSWER_MESSAGE_POST("댓글을 등록했습니다."),
        COMMENT_ANSWER_MESSAGE_PATCH("댓글을 수정했습니다."),
        COMMENT_ANSWER_MESSAGE_DELETE("댓글을 삭제했습니다.");

        @Getter
        private final String message;

        CommentAnswerSimpleResponseMessages(String message) {
            this.message = message;
        }
    }

    public static CommentAnswerSimpleResponseDto createAnswerSimpleResponseDto(
                  CommentAnswerController.CommentAnswerSimpleResponseMessages commentAnswerSimpleResponseMessages) {
        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
        responseDto.setMessage(commentAnswerSimpleResponseMessages.getMessage());

        return responseDto;
    }


    @PostMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity postCommentAnswer(@Valid @RequestBody CommentAnswerDto.Post commentAnswerDtoPost,
                                            @PathVariable("answerId") @Positive Long answerId) {
        commentAnswerDtoPost.setAnswerId(answerId);
        commentAnswerService.createCommentAnswer(mapper.commentAnswerPostDtoToCommentAnswer(commentAnswerDtoPost));

        CommentAnswerSimpleResponseDto responseDto = createAnswerSimpleResponseDto(CommentAnswerSimpleResponseMessages.COMMENT_ANSWER_MESSAGE_POST);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity patchCommentAnswer(@Valid @RequestBody CommentAnswerDto.Patch commentAnswerDtoPatch,
                                       @PathVariable("answerId") @Positive Long answerId,
                                       @PathVariable("commentId") @Positive Long commentId) {
        commentAnswerDtoPatch.setCommentId(commentId);
        commentAnswerDtoPatch.setAnswerId(answerId);
        CommentAnswer commentAnswer = mapper.commentAnswerPatchDtoToCommentAnswer(commentAnswerDtoPatch);
        commentAnswerService.updateCommentAnswer(commentAnswer);

        CommentAnswerSimpleResponseDto responseDto = createAnswerSimpleResponseDto(CommentAnswerSimpleResponseMessages.COMMENT_ANSWER_MESSAGE_PATCH);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_DEFAULT_URL_DETAIL)
    public ResponseEntity getAllCommentAnswers(@PathVariable("answerId") @Positive Long answerId,
                                        @RequestParam("page") @Positive Integer page,
                                        @RequestParam("size") @Positive Integer size) {
        Page<CommentAnswer> pageCommentAnswers = commentAnswerService.findCommentAnswers(answerId,page - 1, size);
        List<CommentAnswer> commentAnswers = pageCommentAnswers.getContent();

        CommentAnswerResponseDto responseDto = mapper.commentsAnswerToCommentAnswerResponseDto(commentAnswers);
        responseDto.setMessage("댓글 조회가 완료되었습니다.");
        responseDto.setMessageCount(commentAnswers.size());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(COMMENT_ANSWER_DEFAULT_URL + "/{answerId}" + COMMENT_ANSWER_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity deleteCommentAnswer(@PathVariable("answerId") @Positive Long answerId,
                                              @PathVariable("commentId") @Positive Long commentId) {
        commentAnswerService.deleteCommentAnswer(answerId, commentId);

        CommentAnswerSimpleResponseDto responseDto = createAnswerSimpleResponseDto(CommentAnswerSimpleResponseMessages.COMMENT_ANSWER_MESSAGE_DELETE);
        return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
    }
}
