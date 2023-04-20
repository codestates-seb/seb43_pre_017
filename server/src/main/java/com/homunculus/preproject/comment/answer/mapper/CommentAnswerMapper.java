package com.homunculus.preproject.comment.answer.mapper;

import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentAnswerMapper {
    CommentAnswer commentAnswerPostDtoToCommentAnswer(CommentAnswerDto.Post commentDtoPost);
    CommentAnswer commentAnswerPatchDtoToCommentAnswer(CommentAnswerDto.Patch commentDtoPatch);
    default CommentAnswerResponseDto commentsAnswerToCommentAnswerResponseDto(List<CommentAnswer> commentAnswers) {

        return null;
    }
}
