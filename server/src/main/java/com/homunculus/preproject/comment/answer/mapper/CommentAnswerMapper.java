package com.homunculus.preproject.comment.answer.mapper;

import com.homunculus.preproject.comment.answer.controller.CommentAnswerController;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerSimpleResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentAnswerMapper {
    CommentAnswer commentAnswerPostDtoToCommentAnswer(CommentAnswerDto.Post commentDtoPost);
    CommentAnswer commentAnswerPatchDtoToCommentAnswer(CommentAnswerDto.Patch commentDtoPatch);
    default CommentAnswerResponseDto commentAnswersToCommentAnswerResponseDto(List<CommentAnswer> commentAnswers) {
        CommentAnswerResponseDto result = new CommentAnswerResponseDto();
        result.setMessage("댓글들 조회를 완료했습니다.");
        result.setMessageCount(commentAnswers.size());

        return result;
    }

    default CommentAnswerSimpleResponseDto commentAnswerToCommentAnswerSimpleResponseDto(CommentAnswer commentAnswer,
                                                                                         CommentAnswerSimpleResponseMessages commentAnswerSimpleResponseMessages) {
        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
        responseDto.setMessage(commentAnswerSimpleResponseMessages.getMessage());
        responseDto.setAnswerId(commentAnswer.getAnswer().getAnswerId());
        responseDto.setCommentId(commentAnswer.getCommentAnswerId());

        return responseDto;
    }

}
