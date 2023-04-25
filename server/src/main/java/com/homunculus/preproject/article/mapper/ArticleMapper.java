package com.homunculus.preproject.article.mapper;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDetailsDto;
import com.homunculus.preproject.article.dto.ArticleResponseDto;
import com.homunculus.preproject.article.dto.ArticleSimpleResponseDto;
import com.homunculus.preproject.article.entity.Article;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    Article articlePostDtoToArticle(ArticleDto.Post articleDtoPost);
    Article articlePatchDtoToArticle(ArticleDto.Patch articleDtoPatch);

    default ArticleResponseDetailsDto articleToArticleResponseDetailsDto(Article article) {
        ArticleResponseDetailsDto result = new ArticleResponseDetailsDto();
        result.setMessage("질문글 조회를 완료했습니다.");

        ArticleResponseDetailsDto.Article resultArticle = new ArticleResponseDetailsDto.Article();
        resultArticle.setId(article.getArticleId());
        resultArticle.setTitle(article.getTitle());
        resultArticle.setContent(article.getContent());
        // resultArticle.setEvaluationScore(); todo:추천점수
        resultArticle.setCreatedAt(article.getCreatedAt());
        resultArticle.setUpdatedAt(article.getUpdatedAt());
        result.setArticle(resultArticle);

        ArticleResponseDetailsDto.Member resultMember = new ArticleResponseDetailsDto.Member();
        resultMember.setId(article.getMember().getMemberId());
        resultMember.setName(article.getMember().getName());
        result.setMember(resultMember);

        ArticleResponseDetailsDto.Count count = new ArticleResponseDetailsDto.Count();
        count.setComment(article.getCommentArticles().size());
        count.setAnswer(article.getAnswers().size());
        result.setCount(count);

        return result;
    }

    default ArticleResponseDto articlesToArticleResponseDto(List<Article> articles) {
        ArticleResponseDto result = new ArticleResponseDto();

        result.setMessage("질문글 조회를 완료했습니다.");
        result.setMessageCount(articles.size());
        
        List<ArticleResponseDto.Articles> resultArticles = new ArrayList<>();
        {
            for(Article src : articles) {
                ArticleResponseDto.Articles article = new ArticleResponseDto.Articles();
                article.setId(src.getArticleId());
                article.setTitle(src.getTitle());
                article.setContent(src.getContent());
                article.setEvaluationScore(src.getEvaluationScore());

                article.setCreatedAt(src.getCreatedAt());
                article.setUpdatedAt(src.getUpdatedAt());

                ArticleResponseDto.Member member = new ArticleResponseDto.Member();
                member.setId(src.getMember().getMemberId());
                member.setName(src.getMember().getName());
                article.setMember(member);

                ArticleResponseDto.Articles.Count count = new ArticleResponseDto.Articles.Count();
                count.setComments(src.getCommentArticles().size());
                count.setAnswers(src.getAnswers().size());
                article.setCount(count);

                resultArticles.add(article);
            }
        }
        result.setArticles(resultArticles);

        return result;
    }

    default ArticleSimpleResponseDto articleToArticleSimpleResponseDto(Article article,
                                                                       ArticleSimpleResponseMessages articleSimpleResponseMessages) {
        ArticleSimpleResponseDto responseDto = new ArticleSimpleResponseDto();
        responseDto.setMessage(articleSimpleResponseMessages.getMessage());
        responseDto.setArticleId(article.getArticleId());

        return responseDto;
    }
}
