package com.homunculus.preproject.comment.article.controller;


import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleSimpleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.comment.article.mapper.CommentArticleMapper;
import com.homunculus.preproject.comment.article.service.CommentArticleService;
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

    private final CommentArticleService commentArticleService;
    private final CommentArticleMapper mapper;

    private enum CommentArticleSimpleResponseMessages {
        COMMENT_ARTICLE_MESSAGE_POST("댓글을 등록했습니다."),
        COMMENT_ARTICLE_MESSAGE_PATCH("댓글을 수정했습니다."),
        COMMENT_ARTICLE_MESSAGE_DELETE("댓글을 삭제했습니다.");

        @Getter
        private final String message;

        CommentArticleSimpleResponseMessages(String message) {
            this.message = message;
        }
    }

    public static CommentArticleSimpleResponseDto createCommentArticleSimpleResponseDto(
                  CommentArticleSimpleResponseMessages commentArticleSimpleResponseMessages) {
        CommentArticleSimpleResponseDto responseDto = new CommentArticleSimpleResponseDto();
        responseDto.setMessage(commentArticleSimpleResponseMessages.getMessage());

        return responseDto;
    }
    @PostMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL)
    public ResponseEntity postCommentArticle(@Valid @RequestBody CommentArticleDto.Post commentArticleDtoPost,
                                      @PathVariable("articleId") @Positive Long articleId) {
        commentArticleDtoPost.setArticleId(articleId);
        commentArticleService.createCommentArticle(mapper.commentArticlePostDtoToCommentArticle(commentArticleDtoPost));

        CommentArticleSimpleResponseDto responseDto = createCommentArticleSimpleResponseDto(CommentArticleSimpleResponseMessages.COMMENT_ARTICLE_MESSAGE_POST);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity patchCommentArticle(@Valid @RequestBody CommentArticleDto.Patch commentArticleDtoPatch,
                                       @PathVariable("articleId") @Positive Long articleId,
                                       @PathVariable("commentId") @Positive Long commentId) {
        commentArticleDtoPatch.setCommentId(commentId);
        commentArticleDtoPatch.setAnswerId(articleId);
        CommentArticle commentArticle = mapper.commentArticlePatchDtoToCommentArticle(commentArticleDtoPatch);
        commentArticleService.updateCommentArticle(commentArticle);

        CommentArticleSimpleResponseDto responseDto = createCommentArticleSimpleResponseDto(CommentArticleSimpleResponseMessages.COMMENT_ARTICLE_MESSAGE_PATCH);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL)
    public ResponseEntity getAllCommentArticles(@PathVariable("articleId") @Positive Long articleId,
                                        @RequestParam("page") @Positive Integer page,
                                        @RequestParam("size") @Positive Integer size) {
        Page<CommentArticle> pageCommentArticles = commentArticleService.findCommentArticles(articleId, page - 1, size);
        List<CommentArticle> commentArticles = pageCommentArticles.getContent();

        CommentArticleResponseDto responseDto = mapper.commentArticlesToCommentArticleResponseDto(commentArticles);
        responseDto.setMessage("댓글들 조회를 완료했습니다.");
        responseDto.setMessageCount(commentArticles.size());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @DeleteMapping(COMMENT_ARTICLE_DEFAULT_URL + "/{articleId}" + COMMENT_ARTICLE_DEFAULT_URL_DETAIL + "/{commentId}")
    public ResponseEntity deleteCommentArticle(@PathVariable("articleId") @Positive Long articleId,
                                              @PathVariable("commentId") @Positive Long commentId) {
        commentArticleService.deleteCommentArticle(articleId, commentId);

        CommentArticleSimpleResponseDto responseDto = createCommentArticleSimpleResponseDto(CommentArticleSimpleResponseMessages.COMMENT_ARTICLE_MESSAGE_DELETE);
        return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
    }
}
