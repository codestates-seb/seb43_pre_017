package com.homunculus.preproject.answer.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.answer.dto.AnswerDto;
import com.homunculus.preproject.answer.dto.AnswerResponseDto;
import com.homunculus.preproject.answer.dto.AnswerSimpleResponseDto;
import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.mapper.AnswerMapper;
import com.homunculus.preproject.answer.service.AnswerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        final String postContent = "등록할 답변글 내용";
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
                        post("/api/article/{articleId}/answer", articleId)
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
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답변글 내용")
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
        final String patchContent = "수정할 답변글 내용";
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
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
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
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답변글 내용")
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
        final Long articleId = 1L;
        final Integer evaluationScore = 999;
        final Long answerId1 = 1L;      final String answerContent1 = "답변글 내용1";
        final Long memberId1 = 1L;      final String memberName1 = "유저1";
        final Integer commentCount1 = 5;

        final Long answerId2 = 2L;      final String answerContent2 = "답변글 내용2";
        final Long memberId2 = 2L;      final String memberName2 = "유저2";
        final Integer commentCount2 = 999;

        AnswerResponseDto responseDto = new AnswerResponseDto();
        {
            responseDto.setMessage(answerMessage);
            responseDto.setArticleId(articleId);
            responseDto.setEvaluationScore(evaluationScore);

            List<AnswerResponseDto.Answers> answers = new ArrayList<>();
            answers.add(createDummyAnswers(timeStamp, answerId1, answerContent1, memberId1, memberName1, commentCount1));
            answers.add(createDummyAnswers(timeStamp, answerId2, answerContent2, memberId2, memberName2, commentCount2));

            responseDto.setMessageCount(answers.size());
            responseDto.setAnswers(answers);
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
                .andExpect(jsonPath("$.articleId").value(responseDto.getArticleId()))
                .andExpect(jsonPath("$.evaluationScore").value(responseDto.getEvaluationScore()));

        expectAnswers(responseDto.getAnswers(), 0, actions);
        expectAnswers(responseDto.getAnswers(), 1, actions);

        actions
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
                                        fieldWithPath("messageCount").type(JsonFieldType.NUMBER).description("답변글 개수"),
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("evaluationScore").type(JsonFieldType.NUMBER).description("추천 점수"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답변글 목록"),
                                        fieldWithPath("answers[].id").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("answers[].content").type(JsonFieldType.STRING).description("답변글 내용"),
                                        fieldWithPath("answers[].member").type(JsonFieldType.OBJECT).description("답변글 작성한 유저의 정보"),
                                        fieldWithPath("answers[].member.id").type(JsonFieldType.NUMBER).description("유저의 식별자"),
                                        fieldWithPath("answers[].member.name").type(JsonFieldType.STRING).description("유저의 이름"),
                                        fieldWithPath("answers[].count").type(JsonFieldType.OBJECT).description("댓글 개수 목록"),
                                        fieldWithPath("answers[].count.comments").type(JsonFieldType.NUMBER).description("총 댓글 개수"),
                                        fieldWithPath("answers[].createdAt").type(JsonFieldType.STRING).description("답변글 생성시간"),
                                        fieldWithPath("answers[].updatedAt").type(JsonFieldType.STRING).description("답변글 수정시간")
                                )
                        )
                ));
    }

    private static AnswerResponseDto.Answers createDummyAnswers(
                   LocalDateTime timeStamp, Long answerId, String answerContent, Long memberId, String memberName, Integer commentCount) {
        AnswerResponseDto.Answers answers = new AnswerResponseDto.Answers();
        answers.setId(answerId);
        answers.setContent(answerContent);

        AnswerResponseDto.Answers.Member member = new AnswerResponseDto.Answers.Member();
        member.setId(memberId);
        member.setName(memberName);
        answers.setMember(member);

        AnswerResponseDto.Answers.Count count = new AnswerResponseDto.Answers.Count();
        count.setComments(commentCount);
        answers.setCount(count);

        answers.setCreatedAt(timeStamp);
        answers.setUpdatedAt(timeStamp);

        return answers;
    }

    private void expectAnswers(List<AnswerResponseDto.Answers> answerResponseDetailsList, Integer index, ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.answers[" + index + "].id").value(answerResponseDetailsList.get(index).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].content").value(answerResponseDetailsList.get(index).getContent()));
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