package com.homunculus.preproject.article.mapper;

import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDetailsDto;
import com.homunculus.preproject.article.dto.ArticleResponseDto;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.response.details.ArticleResponseDetails;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    Article articlePostDtoToArticle(ArticleDto.Post articleDtoPost);
    Article articlePatchDtoToArticle(ArticleDto.Patch articleDtoPatch);
    default ArticleResponseDetailsDto articleToArticleResponseDetailsDto(Article article) {


        return null;
    }
    default ArticleResponseDto articlesToArticleResponseDto(List<Article> articles) {
        ArticleResponseDto result = new ArticleResponseDto();

        //result.setMessage();  // 컨트롤러에서 조회 메세지를 입력했으니 여기에서는 패스
        List<ArticleResponseDto.Articles> resultArticles = new ArrayList<>();
        {
            for(Article src : articles) {
                ArticleResponseDto.Articles article = new ArticleResponseDto.Articles();
                article.setId(src.getArticleId());
                article.setTitle(src.getTitle());
                article.setContent(src.getContent());

                // todo : 추천 점수
                //article.setEvaluationScore(src.getEvaluationScore());
                article.setCreatedAt(src.getCreatedAt());
                article.setUpdatedAt(src.getUpdatedAt());

                ArticleResponseDto.Member member = new ArticleResponseDto.Member();
                member.setId(src.getMember().getMemberId());
                member.setName(src.getMember().getName());
                article.setMember(member);

                // todo : count 구현
                ArticleResponseDto.Articles.Count count = new ArticleResponseDto.Articles.Count();
//                count.setComments(src.getCommentArticleCount());
//                count.setAnswers(src.getAnswerCount());
                article.setCount(count);

                resultArticles.add(article);
            }
        }

        return result;
    }
}
