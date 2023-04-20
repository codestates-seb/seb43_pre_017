package com.homunculus.preproject.article.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleSimpleResponseDto;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.mapper.ArticleMapper;
import com.homunculus.preproject.article.service.ArticleService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @MockBean
    private ArticleMapper mapper;

    @Autowired
    private Gson gson = new Gson();

    @Test
    @DisplayName("Article 등록 테스트")
    void postArticleTest() throws Exception {
        // given
        final String postTitle = "등록할 질문글 제목";
        final String postContent = "등록할 질문글 내용";
        final String responseContent = "질문을 등록했습니다.";
        final Long articleId = 1L;

        ArticleDto.Post post = new ArticleDto.Post();
        post.setTitle(postTitle);
        post.setContent(postContent);
        String content = gson.toJson(post);
        given(mapper.articlePostDtoToArticle(any())).willReturn(new Article());

        ArticleSimpleResponseDto responseDto = new ArticleSimpleResponseDto();
        responseDto.setMessage(responseContent);

        given(articleService.createArticle(any())).willReturn(new Article());

        // when

        ResultActions actions =
                mockMvc.perform(
                        post("/api/article")
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
                        "post-article",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문글 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("질문글 내용")
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
    @DisplayName("Article 수정 테스트")
    void patchArticle() throws Exception {
        // given
        final String patchTitle = "수정할 질문글 제목";
        final String patchContent = "수정할 질문글 내용";
        final String responseContent = "질문을 수정했습니다.";
        final Long articleId = 1L;

        ArticleDto.Patch patch = new ArticleDto.Patch();
        patch.setTitle(patchTitle);
        patch.setContent(patchContent);
        String content = gson.toJson(patch);

        given(mapper.articlePatchDtoToArticle(any())).willReturn(new Article());

        ArticleSimpleResponseDto responseDto = new ArticleSimpleResponseDto();
        responseDto.setMessage(responseContent);

        given(articleService.updateArticle(any())).willReturn(new Article());

        // when

        ResultActions actions =
                mockMvc.perform(
                        patch("/api/article/{articleId}", articleId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andDo(document(
                        "patch-article",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문글 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("질문글 내용")
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
    @DisplayName("Answer 조회 테스트")
    void getAllAnswers() throws Exception {
        // given
        final LocalDateTime timeStamp = LocalDateTime.of(2023,4,19,21,0,0);

        final String answerMessage = "답변글 조회를 완료했습니다.";
        final Long articleId = 1L;
        final Long answerId1 = 1L;          final String answerContent1 = "무언가1";
        final Long answerId2 = 2L;          final String answerContent2 = "무언가2";

        AnswerResponseDto responseDto = new AnswerResponseDto();
        {
            responseDto.setMessage(answerMessage);
            responseDto.setArticleId(articleId);

            List<AnswerResponseDto.Answers> answers = new ArrayList<>();
            answers.add(createDummyAnswers(timeStamp, answerId1, answerContent1));
            answers.add(createDummyAnswers(timeStamp, answerId2, answerContent2));

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
                        "getAll-answers",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                                        fieldWithPath("messageCount").type(JsonFieldType.NUMBER).description("답변글 갯수"),
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답변글 묶음"),
                                        fieldWithPath("answers[0].id").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("answers[0].content").type(JsonFieldType.STRING).description("답변글 내용"),
                                        fieldWithPath("answers[0].createdAt").type(JsonFieldType.STRING).description("답변글 생성시간"),
                                        fieldWithPath("answers[0].updatedAt").type(JsonFieldType.STRING).description("답변글 수정시간"),
                                        fieldWithPath("answers[0].status").type(JsonFieldType.STRING).description("답변글 상태(등록상태, 삭제상태"),
                                        fieldWithPath("status").type(JsonFieldType.STRING).description("질문글 상태(등록상태, 삭제상태)")
                                )
                        )
                ));
    }

    private static AnswerResponseDto.Answers createDummyAnswers(LocalDateTime timeStamp, Long answerId, String answerContent) {
        AnswerResponseDto.Answers answers = new AnswerResponseDto.Answers();
        answers.setId(answerId);
        answers.setContent(answerContent);
        answers.setCreatedAt(timeStamp);
        answers.setUpdatedAt(timeStamp);
        answers.setStatus(Answer.AnswerStatus.ANSWER_REGISTRY);

        return answers;
    }

    private void expectAnswers(List<AnswerResponseDto.Answers> answerResponseDetailsList, Integer index, ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.answers[" + index + "].id").value(answerResponseDetailsList.get(index).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].content").value(answerResponseDetailsList.get(index).getContent()))
                .andExpect(jsonPath("$.answers[" + index + "].status").value(answerResponseDetailsList.get(index).getStatus()));
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
 */
}