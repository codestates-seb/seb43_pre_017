package com.homunculus.preproject.comment.answer.mapper;

import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentAnswerMapper {
    CommentAnswer commentPostDtoToComment(CommentAnswerDto.Post commentDtoPost);
    CommentAnswer commentPatchDtoToComment(CommentAnswerDto.Patch commentDtoPatch);
    CommentAnswerResponseDto commentToCommentResponseDto(CommentAnswer commentAnswer);
    List<CommentAnswerResponseDto> commentsToCommentResponseDtos(List<CommentAnswer> commentAnswers);
}
