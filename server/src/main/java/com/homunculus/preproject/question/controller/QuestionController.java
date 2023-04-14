package com.homunculus.preproject.question.controller;

import com.homunculus.preproject.dto.MultiResponseDto;
import com.homunculus.preproject.question.dto.QuestionDto;
import com.homunculus.preproject.question.dto.QuestionResponseDto;
import com.homunculus.preproject.question.entity.Question;
import com.homunculus.preproject.question.mapper.QuestionMapper;
import com.homunculus.preproject.question.service.QuestionService;
import com.homunculus.preproject.user.dto.UserDto;
import com.homunculus.preproject.user.dto.UserResponseDto;
import com.homunculus.preproject.user.entity.User;
import com.homunculus.preproject.user.mapper.UserMapper;
import com.homunculus.preproject.user.service.UserService;
import com.homunculus.preproject.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class QuestionController {
    private static final String QUESTION_DEFAULT_URL = "/api/question";
    private static final String QUESTION_ALL_MAPPING_URL = "/api/questions";

    private final QuestionService questionService;
    private final QuestionMapper mapper;

    @PostMapping(QUESTION_DEFAULT_URL)
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionDtoPost) {
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionDtoPost));

        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PatchMapping(QUESTION_DEFAULT_URL + "/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive Long questionId,
                                        @Valid @RequestBody QuestionDto.Patch questionDtoPatch) {
        questionDtoPatch.setQuestionId(questionId);
        Question question = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionDtoPatch));

        QuestionResponseDto responseDto = mapper.questionToQuestionResponseDto(question);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(QUESTION_DEFAULT_URL + "/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive Long questionId) {
        Question question = questionService.findQuestion(questionId);

        QuestionResponseDto responseDto = mapper.questionToQuestionResponseDto(question);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(QUESTION_ALL_MAPPING_URL)
    public ResponseEntity getQuestion(@RequestParam @Positive int page,
                                      @RequestParam @Positive int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions), pageQuestions),
                HttpStatus.OK);
    }

    @DeleteMapping(QUESTION_DEFAULT_URL + "/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive Long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
