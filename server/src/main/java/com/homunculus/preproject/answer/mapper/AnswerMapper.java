package com.homunculus.preproject.answer.mapper;

import com.homunculus.preproject.answer.dto.AnswerAcceptanceResponseDto;
import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.dto.AnswerSimpleResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.service.ArticleService;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.service.MemberService;
import com.homunculus.preproject.response.details.AnswerResponseDetails;
import org.mapstruct.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto, MemberService memberService, ArticleService articleService) {
        Answer result = new Answer();
        result.setContent(answerPostDto.getContent());

        Article resultArticle = new Article();
        resultArticle.setArticleId(answerPostDto.getArticleId());
        Article article = articleService.findVerifiedArticle(resultArticle.getArticleId());
        result.setArticle(article);

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Member member = memberService.findVerifiedMemberByEmail(email);
        result.setMember(member);

        return result;
    }

    default Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto, MemberService memberService, ArticleService articleService) {
        Answer result = new Answer();
        result.setAnswerId(answerPatchDto.getAnswerId());
        result.setContent(answerPatchDto.getContent());

        Article resultArticle = new Article();
        resultArticle.setArticleId(answerPatchDto.getArticleId());
        Article article = articleService.findVerifiedArticle(resultArticle.getArticleId());
        result.setArticle(article);

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Member member = memberService.findVerifiedMemberByEmail(email);
        result.setMember(member);

        return result;
    };

    AnswerResponseDto answerToAnswerResponseDto(Answer answer);

    default AnswerResponseDto answersToAnswerResponseDto(Long articleId, List<Answer> answers) {
        AnswerResponseDto result = new AnswerResponseDto();
        result.setMessage("답변글 조회를 완료했습니다.");
        result.setMessageCount(answers.size());
        result.setArticleId(articleId);

        List<AnswerResponseDto.Answers> resultAnswers = new ArrayList<>();
        {
            for(Answer src : answers) {
                AnswerResponseDto.Answers answer = new AnswerResponseDto.Answers();
                answer.setId(src.getAnswerId());
                answer.setContent(src.getContent());
                answer.setEvaluationScore(src.getEvaluationScore());
                answer.setIsAccepted(src.getIsAccepted());

                AnswerResponseDto.Answers.Member member = new AnswerResponseDto.Answers.Member();
                member.setId(src.getMember().getMemberId());
                member.setName(src.getMember().getName());
                answer.setMember(member);

                AnswerResponseDto.Answers.Count count = new AnswerResponseDto.Answers.Count();
                count.setComments(src.getCommentAnswers().size());
                answer.setCount(count);

                answer.setCreatedAt(src.getCreatedAt());
                answer.setUpdatedAt(src.getUpdatedAt());

                resultAnswers.add(answer);
            }
        }
        result.setAnswers(resultAnswers);

        return result;
    }

    default AnswerSimpleResponseDto answerToAnswerSimpleResponseDto(Answer answer, AnswerSimpleResponseMessages answerSimpleResponseMessages) {
        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(answerSimpleResponseMessages.getMessage());
        responseDto.setArticleId(answer.getArticle().getArticleId());
        responseDto.setAnswerId(answer.getAnswerId());

        return responseDto;
    }

    default AnswerAcceptanceResponseDto answerToAnswerAcceptanceResponseDto(Answer answer, AnswerSimpleResponseMessages answerAcceptanceResponseDto) {
        AnswerAcceptanceResponseDto responseDto = new AnswerAcceptanceResponseDto();
        responseDto.setMessage(answerAcceptanceResponseDto.getMessage());
        responseDto.setArticleId(answer.getArticle().getArticleId());
        responseDto.setAnswerId(answer.getAnswerId());
        responseDto.setIsAccepted(answer.getIsAccepted());

        return responseDto;
    }

}
