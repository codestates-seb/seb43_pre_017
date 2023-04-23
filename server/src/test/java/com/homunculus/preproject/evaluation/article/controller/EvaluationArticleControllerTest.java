package com.homunculus.preproject.evaluation.article.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.evaluation.article.controller.EvaluationArticleController;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleDto;
import com.homunculus.preproject.evaluation.article.dto.EvaluationArticleSimpleResponseDto;
import com.homunculus.preproject.evaluation.article.entity.EvaluationArticle;
import com.homunculus.preproject.evaluation.article.mapper.EvaluationArticleMapper;
import com.homunculus.preproject.evaluation.article.service.EvaluationArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.homunculus.preproject.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.homunculus.preproject.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(EvaluationArticleController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class EvaluationArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluationArticleService evaluationArticleService;

    @MockBean
    private EvaluationArticleMapper mapper;

    @Autowired
    private Gson gson = new Gson();

    @Test
    @DisplayName("EvaluationArticle 등록 테스트")
    @WithMockUser(username = "유저이름", roles = "USER")
    void postEvaluationArticleTest() throws Exception {
        // given
        final String responseContent = "평가를 등록했습니다.";
        final Long articleId = 1L;
        final Long evaluationId = 1L;
        final String score = "+1";
        final Integer evaluationScore = 999;

        EvaluationArticleDto.Post post = new EvaluationArticleDto.Post();
        post.setEvaluationScore(score);
        String content = gson.toJson(post);
        post.setArticleId(articleId);
        given(mapper.evaluationArticlePostDtoToEvaluationArticle(any())).willReturn(new EvaluationArticle());

        EvaluationArticleSimpleResponseDto responseDto = new EvaluationArticleSimpleResponseDto();
        responseDto.setMessage(responseContent);
        responseDto.setArticleId(articleId);
        responseDto.setEvaluationId(evaluationId);
        responseDto.setEvaluationScore(evaluationScore);
        given(mapper.evaluationArticleToEvaluationArticleSimpleResponseDto(any(), any())).willReturn(responseDto);

        given(evaluationArticleService.createEvaluationArticle(any())).willReturn(new EvaluationArticle());

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/api/article/{articleId}/evaluation", articleId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
//                .andExpect(header().string("Authorization", is(startsWith("bearer "))))     // todo : Security 적용 시 토큰 추가해야함
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andDo(document(
                        "post-evaluationArticle",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("evaluationScore").type(JsonFieldType.STRING).description("평가 점수(\"+1\", \"0\", \"-1\")")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("evaluationId").type(JsonFieldType.NUMBER).description("질문글 추천 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                                        fieldWithPath("evaluationScore").type(JsonFieldType.NUMBER).description("투표점수 합산결과")
                                )
                        )
                ));
    }

//    @Test
//    @DisplayName("EvaluationArticle 삭제 테스트")
//    @WithMockUser(username = "유저이름", roles = "USER")
//    void deleteEvaluationArticle() throws Exception {
//        // given
//        final Long articleId = 1L;
//        final Long evaluationId = 1L;
//        final String responseContent = "평가를 삭제했습니다.";
//
//        EvaluationArticleSimpleResponseDto responseDto = new EvaluationArticleSimpleResponseDto();
//        responseDto.setMessage(responseContent);
//        responseDto.setArticleId(articleId);
//        responseDto.setEvaluationId(evaluationId);
//        given(mapper.evaluationArticleToEvaluationArticleSimpleResponseDto(any(), any())).willReturn(responseDto);
//
//        given(evaluationArticleService.deleteEvaluationArticle(anyLong(), anyLong())).willReturn(new EvaluationArticle());
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        delete("/api/article/{articleId}/evaluation/{evaluationId}", articleId, evaluationId)
//                );
//
//        // then
//        actions
//                .andExpect(status().isNoContent())
//                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
//                .andDo(document(
//                        "delete-evaluationArticle",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("articleId").description("질문글 식별자"),
//                                parameterWithName("evaluationId").description("평가 식별자")
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
//                                        fieldWithPath("evaluationId").type(JsonFieldType.NUMBER).description("질문글 추천 식별자"),
//                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
//                                )
//                        )
//                ));
//    }
}