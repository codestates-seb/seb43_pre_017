package com.homunculus.preproject.answer.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.dto.AnswerSimpleResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.response.details.AnswerResponseDetails;
import com.homunculus.preproject.response.details.CommentAnswerResponseDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.homunculus.preproject.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.homunculus.preproject.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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

        AnswerDto.Post post = new AnswerDto.Post();
        post.setContent(postContent);
        String content = gson.toJson(post);
        post.setArticleId(articleId);
        given(mapper.answerPostDtoToAnswer(any())).willReturn(new Answer());

        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);

        given(answerService.createAnswer(any())).willReturn(new Answer());

        // when

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
    @DisplayName("Answer 수정 테스트")
    void patchAnswer() throws Exception {
        // given
        final String patchContent = "아무거나 집어넣은 내용";
        final String responseContent = "답변을 수정했습니다.";
        final Long articleId = 1L;
        final Long answerId = 1L;

        AnswerDto.Patch patch = new AnswerDto.Patch();
        patch.setContent(patchContent);
        String content = gson.toJson(patch);

        given(mapper.answerPatchDtoToAnswer(any())).willReturn(new Answer());

        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);

        given(answerService.updateAnswer(any())).willReturn(new Answer());

        // when

        ResultActions actions =
                mockMvc.perform(
                        patch("/api/article/{articleId}/answer/{answerId}", articleId, answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseContent))
                .andDo(document(
                        "patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자"),
                                parameterWithName("answerId").description("답변글 식별자")
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
    @DisplayName("Answer 조회 테스트")
    void getAllAnswers() throws Exception {
        // given
        final LocalDateTime timeStamp = LocalDateTime.now();

        final String answerMessage = "답변글 조회를 완료했습니다.";
        final String commentMessage = "코멘트 조회를 완료했습니다.";
        final Long articleId = 1L;

        final Long answerId1 = 1L;          final String answerContent1 = "무언가1";
        final Long commentAnswerId1_1 = 1L;   final String commentAnswerContent1_1 = "코멘트1-1";
        final Long commentAnswerId1_2 = 2L;   final String commentAnswerContent1_2 = "코멘트1-2";

        final Long answerId2 = 2L;          final String answerContent2 = "무언가2";
        final Long commentAnswerId2_1 = 1L;   final String commentAnswerContent2_1 = "코멘트2-1";
        final Long commentAnswerId2_2 = 2L;   final String commentAnswerContent2_2 = "코멘트2-2";

        AnswerResponseDto responseDto = new AnswerResponseDto();
        {
            responseDto.setMessage(answerMessage);
            responseDto.setArticleId(articleId);

            List<AnswerResponseDetails> answers = new ArrayList<>();
            answers.add(createDummyAnswerResponseDetails(
                    timeStamp, commentMessage, answerId1, answerContent1,
                    commentAnswerId1_1, commentAnswerContent1_1,
                    commentAnswerId1_2, commentAnswerContent1_2));
            answers.add(createDummyAnswerResponseDetails(
                    timeStamp, commentMessage, answerId2, answerContent2,
                    commentAnswerId2_1, commentAnswerContent2_1,
                    commentAnswerId2_2, commentAnswerContent2_2));
            responseDto.setMessageCount(answers.size());
            responseDto.setAnswers(answers);
            responseDto.setStatus(Answer.AnswerStatus.ANSWER_REGISTRY);
        }

        Page<Answer> answers = new PageImpl<>(List.of(new Answer(), new Answer()));
        given(answerService.findAnswers(anyLong(), anyInt(), anyInt())).willReturn(answers);

        // when
        given(mapper.answersToAnswerResponseDto(any())).willReturn(responseDto);

        String page = "1";
        String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        ResultActions actions =
                mockMvc.perform(
                        get("/api/article/{articleId}/answers", articleId)
                                .params(queryParams)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andExpect(jsonPath("$.messageCount").value(responseDto.getMessageCount()))
                .andExpect(jsonPath("$.articleId").value(responseDto.getArticleId()));

        expectAnswers(responseDto.getAnswers(), 0, actions);
        expectAnswers(responseDto.getAnswers(), 1, actions);

        actions
                .andExpect(jsonPath("$.status").value(responseDto.getStatus()))
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }
    private void expectAnswers(List<AnswerResponseDetails> answerResponseDetailsList, Integer index, ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.answers[" + index + "].id").value(answerResponseDetailsList.get(index).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].content").value(answerResponseDetailsList.get(index).getContent()))
                .andExpect(jsonPath("$.answers[" + index + "].message").value(answerResponseDetailsList.get(index).getMessage()))
                .andExpect(jsonPath("$.answers[" + index + "].messageCount").value(answerResponseDetailsList.get(index).getMessageCount()));

        actions
                .andExpect(jsonPath("$.answers[" + index + "].comments[0].id").value(answerResponseDetailsList.get(index).getComments().get(0).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[0].content").value(answerResponseDetailsList.get(index).getComments().get(0).getContent()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[0].createdAt").value(answerResponseDetailsList.get(index).getComments().get(0).getCreatedAt()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[0].updatedAt").value(answerResponseDetailsList.get(index).getComments().get(0).getUpdatedAt()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[0].status").value(answerResponseDetailsList.get(index).getComments().get(0).getStatus()));

        actions
                .andExpect(jsonPath("$.answers[" + index + "].comments[1].id").value(answerResponseDetailsList.get(index).getComments().get(1).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[1].content").value(answerResponseDetailsList.get(index).getComments().get(1).getContent()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[1].createdAt").value(answerResponseDetailsList.get(index).getComments().get(1).getCreatedAt()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[1].updatedAt").value(answerResponseDetailsList.get(index).getComments().get(1).getUpdatedAt()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[1].status").value(answerResponseDetailsList.get(index).getComments().get(1).getStatus()));

        actions
                .andExpect(jsonPath("$.answers[" + index + "].createdAt").value(answerResponseDetailsList.get(index).getCreatedAt()))
                .andExpect(jsonPath("$.answers[" + index + "].updatedAt").value(answerResponseDetailsList.get(index).getUpdatedAt()))
                .andExpect(jsonPath("$.answers[" + index + "].status").value(answerResponseDetailsList.get(index).getStatus()));
    }
    private static AnswerResponseDetails createDummyAnswerResponseDetails(LocalDateTime timeStamp,
                                                                          String commentMessage, Long answerId, String answerContent,
                                                                          Long commentAnswerId1_1, String commentAnswerContent1_1,
                                                                          Long commentAnswerId2_1, String commentAnswerContent2_1) {
        AnswerResponseDetails answerResponseDetails = new AnswerResponseDetails();
        answerResponseDetails.setId(answerId);
        answerResponseDetails.setContent(answerContent);
        answerResponseDetails.setMessage(commentMessage);

        List<CommentAnswerResponseDetails> comments = new ArrayList<>();
        comments.add(createDummyCommentAnswerResponseDetails(timeStamp, commentAnswerId1_1, commentAnswerContent1_1));
        comments.add(createDummyCommentAnswerResponseDetails(timeStamp, commentAnswerId2_1, commentAnswerContent2_1));
        answerResponseDetails.setMessageCount(comments.size());
        answerResponseDetails.setComments(comments);
        answerResponseDetails.setCreatedAt(timeStamp);
        answerResponseDetails.setUpdatedAt(timeStamp);
        answerResponseDetails.setStatus(Answer.AnswerStatus.ANSWER_REGISTRY);

        return answerResponseDetails;
    }

    private static CommentAnswerResponseDetails createDummyCommentAnswerResponseDetails(LocalDateTime timeStamp,
                                                                                        Long commentAnswerId, String commentAnswerContent) {
        CommentAnswerResponseDetails commentAnswerResponseDetails = new CommentAnswerResponseDetails();
        commentAnswerResponseDetails.setId(commentAnswerId);
        commentAnswerResponseDetails.setContent(commentAnswerContent);
        commentAnswerResponseDetails.setCreatedAt(timeStamp);
        commentAnswerResponseDetails.setUpdatedAt(timeStamp);
        commentAnswerResponseDetails.setStatus(CommentAnswer.CommentAnswerStatus.COMMENT_ANSWER_REGISTRY);

        return commentAnswerResponseDetails;
    }

    @Test
    @DisplayName("Answer 삭제 테스트")
    void deleteAnswer() throws Exception {
        // given
        final Long articleId = 1L;
        final Long answerId = 1L;
        final String responseContent = "답변을 삭제했습니다.";

        AnswerSimpleResponseDto responseDto = new AnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);

        doNothing().when(answerService).deleteAnswer(anyLong(), anyLong());

        // when

        ResultActions actions =
                mockMvc.perform(
                        delete("/api/article/{articleId}/answer/{answerId}", articleId, answerId)
                );

        // then
        actions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value(responseContent))
                .andDo(document(
                        "delete-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자"),
                                parameterWithName("answerId").description("답변글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }
}