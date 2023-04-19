package com.homunculus.preproject.answer.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.dto.AnswerSimpleResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import com.homunculus.preproject.response.details.AnswerResponseDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static com.homunculus.preproject.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.homunculus.preproject.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper mapper;

    @Autowired
    private Gson gson = new Gson();

    @Test
    @DisplayName("Answer 등록 테스트")
    void postAnswerTest() throws Exception {
        // given
        final String postContent = "아무거나 집어넣은 내용";
        final String responseContent = "답변을 등록했습니다.";
        final Long articleId = 1L;
//        final Integer messageCount = 2;
//        final Long answerId1 = 1L;      final String answerContent1 = "무언가1";
//        final Long answerId2 = 2L;      final String answerContent2 = "무언가2";
//

        AnswerDto.Post post = new AnswerDto.Post();
        post.setContent(postContent);
        String content = gson.toJson(post);
        given(mapper.answerPostDtoToAnswer(any())).willReturn(new Answer());

        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);

//        List<AnswerResponseDetails> answers = new ArrayList<>();
//        AnswerResponseDetails answerResponseDetails1 = new AnswerResponseDetails();
//        answerResponseDetails1.setId(answerId1);
//        answerResponseDetails1.setContent(answerContent1);
//        answers.add(answerResponseDetails1);
//
//        AnswerResponseDetails answerResponseDetails2 = new AnswerResponseDetails();
//        answerResponseDetails2.setId(answerId2);
//        answerResponseDetails2.setContent(answerContent2);
//        answers.add(answerResponseDetails2);
//
//        responseDto.setAnswers(answers);
//        responseDto.setStatus(Answer.AnswerStatus.ANSWER_REGISTRY);
//        responseDto.setMessageCount(messageCount);

        given(answerService.createAnswer(any(), anyLong())).willReturn(new Answer());

        // when
        given(mapper.answerToAnswerSimpleResponseDto(any())).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        post("/api/article/{articleId}/answer",articleId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
//                .andExpect(header().string("Authorization", is(startsWith("bearer "))))     // todo : Security 적용 시 토큰 추가해야함
                .andExpect(jsonPath("$.message").value(responseContent))
//                .andExpect(jsonPath("$.messageCount").value(1))
//                .andExpect(jsonPath("$.answers[0].id").value(answerId1))
//                .andExpect(jsonPath("$.answers[0].content").value(answerContent1))
//                .andExpect(jsonPath("$.answers[1].id").value(answerId2))
//                .andExpect(jsonPath("$.answers[1].content").value(answerContent2))
//                .andExpect(jsonPath("$.status").value("등록상태"))
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("컨텐츠 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }
/*
    @Test
    void patchAnswer() {
        // given
        final String postContent = "아무거나 집어넣은 내용";
        final String responseContent = "아무거나 집어넣어서 나온 응답";
        final Long articleId = 1L;
//        final Integer messageCount = 2;
//        final Long answerId1 = 1L;      final String answerContent1 = "무언가1";
//        final Long answerId2 = 2L;      final String answerContent2 = "무언가2";
//

        AnswerDto.Post post = new AnswerDto.Post();
        post.setContent(postContent);
        String content = gson.toJson(post);
        given(mapper.answerPostDtoToAnswer(any())).willReturn(new Answer());

        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);

//        List<AnswerResponseDetails> answers = new ArrayList<>();
//        AnswerResponseDetails answerResponseDetails1 = new AnswerResponseDetails();
//        answerResponseDetails1.setId(answerId1);
//        answerResponseDetails1.setContent(answerContent1);
//        answers.add(answerResponseDetails1);
//
//        AnswerResponseDetails answerResponseDetails2 = new AnswerResponseDetails();
//        answerResponseDetails2.setId(answerId2);
//        answerResponseDetails2.setContent(answerContent2);
//        answers.add(answerResponseDetails2);
//
//        responseDto.setAnswers(answers);
//        responseDto.setStatus(Answer.AnswerStatus.ANSWER_REGISTRY);
//        responseDto.setMessageCount(messageCount);

        given(answerService.createAnswer(any(), anyLong())).willReturn(new Answer());

        // when
        given(mapper.answerToAnswerSimpleResponseDto(any())).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        post("/api/article/{articleId}/answer",articleId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
//                .andExpect(header().string("Authorization", is(startsWith("bearer "))))     // todo : Security 적용 시 토큰 추가해야함
                .andExpect(jsonPath("$.message").value(responseContent))
//                .andExpect(jsonPath("$.messageCount").value(1))
//                .andExpect(jsonPath("$.answers[0].id").value(answerId1))
//                .andExpect(jsonPath("$.answers[0].content").value(answerContent1))
//                .andExpect(jsonPath("$.answers[1].id").value(answerId2))
//                .andExpect(jsonPath("$.answers[1].content").value(answerContent2))
//                .andExpect(jsonPath("$.status").value("등록상태"))
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("컨텐츠 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }

    @Test
    void getAllAnswers() {
    }

    @Test
    @DisplayName("AnswerDelete 테스트")
    void deleteAnswer() {
        // given
        final Long articleId = 1L;
        final String responseContent = "지워야하는 응답";

        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);

        doNothing().when(answerService).deleteAnswer(anyLong(), anyLong());

        // when
        given(mapper.answerToAnswerSimpleResponseDto(any())).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        post("/api/article/{articleId}/answer",articleId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
//                .andExpect(header().string("Authorization", is(startsWith("bearer "))))     // todo : Security 적용 시 토큰 추가해야함
                .andExpect(jsonPath("$.message").value(responseContent))
//                .andExpect(jsonPath("$.messageCount").value(1))
//                .andExpect(jsonPath("$.answers[0].id").value(answerId1))
//                .andExpect(jsonPath("$.answers[0].content").value(answerContent1))
//                .andExpect(jsonPath("$.answers[1].id").value(answerId2))
//                .andExpect(jsonPath("$.answers[1].content").value(answerContent2))
//                .andExpect(jsonPath("$.status").value("등록상태"))
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("컨텐츠 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }

 */
}