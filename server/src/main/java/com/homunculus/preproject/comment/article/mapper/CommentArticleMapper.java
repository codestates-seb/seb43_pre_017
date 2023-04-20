package com.homunculus.preproject.comment.article.mapper;

import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentArticleMapper {
    CommentArticle commentArticlePostDtoToCommentArticle(CommentArticleDto.Post commentDtoPost);
    CommentArticle commentArticlePatchDtoToCommentArticle(CommentArticleDto.Patch commentDtoPatch);
    default CommentArticleResponseDto commentArticlesToCommentArticleResponseDto(List<CommentArticle> commentArticles) {

        return null;
    }
}
