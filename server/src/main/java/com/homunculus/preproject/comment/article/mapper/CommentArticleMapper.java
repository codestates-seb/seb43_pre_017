package com.homunculus.preproject.comment.article.mapper;

import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerSimpleResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerSimpleResponseMessages;
import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleSimpleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentArticleMapper {
    @Mapping(source = "articleId", target = "article.articleId")
    CommentArticle commentArticlePostDtoToCommentArticle(CommentArticleDto.Post commentDtoPost);

    @Mapping(source = "articleId", target = "article.articleId")
    @Mapping(source = "commentId", target = "commentArticleId")
    CommentArticle commentArticlePatchDtoToCommentArticle(CommentArticleDto.Patch commentDtoPatch);

    default CommentArticleResponseDto commentArticlesToCommentArticleResponseDto(Long articleId, List<CommentArticle> commentArticles) {
        CommentArticleResponseDto result = new CommentArticleResponseDto();
        result.setMessage("댓글들 조회를 완료했습니다.");
        result.setMessageCount(commentArticles.size());
        result.setArticleId(articleId);

        List<CommentArticleResponseDto.Comments> resultComments = new ArrayList<>();
        {
            for(CommentArticle src : commentArticles) {
                CommentArticleResponseDto.Comments comment = new CommentArticleResponseDto.Comments();
                comment.setId(src.getCommentArticleId());
                comment.setContent(src.getContent());

                CommentArticleResponseDto.Comments.Member member = new CommentArticleResponseDto.Comments.Member();
                member.setId(src.getMember().getMemberId());
                member.setName(src.getMember().getName());
                comment.setMember(member);

                comment.setCreatedAt(src.getCreatedAt());
                comment.setUpdatedAt(src.getUpdatedAt());
                resultComments.add(comment);
            }
        }
        result.setComments(resultComments);

        return result;
    }

    default CommentArticleSimpleResponseDto commentArticleToCommentArticleSimpleResponseDto(CommentArticle commentArticle,
                                                                                            CommentArticleSimpleResponseMessages commentArticleSimpleResponseMessages) {
        CommentArticleSimpleResponseDto responseDto = new CommentArticleSimpleResponseDto();
        responseDto.setMessage(commentArticleSimpleResponseMessages.getMessage());
        responseDto.setArticleId(commentArticle.getArticle().getArticleId());
        responseDto.setCommentId(commentArticle.getCommentArticleId());

        return responseDto;
    }
}
