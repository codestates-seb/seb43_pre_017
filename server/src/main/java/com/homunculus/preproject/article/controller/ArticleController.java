package com.homunculus.preproject.article.controller;

import com.homunculus.preproject.dto.MultiResponseDto;
import com.homunculus.preproject.article.dto.QuestionDto;
import com.homunculus.preproject.article.dto.QuestionResponseDto;
import com.homunculus.preproject.article.entity.Question;
import com.homunculus.preproject.article.mapper.QuestionMapper;
import com.homunculus.preproject.article.service.QuestionService;
import com.homunculus.preproject.user.dto.UserDto;
import com.homunculus.preproject.user.dto.UserResponseDto;
import com.homunculus.preproject.user.entity.User;
import com.homunculus.preproject.user.mapper.UserMapper;
import com.homunculus.preproject.user.service.UserService;
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

        URI location = UriCreator.createUri(ARTICLE_DEFAULT_URL, article.getArticleId());

        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PatchMapping(ARTICLE_DEFAULT_URL + "/{article-id}")
    public ResponseEntity patchArticle(@PathVariable("article-id") @Positive Long articleId,
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
        Page<article> pageArticles = articleService.findArticles(page - 1, size);
        List<article> articles = pageArticles.getContent();

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
