package com.homunculus.preproject.answer.mapper;

import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.dto.AnswerSimpleResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import lombok.Getter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    default AnswerResponseDto answersToAnswerResponseDto(List<Answer> answers) {
        AnswerResponseDto result = new AnswerResponseDto();
        result.setMessage("답변글 조회를 완료했습니다.");
        result.setMessageCount(answers.size());
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
