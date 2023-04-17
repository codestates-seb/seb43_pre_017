package com.homunculus.preproject.comment.mapper;

import com.homunculus.preproject.comment.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.entity.CommentAnswer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentAnswerMapper {
    CommentAnswer commentAnswerPostDtoToCommentAnswer(CommentAnswerDto.Post commentDtoPost);
    CommentAnswer commentAnswerPatchDtoToCommentAnswer(CommentAnswerDto.Patch commentDtoPatch);
    CommentAnswerResponseDto commentAnswerToCommentAnswerResponseDto(CommentAnswer comment);
    List<CommentAnswerResponseDto> commentsAnswerToCommentAnswerResponseDtos(List<CommentAnswer> comments);
}
