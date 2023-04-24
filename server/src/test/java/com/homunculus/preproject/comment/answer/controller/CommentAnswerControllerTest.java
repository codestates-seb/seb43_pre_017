package com.homunculus.preproject.comment.answer.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerSimpleResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.mapper.CommentAnswerMapper;
import com.homunculus.preproject.comment.answer.service.CommentAnswerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CommentAnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class CommentAnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentAnswerService commentAnswerService;

    @MockBean
    private CommentAnswerMapper mapper;

    @Autowired
    private Gson gson = new Gson();

    @Test
    @DisplayName("CommentAnswer 등록 테스트")
    @WithMockUser(username = "유저이름", roles = "USER")
    void postCommentAnswerTest() throws Exception {
        // given
        final String postContent = "등록할 댓글 내용";
        final String responseContent = "댓글을 등록했습니다.";
        final Long answerId = 1L;
        final Long commentId = 1L;

        CommentAnswerDto.Post post = new CommentAnswerDto.Post();
        post.setContent(postContent);
        String content = gson.toJson(post);
        post.setAnswerId(answerId);
        given(mapper.commentAnswerPostDtoToCommentAnswer(any())).willReturn(new CommentAnswer());

        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);
        responseDto.setAnswerId(answerId);
        responseDto.setCommentId(commentId);
        given(mapper.commentAnswerToCommentAnswerSimpleResponseDto(any(), any())).willReturn(responseDto);

        given(commentAnswerService.createCommentAnswer(any())).willReturn(new CommentAnswer());

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/api/answer/{answerId}/comment", answerId)
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
                        "post-commentAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answerId").description("답변글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("답변글의 댓글 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("CommentAnswer 수정 테스트")
    @WithMockUser(username = "유저이름", roles = "USER")
    void patchCommentAnswer() throws Exception {
        // given
        final String patchContent = "수정할 댓글 내용";
        final String responseContent = "댓글을 수정했습니다.";
        final Long answerId = 1L;
        final Long commentId = 1L;

        CommentAnswerDto.Patch patch = new CommentAnswerDto.Patch();
        patch.setContent(patchContent);
        String content = gson.toJson(patch);
        patch.setAnswerId(answerId);
        patch.setCommentId(commentId);

        given(mapper.commentAnswerPatchDtoToCommentAnswer(any())).willReturn(new CommentAnswer());

        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);
        responseDto.setAnswerId(answerId);
        responseDto.setCommentId(commentId);
        given(mapper.commentAnswerToCommentAnswerSimpleResponseDto(any(), any())).willReturn(responseDto);

        given(commentAnswerService.updateCommentAnswer(any())).willReturn(new CommentAnswer());

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/api/answer/{answerId}/comment/{commentId}", answerId, commentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andDo(document(
                        "patch-commentAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answerId").description("답변글 식별자"),
                                parameterWithName("commentId").description("댓글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답변글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("답변글의 댓글 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("CommentAnswer 조회 테스트")
    void getAllCommentAnswers() throws Exception {
        // given
        final LocalDateTime timeStamp = LocalDateTime.now();

        final String message = "댓글들 조회를 완료했습니다.";
        final Long answerId = 1L;
        final Long commentId1 = 1L;     final String commentContent1 = "댓글 내용1";
        final Long memberId1 = 1L;      final String memberName1 = "유저1";

        final Long commentId2 = 2L;     final String commentContent2 = "댓글 내용2";
        final Long memberId2 = 2L;      final String memberName2 = "유저2";

        CommentAnswerResponseDto responseDto = new CommentAnswerResponseDto();
        {
            responseDto.setMessage(message);
            responseDto.setAnswerId(answerId);

            List<CommentAnswerResponseDto.Comments> comments = new ArrayList<>();
            comments.add(createDummyComments(timeStamp, commentId1, commentContent1, memberId1, memberName1));
            comments.add(createDummyComments(timeStamp, commentId2, commentContent2, memberId2, memberName2));

            responseDto.setMessageCount(comments.size());
            responseDto.setComments(comments);
        }

        Page<CommentAnswer> commentAnswers = new PageImpl<>(List.of(new CommentAnswer(), new CommentAnswer()));
        given(commentAnswerService.findCommentAnswers(anyLong(), anyInt(), anyInt())).willReturn(commentAnswers);

        // when
        given(mapper.commentAnswersToCommentAnswerResponseDto(anyLong(), any())).willReturn(responseDto);

        String page = "1";
        String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        ResultActions actions =
                mockMvc.perform(
                        get("/api/answer/{answerId}/comments", answerId)
                                .params(queryParams)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andExpect(jsonPath("$.messageCount").value(responseDto.getMessageCount()))
                .andExpect(jsonPath("$.answerId").value(responseDto.getAnswerId()))
                .andExpect(jsonPath("$.comments[0].id").value(responseDto.getComments().get(0).getId()))
                .andExpect(jsonPath("$.comments[0].content").value(responseDto.getComments().get(0).getContent()))
                .andExpect(jsonPath("$.comments[0].member.id").value(responseDto.getComments().get(0).getMember().getId()))
                .andExpect(jsonPath("$.comments[0].member.name").value(responseDto.getComments().get(0).getMember().getName()))
                .andExpect(jsonPath("$.comments[1].id").value(responseDto.getComments().get(1).getId()))
                .andExpect(jsonPath("$.comments[1].content").value(responseDto.getComments().get(1).getContent()))
                .andExpect(jsonPath("$.comments[1].member.id").value(responseDto.getComments().get(1).getMember().getId()))
                .andExpect(jsonPath("$.comments[1].member.name").value(responseDto.getComments().get(1).getMember().getName()))
                .andDo(document(
                        "getAll-commentAnswers",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answerId").description("답변글 식별자")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                                        fieldWithPath("messageCount").type(JsonFieldType.NUMBER).description("댓글 개수"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("답변글의 댓글 목록"),
                                        fieldWithPath("comments[].id").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        fieldWithPath("comments[].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                        fieldWithPath("comments[].member").type(JsonFieldType.OBJECT).description("댓글 작성한 유저"),
                                        fieldWithPath("comments[].member.id").type(JsonFieldType.NUMBER).description("유저의 식별자"),
                                        fieldWithPath("comments[].member.name").type(JsonFieldType.STRING).description("유저의 이름"),
                                        fieldWithPath("comments[].createdAt").type(JsonFieldType.STRING).description("댓글 생성시간"),
                                        fieldWithPath("comments[].updatedAt").type(JsonFieldType.STRING).description("댓글 수정시간")
                                )
                        )
                ));
    }

    private static CommentAnswerResponseDto.Comments createDummyComments(
                   LocalDateTime timeStamp, Long commentId, String commentContent, Long memberId, String memberName) {
        CommentAnswerResponseDto.Comments comments = new CommentAnswerResponseDto.Comments();
        comments.setId(commentId);
        comments.setContent(commentContent);
        comments.setCreatedAt(timeStamp);
        comments.setUpdatedAt(timeStamp);

        CommentAnswerResponseDto.Comments.Member member = new CommentAnswerResponseDto.Comments.Member();
        member.setId(memberId);
        member.setName(memberName);
        comments.setMember(member);

        return comments;
    }

    @Test
    @DisplayName("CommentAnswer 삭제 테스트")
    @WithMockUser(username = "유저이름", roles = "USER")
    void deleteCommentAnswer() throws Exception {
        // given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String responseContent = "댓글을 삭제했습니다.";

        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
        responseDto.setMessage(responseContent);
        responseDto.setAnswerId(answerId);
        responseDto.setCommentId(commentId);
        given(mapper.commentAnswerToCommentAnswerSimpleResponseDto(any(), any())).willReturn(responseDto);

        given(commentAnswerService.deleteCommentAnswer(anyLong(), anyLong())).willReturn(new CommentAnswer());

        // when
        ResultActions actions =
                mockMvc.perform(
                        delete("/api/answer/{answerId}/comment/{commentId}", answerId, commentId)
                );

        // then
        actions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andDo(document(
                        "delete-commentAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answerId").description("답변글 식별자"),
                                parameterWithName("commentId").description("댓글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("답변글의 댓글 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }
}