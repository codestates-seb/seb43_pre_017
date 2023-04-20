package com.homunculus.preproject.article.mapper;

import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDetailsDto;
import com.homunculus.preproject.article.dto.ArticleResponseDto;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.response.details.ArticleResponseDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article articlePostDtoToArticle(ArticleDto.Post articleDtoPost);
    Article articlePatchDtoToArticle(ArticleDto.Patch articleDtoPatch);
    default ArticleResponseDetailsDto articleToArticleResponseDetailsDto(Article article) {
        return null;
    }
    default ArticleResponseDto articlesToArticleResponseDto(List<Article> articles) {
        return null;
    }
}
