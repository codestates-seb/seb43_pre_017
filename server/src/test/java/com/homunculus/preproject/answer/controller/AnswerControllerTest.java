package com.homunculus.preproject.answer.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper answerMapper;

    @Autowired
    private Gson gson = new Gson();

    @Test
    void postAnswer() {

    }

    @Test
    void patchAnswer() {
    }

    @Test
    void getAllAnswers() {
    }

    @Test
    void deleteAnswer() {
    }
}