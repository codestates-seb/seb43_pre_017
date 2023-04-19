package com.homunculus.preproject.answer.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
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

import java.util.List;

import static com.homunculus.preproject.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.homunculus.preproject.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        final Long articleId = 1L;

        AnswerDto.Post post = new AnswerDto.Post();
        post.setContent(postContent);
        String content = gson.toJson(post);
        given(mapper.answerPostDtoToAnswer(any())).willReturn(new Answer());

        AnswerResponseDto responseDto = new AnswerResponseDto();
        responseDto.setMessage(postContent);
        given(answerService.createAnswer(any(), anyLong())).willReturn(new Answer());

        // when
        when(mapper.answerToAnswerResponseDto(any())).thenReturn(responseDto);

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
                .andExpect(header().string("Authorization", is(startsWith("bearer "))))     // todo : Security 적용 시 토큰 추가해야함
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
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
    void patchAnswer() {
    }

    @Test
    void getAllAnswers() {
    }

    @Test
    void deleteAnswer() {
    }
}