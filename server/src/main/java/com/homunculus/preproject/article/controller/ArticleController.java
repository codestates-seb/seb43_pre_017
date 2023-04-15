package com.homunculus.preproject.article.controller;

import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDto;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.mapper.ArticleMapper;
import com.homunculus.preproject.article.service.ArticleService;
import com.homunculus.preproject.dto.MultiResponseDto;
import com.homunculus.preproject.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ArticleController {
    private static final String ARTICLE_DEFAULT_URL = "/api/article";
    private static final String ARTICLE_ALL_MAPPING_URL = "/api/articles";

    private final ArticleService articleService;
    private final ArticleMapper mapper;

    @PostMapping(ARTICLE_DEFAULT_URL)
    public ResponseEntity postArticle(@Valid @RequestBody ArticleDto.Post articleDtoPost) {
        Article article = articleService.createArticle(mapper.articlePostDtoToArticle(articleDtoPost));

        ArticleResponseDto responseDto = mapper.articleToArticleResponseDto(article);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(ARTICLE_DEFAULT_URL + "/{articleId}")
    public ResponseEntity patchArticle(@PathVariable("articleId") @Positive Long articleId,
                                       @Valid @RequestBody ArticleDto.Patch articleDtoPatch) {
        articleDtoPatch.setArticleId(articleId);
        Article article = articleService.updateArticle(mapper.articlePatchDtoToArticle(articleDtoPatch));

        ArticleResponseDto responseDto = mapper.articleToArticleResponseDto(article);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(ARTICLE_DEFAULT_URL + "/{article-id}")
    public ResponseEntity getArticle(@PathVariable("article-id") @Positive Long articleId) {
        Article article = articleService.findArticle(articleId);

        ArticleResponseDto responseDto = mapper.articleToArticleResponseDto(article);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(ARTICLE_ALL_MAPPING_URL)
    public ResponseEntity getArticle(@RequestParam @Positive int page,
                                     @RequestParam @Positive int size) {
        Page<Article> pageArticles = articleService.findArticles(page - 1, size);
        List<Article> articles = pageArticles.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.articlesToArticleResponseDtos(articles), pageArticles),
                HttpStatus.OK);
    }

    @DeleteMapping(ARTICLE_DEFAULT_URL + "/{article-id}")
    public ResponseEntity deleteArticle(@PathVariable("article-id") @Positive Long articleId) {
        articleService.deleteArticle(articleId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
