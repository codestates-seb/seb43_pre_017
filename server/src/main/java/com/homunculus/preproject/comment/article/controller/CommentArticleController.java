package com.homunculus.preproject.comment.article.controller;


import com.homunculus.preproject.article.service.ArticleService;
import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleSimpleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.comment.article.mapper.CommentArticleMapper;
import com.homunculus.preproject.comment.article.mapper.CommentArticleSimpleResponseMessages;
import com.homunculus.preproject.comment.article.service.CommentArticleService;
import com.homunculus.preproject.member.service.MemberService;
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
public class CommentArticleController {
    private static final String COMMENT_ARTICLE_DEFAULT_URL = "/api/article";
    private static final String COMMENT_ARTICLE_DEFAULT_URL_DETAIL = "/comment";
    private static final String COMMENT_ARTICLE_ALL_MAPPING_URL = "/comments";

    private final CommentArticleService commentArticleService;
    private final MemberService memberService;
    private final ArticleService articleService;
    private final CommentArticleMapper mapper;

    @PostMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL)
    public ResponseEntity postCommentArticle(@Valid @RequestBody CommentArticleDto.Post commentArticleDtoPost,
                                             @PathVariable("articleId") @Positive Long articleId) {
        commentArticleDtoPost.setArticleId(articleId);
        CommentArticle createdCommentArticle = commentArticleService.createCommentArticle(mapper.commentArticlePostDtoToCommentArticle(commentArticleDtoPost, memberService, articleService));

        return new ResponseEntity<>(
                mapper.commentArticleToCommentArticleSimpleResponseDto(createdCommentArticle,
                        CommentArticleSimpleResponseMessages.COMMENT_ARTICLE_MESSAGE_POST),
                HttpStatus.CREATED);
    }

    @PatchMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity patchCommentArticle(@Valid @RequestBody CommentArticleDto.Patch commentArticleDtoPatch,
                                              @PathVariable("articleId") @Positive Long articleId,
                                              @PathVariable("commentId") @Positive Long commentId) {
        commentArticleDtoPatch.setCommentId(commentId);
        commentArticleDtoPatch.setArticleId(articleId);
        CommentArticle commentArticle = mapper.commentArticlePatchDtoToCommentArticle(commentArticleDtoPatch, memberService, articleService);
        CommentArticle updatedCommentArticle = commentArticleService.updateCommentArticle(commentArticle);

        return new ResponseEntity<>(
                mapper.commentArticleToCommentArticleSimpleResponseDto(updatedCommentArticle,
                        CommentArticleSimpleResponseMessages.COMMENT_ARTICLE_MESSAGE_PATCH),
                HttpStatus.OK);
    }

    @GetMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_ALL_MAPPING_URL)
    public ResponseEntity getAllCommentArticles(@PathVariable("articleId") @Positive Long articleId,
                                                @RequestParam("page") @Positive Integer page,
                                                @RequestParam("size") @Positive Integer size) {
        Page<CommentArticle> pageCommentArticles = commentArticleService.findCommentArticles(articleId, page - 1, size);
        List<CommentArticle> commentArticles = pageCommentArticles.getContent();

        return new ResponseEntity<>(
                mapper.commentArticlesToCommentArticleResponseDto(articleId, commentArticles),
                HttpStatus.OK);
    }

    @DeleteMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity deleteCommentArticle(@PathVariable("articleId") @Positive Long articleId,
                                              @PathVariable("commentId") @Positive Long commentId) {
        CommentArticle deletedCommentArticle = commentArticleService.deleteCommentArticle(articleId, commentId);

        return new ResponseEntity<>(
                mapper.commentArticleToCommentArticleSimpleResponseDto(deletedCommentArticle,
                        CommentArticleSimpleResponseMessages.COMMENT_ARTICLE_MESSAGE_DELETE),
                HttpStatus.NO_CONTENT);
    }
}
