package com.homunculus.preproject.article.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDto;
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

    @Test
    @DisplayName("Article 조회 테스트")
    void getAllArticles() throws Exception {
        // given
        final LocalDateTime timeStamp = LocalDateTime.of(2023,4,19,21,0,0);

        final String articleMessage = "질문글 조회를 완료했습니다.";

        final Long articleId1 = 1L;
        final Integer answerCount1 = 5;
        final String articleTitle1 = "질문글 제목1";     final String articleContent1 = "질문글 내용1";
        final Long userId1 = 1L;        final String userName1 = "유저1";

        final Long articleId2 = 2L;
        final Integer answerCount2 = 9;
        final String articleTitle2 = "질문글 제목2";     final String articleContent2 = "질문글 내용2";
        final Long userId2 = 3L;        final String userName2 = "유저2";

        ArticleResponseDto responseDto = new ArticleResponseDto();
        {
            responseDto.setMessage(articleMessage);

            List<ArticleResponseDto.Articles> articles = new ArrayList<>();
            articles.add(createDummyArticles(timeStamp,
                    articleId1, articleTitle1, articleContent1,
                    userId1, userName1, answerCount1
            ));
            articles.add(createDummyArticles(timeStamp,
                    articleId2, articleTitle2, articleContent2,
                    userId2, userName2, answerCount2
            ));
            responseDto.setMessageCount(articles.size());
            responseDto.setArticles(articles);
        }

        Page<Article> articlePage = new PageImpl<>(List.of(new Article(), new Article()));
        given(articleService.findArticles(anyInt(), anyInt())).willReturn(articlePage);

        // when
        given(mapper.articlesToArticleResponseDto(any())).willReturn(responseDto);

        String page = "1";
        String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        ResultActions actions =
                mockMvc.perform(
                        get("/api/articles")
                                .params(queryParams)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andExpect(jsonPath("$.messageCount").value(responseDto.getMessageCount()));

        expectArticles(responseDto.getArticles(), 0, actions);
        expectArticles(responseDto.getArticles(), 1, actions);

        actions
                .andDo(document(
                        "getAll-articles",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                                        fieldWithPath("messageCount").type(JsonFieldType.NUMBER).description("질문글 개수"),
                                        fieldWithPath("articles").type(JsonFieldType.ARRAY).description("질문글 묶음"),
                                        fieldWithPath("articles[0].id").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("articles[0].title").type(JsonFieldType.STRING).description("질문글 제목"),
                                        fieldWithPath("articles[0].content").type(JsonFieldType.STRING).description("질문글 내용"),
                                        fieldWithPath("articles[0].createdAt").type(JsonFieldType.STRING).description("질문글 생성시간"),
                                        fieldWithPath("articles[0].updatedAt").type(JsonFieldType.STRING).description("질문글 수정시간"),
                                        fieldWithPath("articles[0].user").type(JsonFieldType.OBJECT).description("질문글 등록한 유저의 정보"),
                                        fieldWithPath("articles[0].user.id").type(JsonFieldType.NUMBER).description("질문글 등록한 유저의 식별번호"),
                                        fieldWithPath("articles[0].user.name").type(JsonFieldType.STRING).description("질문글 등록한 유저의 이름"),
                                        fieldWithPath("articles[0].answerCount").type(JsonFieldType.NUMBER).description("질문에 대한 답변글 개수"),
                                        fieldWithPath("articles[0].status").type(JsonFieldType.STRING).description("질문글의 상태(등록상태, 삭제상태")
                                )
                        )
                ));
    }

    private static ArticleResponseDto.Articles createDummyArticles(LocalDateTime timeStamp,
                                                            Long articleId, String articleTitle, String articleContent,
                                                            Long userId, String userName,
                                                            Integer answerCount) {
        ArticleResponseDto.Articles articles = new ArticleResponseDto.Articles();
        articles.setId(articleId);
        articles.setTitle(articleTitle);
        articles.setContent(articleContent);
        articles.setCreatedAt(timeStamp);
        articles.setUpdatedAt(timeStamp);

        ArticleResponseDto.User user = new ArticleResponseDto.User();
        user.setId(userId);
        user.setName(userName);
        articles.setUser(user);

        articles.setAnswerCount(answerCount);
        articles.setStatus(Article.ArticleStatus.ARTICLE_REGISTRY);

        return articles;
    }

    private void expectArticles(List<ArticleResponseDto.Articles> articles, Integer index, ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.articles[" + index + "].id").value(articles.get(index).getId()))
                .andExpect(jsonPath("$.articles[" + index + "].title").value(articles.get(index).getTitle()))
                .andExpect(jsonPath("$.articles[" + index + "].content").value(articles.get(index).getContent()))
                .andExpect(jsonPath("$.articles[" + index + "].user.id").value(articles.get(index).getUser().getId()))
                .andExpect(jsonPath("$.articles[" + index + "].user.name").value(articles.get(index).getUser().getName()))
                .andExpect(jsonPath("$.articles[" + index + "].answerCount").value(articles.get(index).getAnswerCount()))
                .andExpect(jsonPath("$.articles[" + index + "].status").value(articles.get(index).getStatus()));
    }

    @Test
    @DisplayName("Article 삭제 테스트")
    void deleteArticle() throws Exception {
        // given
        final Long articleId = 1L;
        final String responseContent = "질문을 삭제했습니다.";

        ArticleSimpleResponseDto responseDto = new ArticleSimpleResponseDto();
        responseDto.setMessage(responseContent);

        doNothing().when(articleService).deleteArticle(anyLong());

        // when
        ResultActions actions =
                mockMvc.perform(
                        delete("/api/article/{articleId}", articleId)
                );

        // then
        actions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andDo(document(
                        "delete-article",
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
}