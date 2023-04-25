package com.homunculus.preproject.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.utils.AuthenticationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private AuthenticationUtils authenticationUtils;

    @InjectMocks
    private ArticleService articleService;

    @Test
    @DisplayName("질문글 생성 테스트")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createArticleTest() {
        //given
        final Long articleId = 1L;
        final String title = "질문의 제목";
        final String content = "질문의 내용";
        final String email = "email@gmail.com";
        Article article = createDummyArticle(articleId, title, content, email);

        given(articleRepository.save(article)).willReturn(article);
        given(authenticationUtils.findMemberWithCheckAllowed(any(Member.class), anyBoolean(), any())).willReturn(article.getMember());

        //when, then
        assertDoesNotThrow(() -> articleService.createArticle(article));
    }

    private static Article createDummyArticle(Long articleId, String title, String content, String email) {
        Article article = new Article();

        article.setArticleId(articleId);
        article.setTitle(title);
        article.setContent(content);

        Member member1 = new Member();
        member1.setEmail(email);
        article.setMember(member1);

        return article;
    }

    @Test
    @DisplayName("질문글 수정 테스트 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void updateArticleTest() {
        //given
        final Long articleId = 1L;
        final String email = "email@gmail.com";

        final String title = "질문의 내용";
        final String content = "질문의 내용";
        Article article = createDummyArticle(articleId, title, content, email);

        final String updateTitle = "변경된 질문의 제목";
        final String updateContent = "변경된 질문의 내용";
        Article updateArticle = createDummyArticle(articleId, updateTitle, updateContent, email);

        given(articleRepository.save(article)).willReturn(new Article());
        given(articleRepository.findById(article.getArticleId())).willReturn(Optional.of(article));
        given(authenticationUtils.findMemberWithCheckAllowed(any(Member.class), anyBoolean(), any())).willReturn(article.getMember());

        //when, then
        assertDoesNotThrow( () -> articleService.updateArticle(updateArticle) );
    }

    @Test
    @DisplayName("질문글 수정 테스트 - 질문글 없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void updateArticleTest_NotExistArticle_Fail() {
        //given
        final Long articleId = 1L;
        final String email = "email@gmail.com";
        final String title = "질문의 제목";
        final String content = "질문의 내용";
        Article article = createDummyArticle(null, title, content, email);

        given(articleRepository.save(article)).willReturn(new Article());
        given(articleRepository.findById(article.getArticleId())).willReturn(Optional.of(article));
        given(authenticationUtils.findMemberWithCheckAllowed(any(Member.class), anyBoolean(), any())).willReturn(article.getMember());

        //when, then
        assertThrows(NullPointerException.class,
                () -> articleService.updateArticle(article));
    }

    @Test
    @DisplayName("질문글 조회 테스트 - 성공")
    void findArticle() {
        final Long articleId = 1L;
        final String title = "질문의 제목";
        final String content = "질문의 내용";
        Article article = createDummyArticle(articleId, title, content, null);

        given(articleRepository.findById(anyLong())).willReturn(Optional.of(article));
        given(articleRepository.save(article)).willReturn(article);

        assertDoesNotThrow(() -> articleService.findArticle(articleId));
    }

    @Test
    @DisplayName("질문글 리스트 조회 - 성공")
    void findArticles() {
        final Long articleId = 1L;
        final Integer page = 1;
        final Integer size = 10;
        final String typeForSorting = "조회순";
        final String title = "질문글의 제목";
        final String content = "질문글의 내용";

        Article article = createDummyArticle(articleId, title, content, null);

        given(articleRepository.findAll(PageRequest.of(page, size)))
                .willReturn(new PageImpl<>(List.of(new Article())));

        assertDoesNotThrow(() -> articleService.findArticles(page, size, typeForSorting));
    }

    @Test
    @DisplayName("질문글 삭제 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void deleteArticle() {
        //given
        final Long articleId = 1L;
        final String email = "email@gmail.com";
        final String title = "기존에 있던 제목";
        final String content = "기존에 있던 내용";

        Article article = createDummyArticle(articleId, title, content, email);
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(article));
        given(authenticationUtils.findMemberWithCheckAllowed(any(Member.class), anyBoolean(), any())).willReturn(article.getMember());

        doNothing().when(articleRepository).delete(article);

        //when, then
        assertDoesNotThrow(() -> articleService.deleteArticle(articleId));
    }

    @Test
    @DisplayName("질문글 삭제 - 질문글 없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void deleteArticle_NotExistArticle_Fail() {
        //given
        final Long articleId = 1L;
        final String email = "email@gmail.com";
        final String title = "기존에 있던 제목";
        final String content = "기존에 있던 내용";

        Article article = createDummyArticle(articleId, title, content, email);
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(article));
        given(authenticationUtils.findMemberWithCheckAllowed(any(Member.class), anyBoolean(), any())).willReturn(article.getMember());

        doNothing().when(articleRepository).delete(article);

        //when, then
        assertThrows(NullPointerException.class,
                () -> articleService.deleteArticle(null) );
    }
}