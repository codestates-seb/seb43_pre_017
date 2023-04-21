package com.homunculus.preproject.article.controller;

import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDetailsDto;
import com.homunculus.preproject.article.dto.ArticleResponseDto;
import com.homunculus.preproject.article.dto.ArticleSimpleResponseDto;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.mapper.ArticleMapper;
import com.homunculus.preproject.article.service.ArticleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ArticleController {
    private static final String ARTICLE_DEFAULT_URL = "/api/article";
    private static final String ARTICLE_ALL_MAPPING_URL = "/api/articles";

    private final ArticleService articleService;
    private final ArticleMapper mapper;

    private enum ArticleSimpleResponseMessages {
        ARTICLE_MESSAGE_POST("질문을 등록했습니다."),
        ARTICLE_MESSAGE_PATCH("질문을 수정했습니다."),
        ARTICLE_MESSAGE_DELETE("질문을 삭제했습니다.");

        @Getter
        private final String message;

        ArticleSimpleResponseMessages(String message) {
            this.message = message;
        }
    }

    public static ArticleSimpleResponseDto createArticleSimpleResponseDto(ArticleSimpleResponseMessages articleSimpleResponseMessages) {
        ArticleSimpleResponseDto responseDto = new ArticleSimpleResponseDto();
        responseDto.setMessage(articleSimpleResponseMessages.getMessage());

        return responseDto;
    }

    @PostMapping(ARTICLE_DEFAULT_URL)
    public ResponseEntity postArticle(@Valid @RequestBody ArticleDto.Post articleDtoPost) {
        Article article = articleService.createArticle(mapper.articlePostDtoToArticle(articleDtoPost));

        ArticleSimpleResponseDto responseDto = createArticleSimpleResponseDto(ArticleSimpleResponseMessages.ARTICLE_MESSAGE_POST);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(ARTICLE_DEFAULT_URL + "/{articleId}")
    public ResponseEntity patchArticle(@PathVariable("articleId") @Positive Long articleId,
                                       @Valid @RequestBody ArticleDto.Patch articleDtoPatch) {
        articleDtoPatch.setArticleId(articleId);
        Article article = articleService.updateArticle(mapper.articlePatchDtoToArticle(articleDtoPatch));

        ArticleSimpleResponseDto responseDto = createArticleSimpleResponseDto(ArticleSimpleResponseMessages.ARTICLE_MESSAGE_PATCH);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(ARTICLE_DEFAULT_URL + "/{articleId}")
    public ResponseEntity getArticle(@PathVariable("articleId") @Positive Long articleId) {
        Article article = articleService.findArticle(articleId);

        ArticleResponseDetailsDto responseDto = mapper.articleToArticleResponseDetailsDto(article);
        responseDto.setMessage("질문글 조회를 완료했습니다.");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(ARTICLE_ALL_MAPPING_URL)
    public ResponseEntity getArticle(@RequestParam @Positive int page,
                                     @RequestParam @Positive int size) {
        Page<Article> pageArticles = articleService.findArticles(page - 1, size);
        List<Article> articles = pageArticles.getContent();

        ArticleResponseDto responseDto = mapper.articlesToArticleResponseDto(articles);
        responseDto.setMessage("질문글 조회를 완료했습니다.");
        responseDto.setMessageCount(articles.size());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(ARTICLE_DEFAULT_URL + "/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable("articleId") @Positive Long articleId) {
        articleService.deleteArticle(articleId);

        ArticleSimpleResponseDto responseDto = createArticleSimpleResponseDto(ArticleSimpleResponseMessages.ARTICLE_MESSAGE_DELETE);
        return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
    }
}
