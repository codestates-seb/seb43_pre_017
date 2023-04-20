//package com.homunculus.preproject.evalutation.answer.controller;
//
//import com.google.gson.Gson;
//import com.homunculus.preproject.evaluation.answer.controller.EvaluationAnswerController;
//import com.homunculus.preproject.evaluation.answer.mapper.EvaluationAnswerMapper;
//import com.homunculus.preproject.evaluation.answer.service.EvaluationAnswerService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.homunculus.preproject.util.ApiDocumentUtils.getRequestPreProcessor;
//import static com.homunculus.preproject.util.ApiDocumentUtils.getResponsePreProcessor;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(EvaluationAnswerController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//class EvaluationAnswerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EvaluationAnswerService evaluationAnswerService;
//
//    @MockBean
//    private EvaluationAnswerMapper mapper;
//
//    @Autowired
//    private Gson gson = new Gson();
//
//    @Test
//    @DisplayName("EvaluationAnswer 등록 테스트")
//    void postCommentAnswerTest() throws Exception {
//        // given
//        final String postContent = "등록할 댓글 내용";
//        final String responseContent = "댓글을 등록했습니다.";
//        final Long answerId = 1L;
//
//        CommentAnswerDto.Post post = new CommentAnswerDto.Post();
//        post.setContent(postContent);
//        String content = gson.toJson(post);
//        post.setAnswerId(answerId);
//        given(mapper.commentAnswerPostDtoToCommentAnswer(any())).willReturn(new CommentAnswer());
//
//        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
//        responseDto.setMessage(responseContent);
//
//        given(commentAnswerService.createCommentAnswer(any())).willReturn(new CommentAnswer());
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/api/answer/{answerId}/comment", answerId)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        // then
//        actions
//                .andExpect(status().isCreated())
////                .andExpect(header().string("Authorization", is(startsWith("bearer "))))     // todo : Security 적용 시 토큰 추가해야함
//                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
//                .andDo(document(
//                        "post-commentAnswer",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("answerId").description("답변글 식별자")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName("CommentAnswer 삭제 테스트")
//    void deleteCommentAnswer() throws Exception {
//        // given
//        final Long answerId = 1L;
//        final Long commentId = 1L;
//        final String responseContent = "댓글을 삭제했습니다.";
//
//        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
//        responseDto.setMessage(responseContent);
//
//        doNothing().when(commentAnswerService).deleteCommentAnswer(anyLong(), anyLong());
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        delete("/api/answer/{answerId}/comment/{commentId}", answerId, commentId)
//                );
//
//        // then
//        actions
//                .andExpect(status().isNoContent())
//                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
//                .andDo(document(
//                        "delete-commentAnswer",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("answerId").description("답변글 식별자"),
//                                parameterWithName("commentId").description("댓글 식별자")
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
//                                )
//                        )
//                ));
//    }
//}