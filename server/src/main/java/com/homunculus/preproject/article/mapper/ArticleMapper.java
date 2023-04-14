package com.homunculus.preproject.article.mapper;

import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDto;
import com.homunculus.preproject.article.entity.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article articlePostDtoToArticle(ArticleDto.Post articleDtoPost);
    Article articlePatchDtoToArticle(ArticleDto.Patch articleDtoPatch);
    ArticleResponseDto articleToArticleResponseDto(Article article);
    List<ArticleResponseDto> articlesToArticleResponseDtos(List<Article> articles);
}
