package com.homunculus.preproject.comment.mapper;

import com.homunculus.preproject.comment.dto.CommentDto;
import com.homunculus.preproject.comment.dto.CommentResponseDto;
import com.homunculus.preproject.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostDtoToComment(CommentDto.Post commentDtoPost);
    Comment commentPatchDtoToComment(CommentDto.Patch commentDtoPatch);
    CommentResponseDto commentToCommentResponseDto(Comment comment);
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
