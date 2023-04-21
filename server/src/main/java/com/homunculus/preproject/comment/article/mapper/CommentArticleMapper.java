package com.homunculus.preproject.comment.article.mapper;

import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerSimpleResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerSimpleResponseMessages;
import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleSimpleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentArticleMapper {
    CommentArticle commentArticlePostDtoToCommentArticle(CommentArticleDto.Post commentDtoPost);
    CommentArticle commentArticlePatchDtoToCommentArticle(CommentArticleDto.Patch commentDtoPatch);
    default CommentArticleResponseDto commentArticlesToCommentArticleResponseDto(List<CommentArticle> commentArticles) {
        CommentArticleResponseDto result = new CommentArticleResponseDto();
        result.setMessage("댓글들 조회를 완료했습니다.");
        result.setMessageCount(commentArticles.size());

        return result;
    }

    default CommentArticleSimpleResponseDto commentArticleToCommentArticleSimpleResponseDto(CommentArticle commentArticle,
                                                                                            CommentArticleSimpleResponseMessages commentArticleSimpleResponseMessages) {
        CommentArticleSimpleResponseDto responseDto = new CommentArticleSimpleResponseDto();
        responseDto.setMessage(commentArticleSimpleResponseMessages.getMessage());
        responseDto.setArticleId(commentArticle.getArticle().getArticleId());
        responseDto.setCommentId(commentArticle.getCommentId());

        return responseDto;
    }
}
