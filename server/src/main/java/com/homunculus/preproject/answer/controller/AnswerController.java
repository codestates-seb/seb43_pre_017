package com.homunculus.preproject.answer.controller;

import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class AnswerController {

    private static final String ANSWER_DEFAULT_URL = "/api/answer";
    private static final String ANSWER_ALL_MAPPING_URL = "/api/answers";

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;


}
