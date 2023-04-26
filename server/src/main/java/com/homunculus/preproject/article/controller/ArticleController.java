package com.homunculus.preproject.article.controller;

import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.mapper.ArticleMapper;
import com.homunculus.preproject.article.mapper.ArticleSimpleResponseMessages;
import com.homunculus.preproject.article.service.ArticleService;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
public class ArticleController {
    private static final String ARTICLE_DEFAULT_URL = "/api/article";
    private static final String ARTICLE_ALL_MAPPING_URL = "/api/articles";
    private final MemberService memberService;
    private final ArticleService articleService;
    private final ArticleMapper mapper;
    public ArticleController(MemberService memberService, ArticleService articleService, ArticleMapper mapper) {
        this.memberService = memberService;
        this.articleService = articleService;
        this.mapper = mapper;
    }

    @PostMapping(ARTICLE_DEFAULT_URL)
    public ResponseEntity postArticle(@Valid @RequestBody ArticleDto.Post articleDtoPost) {
        Article postArticle = mapper.articlePostDtoToArticle(articleDtoPost, memberService);

        Article createdArticle = articleService.createArticle(postArticle);

        return new ResponseEntity<>(
                mapper.articleToArticleSimpleResponseDto(createdArticle, ArticleSimpleResponseMessages.ARTICLE_MESSAGE_POST),
                HttpStatus.CREATED);
    }

    @PatchMapping(ARTICLE_DEFAULT_URL + "/{articleId}")
    public ResponseEntity patchArticle(@PathVariable("articleId") @Positive Long articleId,
                                       @Valid @RequestBody ArticleDto.Patch articleDtoPatch) {
        articleDtoPatch.setArticleId(articleId);
        Article updatedArticle = articleService.updateArticle(mapper.articlePatchDtoToArticle(articleDtoPatch, memberService));

        if (updatedArticle.getArticleId() != articleId) {
            throw new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND);
        }

        return new ResponseEntity<>(
                mapper.articleToArticleSimpleResponseDto(updatedArticle, ArticleSimpleResponseMessages.ARTICLE_MESSAGE_PATCH),
                HttpStatus.OK);
    }

    @GetMapping(ARTICLE_DEFAULT_URL + "/{articleId}")
    public ResponseEntity getArticle(@PathVariable("articleId") @Positive Long articleId) {
        Article article = articleService.findArticle(articleId);

        return new ResponseEntity<>(
                mapper.articleToArticleResponseDetailsDto(article),
                HttpStatus.OK);
    }

    @GetMapping(ARTICLE_ALL_MAPPING_URL)
    public ResponseEntity getArticles(@RequestParam @Positive int page,
                                     @RequestParam @Positive int size,
                                     @RequestParam String type) {
        Page<Article> pageArticles = articleService.findArticles(page - 1, size, type);
        List<Article> articles = pageArticles.getContent();

        return new ResponseEntity<>(mapper.articlesToArticleResponseDto(articles), HttpStatus.OK);
    }

    @DeleteMapping(ARTICLE_DEFAULT_URL + "/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable("articleId") @Positive Long articleId) {
        Article deletedArticle = articleService.deleteArticle(articleId);

        return new ResponseEntity<>(
                mapper.articleToArticleSimpleResponseDto(deletedArticle, ArticleSimpleResponseMessages.ARTICLE_MESSAGE_DELETE),
                HttpStatus.NO_CONTENT);
    }
}
