package com.homunculus.preproject.article.controller;

import com.google.gson.Gson;
import com.homunculus.preproject.article.dto.ArticleDto;
import com.homunculus.preproject.article.dto.ArticleResponseDetailsDto;
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
        final LocalDateTime timeStamp = LocalDateTime.now();

        final String articleMessage = "질문글 조회를 완료했습니다.";

        final Long articleId1 = 1L;
        final Integer evaluationScore1 = 32500;
        final Integer commentCount1 = 99;               final Integer answerCount1 = 5;
        final String articleTitle1 = "질문글 제목1";      final String articleContent1 = "질문글 내용1";
        final Long memberId1 = 1L;                      final String memberName1 = "유저1";

        final Long articleId2 = 2L;
        final Integer evaluationScore2 = 100;
        final Integer commentCount2 = 20;               final Integer answerCount2 = 9;
        final String articleTitle2 = "질문글 제목2";      final String articleContent2 = "질문글 내용2";
        final Long memberId2 = 3L;                      final String memberName2 = "유저2";

        ArticleResponseDto responseDto = new ArticleResponseDto();
        {
            responseDto.setMessage(articleMessage);

            List<ArticleResponseDto.Articles> articles = new ArrayList<>();
            articles.add(createDummyArticles(timeStamp, evaluationScore1,
                    articleId1, articleTitle1, articleContent1,
                    memberId1, memberName1, commentCount1, answerCount1
            ));
            articles.add(createDummyArticles(timeStamp, evaluationScore2,
                    articleId2, articleTitle2, articleContent2,
                    memberId2, memberName2, commentCount2, answerCount2
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
                                        fieldWithPath("articles").type(JsonFieldType.ARRAY).description("질문글 목록"),
                                        fieldWithPath("articles[].id").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("articles[].title").type(JsonFieldType.STRING).description("질문글 제목"),
                                        fieldWithPath("articles[].content").type(JsonFieldType.STRING).description("질문글 내용"),
                                        fieldWithPath("articles[].evaluationScore").type(JsonFieldType.NUMBER).description("추천 점수"),
                                        fieldWithPath("articles[].createdAt").type(JsonFieldType.STRING).description("질문글 생성시간"),
                                        fieldWithPath("articles[].updatedAt").type(JsonFieldType.STRING).description("질문글 수정시간"),
                                        fieldWithPath("articles[].member").type(JsonFieldType.OBJECT).description("질문글 등록한 유저의 정보"),
                                        fieldWithPath("articles[].member.id").type(JsonFieldType.NUMBER).description("유저의 식별번호"),
                                        fieldWithPath("articles[].member.name").type(JsonFieldType.STRING).description("유저의 이름"),
                                        fieldWithPath("articles[].count").type(JsonFieldType.OBJECT).description("질문에 대한 개수 목록"),
                                        fieldWithPath("articles[].count.comments").type(JsonFieldType.NUMBER).description("댓글 개수"),
                                        fieldWithPath("articles[].count.answers").type(JsonFieldType.NUMBER).description("답변글 개수")
                                )
                        )
                ));
    }

    private static ArticleResponseDto.Articles createDummyArticles(LocalDateTime timeStamp, Integer evaluationScore,
                                                            Long articleId, String articleTitle, String articleContent,
                                                            Long memberId, String memberName,
                                                            Integer commentCount, Integer answerCount) {
        ArticleResponseDto.Articles articles = new ArticleResponseDto.Articles();
        articles.setId(articleId);
        articles.setTitle(articleTitle);
        articles.setContent(articleContent);
        articles.setEvaluationScore(evaluationScore);
        articles.setCreatedAt(timeStamp);
        articles.setUpdatedAt(timeStamp);

        ArticleResponseDto.Member member = new ArticleResponseDto.Member();
        member.setId(memberId);
        member.setName(memberName);
        articles.setMember(member);

        ArticleResponseDto.Articles.Count count = new ArticleResponseDto.Articles.Count();
        count.setComments(commentCount);
        count.setAnswers(answerCount);
        articles.setCount(count);

        return articles;
    }

    private void expectArticles(List<ArticleResponseDto.Articles> articles, Integer index, ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.articles[" + index + "].id").value(articles.get(index).getId()))
                .andExpect(jsonPath("$.articles[" + index + "].title").value(articles.get(index).getTitle()))
                .andExpect(jsonPath("$.articles[" + index + "].content").value(articles.get(index).getContent()))
                .andExpect(jsonPath("$.articles[" + index + "].evaluationScore").value(articles.get(index).getEvaluationScore()))
                .andExpect(jsonPath("$.articles[" + index + "].member.id").value(articles.get(index).getMember().getId()))
                .andExpect(jsonPath("$.articles[" + index + "].member.name").value(articles.get(index).getMember().getName()))
                .andExpect(jsonPath("$.articles[" + index + "].count.comments").value(articles.get(index).getCount().getComments()))
                .andExpect(jsonPath("$.articles[" + index + "].count.answers").value(articles.get(index).getCount().getAnswers()));
    }

    @Test
    @DisplayName("Article 상세 조회 테스트")
    void getArticle() throws Exception {
        // given
        final LocalDateTime timeStamp = LocalDateTime.now();

        final String articleMessage = "질문글 조회를 완료했습니다.";
        final Long articleId = 1L;
        final String articleTitle = "질문글 제목";
        final String articleContent = "질문글 내용";
        final Integer evaluationScore = 54321;

        final Long memberId = 1L;
        final String memberName = "유저";

        final Long commentArticleId1 = 1L;      final String commentArticleContent1 = "질문의 댓글1";
        final Long commentArticleId2 = 2L;      final String commentArticleContent2 = "질문의 댓글2";

        final Long answerId1 = 1L;
        final String answerContent1 = "답변1의 내용";
        final Long commentAnswerId1_1 = 1L;     final String commentAnswerContent1_1 = "답변1의 댓글1";
        final Long commentAnswerId1_2 = 2L;     final String commentAnswerContent1_2 = "답변1의 댓글2";

        final Long answerId2 = 2L;
        final String answerContent2 = "답변2의 내용";
        final Long commentAnswerId2_1 = 3L;     final String commentAnswerContent2_1 = "답변2의 댓글1";
        final Long commentAnswerId2_2 = 4L;     final String commentAnswerContent2_2 = "답변2의 댓글2";


        ArticleResponseDetailsDto responseDto = new ArticleResponseDetailsDto();
        responseDto.setMessage(articleMessage);

        ArticleResponseDetailsDto.Article article = new ArticleResponseDetailsDto.Article();
        article.setId(articleId);
        article.setTitle(articleTitle);
        article.setContent(articleContent);
        article.setEvaluationScore(evaluationScore);
        article.setCreatedAt(timeStamp);
        article.setUpdatedAt(timeStamp);
        responseDto.setArticle(article);

        ArticleResponseDetailsDto.Member member = new ArticleResponseDetailsDto.Member();
        member.setId(memberId);
        member.setName(memberName);
        responseDto.setMember(member);

        List<ArticleResponseDetailsDto.Comments> commentArticles = new ArrayList<>();
        {
            ArticleResponseDetailsDto.Comments comment1 = new ArticleResponseDetailsDto.Comments();
            comment1.setId(commentArticleId1);
            comment1.setContent(commentArticleContent1);
            comment1.setCreatedAt(timeStamp);
            comment1.setUpdatedAt(timeStamp);
            commentArticles.add(comment1);

            ArticleResponseDetailsDto.Comments comment2 = new ArticleResponseDetailsDto.Comments();
            comment2.setId(commentArticleId2);
            comment2.setContent(commentArticleContent2);
            comment2.setCreatedAt(timeStamp);
            comment2.setUpdatedAt(timeStamp);
            commentArticles.add(comment2);
        }
        responseDto.setComments(commentArticles);

        List<ArticleResponseDetailsDto.Answers> answers = new ArrayList<>();
        {
            answers.add(createDummyAnswer(
                    timeStamp, answerId1, answerContent1,
                    commentAnswerId1_1, commentAnswerContent1_1,
                    commentAnswerId1_2, commentAnswerContent1_2
            ));
            answers.add(createDummyAnswer(
                    timeStamp, answerId2, answerContent2,
                    commentAnswerId2_1, commentAnswerContent2_1,
                    commentAnswerId2_2, commentAnswerContent2_2
            ));
        }
        responseDto.setAnswers(answers);

        given(articleService.findArticle(anyLong())).willReturn(new Article());

        // when
        given(mapper.articleToArticleResponseDetailsDto(any())).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        get("/api/article/{articleId}", articleId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andExpect(jsonPath("$.article.id").value(responseDto.getArticle().getId()))
                .andExpect(jsonPath("$.article.title").value(responseDto.getArticle().getTitle()))
                .andExpect(jsonPath("$.article.content").value(responseDto.getArticle().getContent()))
                .andExpect(jsonPath("$.member.id").value(responseDto.getMember().getId()))
                .andExpect(jsonPath("$.member.name").value(responseDto.getMember().getName()));

        expectComments(responseDto.getComments(), 0, actions);
        expectComments(responseDto.getComments(), 1, actions);
        expectAnswers(responseDto.getAnswers(), 0, actions);
        expectAnswers(responseDto.getAnswers(), 1, actions);

        actions
                .andDo(document(
                        "get-article",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                                        fieldWithPath("article").type(JsonFieldType.OBJECT).description("질문글 정보"),
                                        fieldWithPath("article.id").type(JsonFieldType.NUMBER).description("질문글 식별자"),
                                        fieldWithPath("article.title").type(JsonFieldType.STRING).description("질문글 제목"),
                                        fieldWithPath("article.content").type(JsonFieldType.STRING).description("질문글 내용"),
                                        fieldWithPath("article.evaluationScore").type(JsonFieldType.NUMBER).description("질문글 추천점수"),
                                        fieldWithPath("article.createdAt").type(JsonFieldType.STRING).description("질문글 생성시간"),
                                        fieldWithPath("article.updatedAt").type(JsonFieldType.STRING).description("질문글 수정시간"),
                                        fieldWithPath("member").type(JsonFieldType.OBJECT).description("질문글 등록 유저 정보"),
                                        fieldWithPath("member.id").type(JsonFieldType.NUMBER).description("유저 식별자"),
                                        fieldWithPath("member.name").type(JsonFieldType.STRING).description("유저 이름"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("질문에 대한 답변글 목록"),
                                        fieldWithPath("comments[].id").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("comments[].content").type(JsonFieldType.STRING).description("답변글 내용"),
                                        fieldWithPath("comments[].createdAt").type(JsonFieldType.STRING).description("답변글 생성시간"),
                                        fieldWithPath("comments[].updatedAt").type(JsonFieldType.STRING).description("답변글 수정시간"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("질문의 답변글 목록"),
                                        fieldWithPath("answers[].id").type(JsonFieldType.NUMBER).description("답변글 식별자"),
                                        fieldWithPath("answers[].content").type(JsonFieldType.STRING).description("답변글 내용"),
                                        fieldWithPath("answers[].comments").type(JsonFieldType.ARRAY).description("답변글의 댓글 목록"),
                                        fieldWithPath("answers[].createdAt").type(JsonFieldType.STRING).description("답변의 생성시간"),
                                        fieldWithPath("answers[].updatedAt").type(JsonFieldType.STRING).description("답변의 수정시간"),
                                        fieldWithPath("answers[].comments[].id").type(JsonFieldType.NUMBER).description("댓글의 식별자"),
                                        fieldWithPath("answers[].comments[].content").type(JsonFieldType.STRING).description("댓글의 내용"),
                                        fieldWithPath("answers[].comments[].createdAt").type(JsonFieldType.STRING).description("댓글의 생성시간"),
                                        fieldWithPath("answers[].comments[].updatedAt").type(JsonFieldType.STRING).description("댓글의 수정시간")
                                )
                        )
                ));
    }

    private static ArticleResponseDetailsDto.Answers createDummyAnswer(LocalDateTime timeStamp,
                                                                       Long answerId, String answerContent,
                                                                       Long commentAnswerId1, String commentAnswerContent1,
                                                                       Long commentAnswerId2, String commentAnswerContent2) {
        ArticleResponseDetailsDto.Answers answer = new ArticleResponseDetailsDto.Answers();
        answer.setId(answerId);
        answer.setContent(answerContent);
        answer.setCreatedAt(timeStamp);
        answer.setUpdatedAt(timeStamp);

        List<ArticleResponseDetailsDto.Answers.Comments> commentAnswers = new ArrayList<>();
        {
            commentAnswers.add(createDummyCommentAnswer(timeStamp, commentAnswerId1, commentAnswerContent1));
            commentAnswers.add(createDummyCommentAnswer(timeStamp, commentAnswerId2, commentAnswerContent2));
        }
        answer.setComments(commentAnswers);
        return answer;
    }

    private static ArticleResponseDetailsDto.Answers.Comments createDummyCommentAnswer(LocalDateTime timeStamp,
                                                                                       Long commentAnswerId1_1, String commentAnswerContent1_1) {
        ArticleResponseDetailsDto.Answers.Comments comment = new ArticleResponseDetailsDto.Answers.Comments();
        comment.setId(commentAnswerId1_1);
        comment.setContent(commentAnswerContent1_1);
        comment.setCreatedAt(timeStamp);
        comment.setUpdatedAt(timeStamp);
        return comment;
    }


    private void expectComments(List<ArticleResponseDetailsDto.Comments> comments, Integer index, ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.comments[" + index + "].id").value(comments.get(index).getId()))
                .andExpect(jsonPath("$.comments[" + index + "].content").value(comments.get(index).getContent()));
    }

    private void expectAnswers(List<ArticleResponseDetailsDto.Answers> answers, Integer index, ResultActions actions) throws Exception {
        actions
                .andExpect(jsonPath("$.answers[" + index + "].id").value(answers.get(index).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].content").value(answers.get(index).getContent()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[0].id").value(answers.get(index).getComments().get(0).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[0].content").value(answers.get(index).getComments().get(0).getContent()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[1].id").value(answers.get(index).getComments().get(1).getId()))
                .andExpect(jsonPath("$.answers[" + index + "].comments[1].content").value(answers.get(index).getComments().get(1).getContent()));
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