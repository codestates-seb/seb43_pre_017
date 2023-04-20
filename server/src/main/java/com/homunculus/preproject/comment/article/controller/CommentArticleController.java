package com.homunculus.preproject.comment.article.controller;


import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.comment.article.mapper.CommentArticleMapper;
import com.homunculus.preproject.comment.article.service.CommentArticleService;
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
public class CommentArticleController {
    private static final String COMMENT_ARTICLE_DEFAULT_URL = "/api/article";
    private static final String COMMENT_ARTICLE_DEFAULT_URL_DETAIL = "/comment";



    private final CommentArticleService commentArticleService;
    private final CommentArticleMapper mapper;

    @PostMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL)
    public ResponseEntity postCommentArticle(@Valid @RequestBody CommentArticleDto.Post commentArticleDtoPost,
                                      @PathVariable("articleId") @Positive Long articleId) {
        commentArticleDtoPost.setArticleId(articleId);
        CommentArticle commentArticle = commentArticleService.createCommentArticle(mapper.commentArticlePostDtoToCommentArticle(commentArticleDtoPost));

        CommentArticleResponseDto responseDto = mapper.commentArticleToCommentArticleResponseDto(commentArticle);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity patchCommentArticle(@Valid @RequestBody CommentArticleDto.Patch commentArticleDtoPatch,
                                       @PathVariable("articleId") @Positive Long articleId,
                                       @PathVariable("commentId") @Positive Long commentId) {
        commentArticleDtoPatch.setCommentId(commentId);
        commentArticleDtoPatch.setAnswerId(articleId);
        CommentArticle commentArticle = mapper.commentArticlePatchDtoToCommentArticle(commentArticleDtoPatch);
        CommentArticle updatedCommentArticle = commentArticleService.updateCommentArticle(commentArticle);

        CommentArticleResponseDto responseDto = mapper.commentArticleToCommentArticleResponseDto(updatedCommentArticle);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL)
    public ResponseEntity getAllCommentArticles(@PathVariable("articleId") @Positive Long articleId,
                                        @RequestParam("page") @Positive Integer page,
                                        @RequestParam("size") @Positive Integer size) {
        Page<CommentArticle> pageCommentArticles = commentArticleService.findCommentArticles(articleId, page - 1, size);
        List<CommentArticle> commentArticles = pageCommentArticles.getContent();


        return new ResponseEntity<>(
                mapper.commentArticlesToCommentArticleResponseDtos(commentArticles),
                HttpStatus.OK);
    }


    @DeleteMapping(COMMENT_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity deleteCommentArticle(@PathVariable("articleId") @Positive Long articleId,
                                              @PathVariable("commentId") @Positive Long commentId) {
        commentArticleService.deleteCommentArticle(articleId, commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
