package com.homunculus.preproject.comment.article.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.comment.article.dto.CommentArticleDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleResponseDto;
import com.homunculus.preproject.comment.article.dto.CommentArticleSimpleResponseDto;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.comment.article.mapper.CommentArticleMapper;
import com.homunculus.preproject.comment.article.service.CommentArticleService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentArticleController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class CommentArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentArticleService commentArticleService;

    @MockBean
    private CommentArticleMapper mapper;

    @Autowired
    private Gson gson = new Gson();

    @Test
    @DisplayName("CommentArticle 등록 테스트")
    void postCommentArticleTest() throws Exception {
        // given
        final String postContent = "등록할 댓글 내용";
        final String responseContent = "댓글을 등록했습니다.";
        final Long articleId = 1L;
        final Long commentId = 1L;

        CommentArticleDto.Post post = new CommentArticleDto.Post();
        post.setContent(postContent);
        String content = gson.toJson(post);
        post.setArticleId(articleId);
        given(mapper.commentArticlePostDtoToCommentArticle(any())).willReturn(new CommentArticle());

        CommentArticleSimpleResponseDto responseDto = new CommentArticleSimpleResponseDto();
        responseDto.setMessage(responseContent);
        responseDto.setArticleId(articleId);
        responseDto.setCommentId(commentId);
        given(mapper.commentArticleToCommentArticleSimpleResponseDto(any(), any())).willReturn(responseDto);

        given(commentArticleService.createCommentArticle(any())).willReturn(new CommentArticle());

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/api/article/{articleId}/comment", articleId)
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
                        "post-commentArticle",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("질문글의 댓글 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("CommentArticle 수정 테스트")
    void patchCommentArticle() throws Exception {
        // given
        final String patchContent = "수정할 댓글 내용";
        final String responseContent = "댓글을 수정했습니다.";
        final Long articleId = 1L;
        final Long commentId = 1L;

        CommentArticleDto.Patch patch = new CommentArticleDto.Patch();
        patch.setContent(patchContent);
        String content = gson.toJson(patch);
        patch.setAnswerId(articleId);
        patch.setCommentId(commentId);

        given(mapper.commentArticlePatchDtoToCommentArticle(any())).willReturn(new CommentArticle());

        CommentArticleSimpleResponseDto responseDto = new CommentArticleSimpleResponseDto();
        responseDto.setMessage(responseContent);
        responseDto.setArticleId(articleId);
        responseDto.setCommentId(commentId);
        given(mapper.commentArticleToCommentArticleSimpleResponseDto(any(), any())).willReturn(responseDto);

        given(commentArticleService.updateCommentArticle(any())).willReturn(new CommentArticle());

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/api/article/{articleId}/comment/{commentId}", articleId, commentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andDo(document(
                        "patch-commentArticle",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자"),
                                parameterWithName("commentId").description("댓글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답변글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("질문글의 댓글 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("CommentArticle 조회 테스트")
    void getAllCommentArticles() throws Exception {
        // given
        final LocalDateTime timeStamp = LocalDateTime.now();

        final String message = "댓글들 조회를 완료했습니다.";
        final Long articleId = 1L;
        final Long commentId1 = 1L;     final String commentContent1 = "댓글 내용1";
        final Long memberId1 = 1L;      final String memberName1 = "유저1";

        final Long commentId2 = 2L;     final String commentContent2 = "댓글 내용2";
        final Long memberId2 = 2L;      final String memberName2 = "유저2";

        CommentArticleResponseDto responseDto = new CommentArticleResponseDto();
        {
            responseDto.setMessage(message);
            responseDto.setArticleId(articleId);

            List<CommentArticleResponseDto.Comments> comments = new ArrayList<>();
            comments.add(createDummyComments(timeStamp, commentId1, commentContent1, memberId1, memberName1));
            comments.add(createDummyComments(timeStamp, commentId2, commentContent2, memberId2, memberName2));

            responseDto.setMessageCount(comments.size());
            responseDto.setComments(comments);
        }

        Page<CommentArticle> commentArticlePage = new PageImpl<>(List.of(new CommentArticle(), new CommentArticle()));
        given(commentArticleService.findCommentArticles(anyLong(), anyInt(), anyInt())).willReturn(commentArticlePage);

        // when
        given(mapper.commentArticlesToCommentArticleResponseDto(any())).willReturn(responseDto);

        String page = "1";
        String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        ResultActions actions =
                mockMvc.perform(
                        get("/api/article/{articleId}/comments", articleId)
                                .params(queryParams)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andExpect(jsonPath("$.messageCount").value(responseDto.getMessageCount()))
                .andExpect(jsonPath("$.articleId").value(responseDto.getArticleId()))
                .andExpect(jsonPath("$.comments[0].id").value(responseDto.getComments().get(0).getId()))
                .andExpect(jsonPath("$.comments[0].content").value(responseDto.getComments().get(0).getContent()))
                .andExpect(jsonPath("$.comments[0].member.id").value(responseDto.getComments().get(0).getMember().getId()))
                .andExpect(jsonPath("$.comments[0].member.name").value(responseDto.getComments().get(0).getMember().getName()))
                .andExpect(jsonPath("$.comments[1].id").value(responseDto.getComments().get(1).getId()))
                .andExpect(jsonPath("$.comments[1].content").value(responseDto.getComments().get(1).getContent()))
                .andExpect(jsonPath("$.comments[1].member.id").value(responseDto.getComments().get(1).getMember().getId()))
                .andExpect(jsonPath("$.comments[1].member.name").value(responseDto.getComments().get(1).getMember().getName()))
                .andDo(document(
                        "getAll-commentArticles",
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
                                        fieldWithPath("messageCount").type(JsonFieldType.NUMBER).description("댓글 개수"),
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
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

    private static CommentArticleResponseDto.Comments createDummyComments(
                   LocalDateTime timeStamp, Long commentId, String commentContent, Long memberId, String memberName) {
        CommentArticleResponseDto.Comments comments = new CommentArticleResponseDto.Comments();
        comments.setId(commentId);
        comments.setContent(commentContent);
        comments.setCreatedAt(timeStamp);
        comments.setUpdatedAt(timeStamp);

        CommentArticleResponseDto.Comments.Member member = new CommentArticleResponseDto.Comments.Member();
        member.setId(memberId);
        member.setName(memberName);
        comments.setMember(member);

        return comments;
    }

    @Test
    @DisplayName("CommentArticle 삭제 테스트")
    void deleteCommentArticle() throws Exception {
        // given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String responseContent = "댓글을 삭제했습니다.";

        CommentArticleSimpleResponseDto responseDto = new CommentArticleSimpleResponseDto();
        responseDto.setMessage(responseContent);
        responseDto.setArticleId(articleId);
        responseDto.setCommentId(commentId);
        given(mapper.commentArticleToCommentArticleSimpleResponseDto(any(), any())).willReturn(responseDto);

        given(commentArticleService.deleteCommentArticle(anyLong(), anyLong())).willReturn(new CommentArticle());

        // when
        ResultActions actions =
                mockMvc.perform(
                        delete("/api/article/{articleId}/comment/{commentId}", articleId, commentId)
                );

        // then
        actions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andDo(document(
                        "delete-commentArticle",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("articleId").description("질문글 식별자"),
                                parameterWithName("commentId").description("댓글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("질문글의 댓글 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                                )
                        )
                ));
    }
}