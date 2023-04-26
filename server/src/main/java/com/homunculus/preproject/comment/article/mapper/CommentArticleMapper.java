package com.homunculus.preproject.comment.article.mapper;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.service.ArticleService;
import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleSimpleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.service.MemberService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentArticleMapper {
    default CommentArticle commentArticlePostDtoToCommentArticle(CommentArticleDto.Post commentDtoPost, MemberService memberService, ArticleService articleService) {
        CommentArticle result = new CommentArticle();
        result.setContent(commentDtoPost.getContent());

        Article resultArticle = new Article();
        resultArticle.setArticleId(commentDtoPost.getArticleId());
        Article article = articleService.findVerifiedArticle(resultArticle.getArticleId());
        result.setArticle(article);

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Member member = memberService.findVerifiedMemberByEmail(email);
        result.setMember(member);

        return result;
    }

    @Mapping(source = "articleId", target = "article.articleId")
    @Mapping(source = "commentId", target = "commentArticleId")
    default CommentArticle commentArticlePatchDtoToCommentArticle(CommentArticleDto.Patch commentDtoPatch, MemberService memberService, ArticleService articleService) {
        CommentArticle result = new CommentArticle();
        result.setCommentArticleId(commentDtoPatch.getCommentId());
        result.setContent(commentDtoPatch.getContent());

        Article resultArticle = new Article();
        resultArticle.setArticleId(commentDtoPatch.getArticleId());
        Article article = articleService.findVerifiedArticle(resultArticle.getArticleId());
        result.setArticle(article);

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Member member = memberService.findVerifiedMemberByEmail(email);
        result.setMember(member);;

        return result;
    }

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
