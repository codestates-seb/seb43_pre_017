package com.homunculus.preproject.question.mapper;

import com.homunculus.preproject.question.dto.QuestionDto;
import com.homunculus.preproject.question.dto.QuestionResponseDto;
import com.homunculus.preproject.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post questionDtoPost);
    Question questionPatchDtoToQuestion(QuestionDto.Patch questionDtoPatch);
    QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions);
}
