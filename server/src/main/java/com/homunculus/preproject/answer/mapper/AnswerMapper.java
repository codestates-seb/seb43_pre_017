package com.homunculus.preproject.answer.mapper;

import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.dto.AnswerSimpleResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.member.entity.Member;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto) {
        Answer result = new Answer();
        result.setContent(answerPostDto.getContent());

        Article resultArticle = new Article();
        resultArticle.setArticleId(answerPostDto.getArticleId());
        result.setArticle(resultArticle);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername();

        Member member = new Member();
        member.setEmail(email);
        result.setMember(member);

        return result;
    }

    default Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto) {
        Answer result = new Answer();
        result.setAnswerId(answerPatchDto.getAnswerId());
        result.setContent(answerPatchDto.getContent());

        Article resultArticle = new Article();
        resultArticle.setArticleId(answerPatchDto.getArticleId());
        result.setArticle(resultArticle);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername();

        Member member = new Member();
        member.setEmail(email);
        result.setMember(member);

        return result;
    };
    default AnswerResponseDto answersToAnswerResponseDto(Long articleId, List<Answer> answers) {
        AnswerResponseDto result = new AnswerResponseDto();
        result.setMessage("답변글 조회를 완료했습니다.");
        result.setMessageCount(answers.size());
        result.setArticleId(articleId);
        //result.setEvaluationScore();  // todo : 추천점수 계산해야함

        List<AnswerResponseDto.Answers> resultAnswers = new ArrayList<>();
        {
            for(Answer src : answers) {
                AnswerResponseDto.Answers answer = new AnswerResponseDto.Answers();
                answer.setId(src.getAnswerId());
                answer.setContent(src.getContent());

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

}
