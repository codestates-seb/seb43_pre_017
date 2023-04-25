package com.homunculus.preproject.comment.article.service;

import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.comment.article.entity.CommentArticle;
import com.homunculus.preproject.comment.article.repository.CommentArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
class CommentArticleServiceTest {

    @Mock
    CommentArticleRepository commentArticleRepository;

    @Mock
    MemberService memberService;

    @InjectMocks
    CommentArticleService commentArticleService;

    @Test
    @DisplayName("질문에 대한 댓글 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createCommentArticle() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;

        final String content = "댓글의 내용";
        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, null);

        given(commentArticleRepository.save(comment)).willReturn(comment);
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        //when, then
        assertDoesNotThrow(() -> commentArticleService.createCommentArticle(comment));
    }

    private static CommentArticle createDummyCommentArticle(Long articleId, Long commentId,
                                                           String content, String email) {
        CommentArticle comment = new CommentArticle();
        comment.setCommentArticleId(commentId);
        comment.setContent(content);

        Article article = new Article();
        article.setArticleId(articleId);
        comment.setArticle(article);

        Member member = new Member();
        member.setEmail(email);
        comment.setMember(member);

        return comment;
    }

    @Test
    @DisplayName("질문글에 대한 댓글 수정 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void updateCommentArticleTest() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentArticle updateComment = createDummyCommentArticle(articleId, commentId, updateContent, email);

        given(commentArticleRepository.save(comment)).willReturn(new CommentArticle());
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        //when
        CommentArticle updatedComment = commentArticleService.updateCommentArticle(updateComment);

        //then
        assertEquals(updatedComment.getCommentArticleId(), updateComment.getCommentArticleId());
        assertEquals(updatedComment.getContent(), updateComment.getContent());
        assertEquals(updatedComment.getArticle().getArticleId(), updateComment.getArticle().getArticleId());
    }

    @Test
    @DisplayName("질문글에 대한 댓글 수정 - 인증 실패")
    @WithMockUser(username = "notEmail@gmail.com", roles = "USER")
    void updateCommentArticleTest_authentication_Fail() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentArticle updateComment = createDummyCommentArticle(articleId, commentId, updateContent, email);

        given(commentArticleRepository.save(comment)).willReturn(new CommentArticle());
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentArticleService.updateCommentArticle(updateComment) );
    }

    @Test
    @DisplayName("질문글에 대한 댓글 수정 - 인증정보 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void updateCommentArticleTest_NotExistAuthentication_Fail() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;

        final String content = "댓글의 내용";
        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, null);

        final String updateContent = "변경된 댓글의 내용";
        CommentArticle updateComment = createDummyCommentArticle(articleId, commentId, updateContent, null);

        given(commentArticleRepository.save(comment)).willReturn(new CommentArticle());
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        //when, then
        assertThrows(NullPointerException.class,
                () -> commentArticleService.updateCommentArticle(updateComment) );
    }

    @Test
    @DisplayName("질문글에 대한 댓글 수정 - 댓글 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void updateCommentArticleTest_NotExistComment_Fail() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentArticle updateComment = createDummyCommentArticle(articleId, null, updateContent, email);

        given(commentArticleRepository.save(comment)).willReturn(new CommentArticle());
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentArticleService.updateCommentArticle(updateComment) );
    }

    @Test
    @DisplayName("질문글에 대한 댓글 수정 - 질문글 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void updateCommentArticleTest_NotExistArticle_Fail() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentArticle updateComment = createDummyCommentArticle(null, commentId, updateContent, email);

        given(commentArticleRepository.save(comment)).willReturn(new CommentArticle());
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentArticleService.updateCommentArticle(updateComment) );
    }

    @Test
    @DisplayName("질문글의 댓글 삭제 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void deleteCommentArticle() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";
        final String content = "기존에 있던 내용";

        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, email);
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        doNothing().when(commentArticleRepository).deleteById(commentId);

        //when, then
        assertDoesNotThrow(() -> commentArticleService.deleteCommentArticle(articleId, commentId));
    }

    @Test
    @DisplayName("질문글의 댓글 삭제 - 댓글 없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void deleteCommentArticle_NotExistComment_Fail() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;

        given(commentArticleRepository.findById(anyLong())).willReturn(null);
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(new Member());

        doNothing().when(commentArticleRepository).deleteById(commentId);

        //when, then
        assertThrows(NullPointerException.class,
                () -> commentArticleService.deleteCommentArticle(articleId, commentId));
    }

    @Test
    @DisplayName("질문글의 댓글 삭제 - 인증 실패")
    @WithMockUser(username = "notEmail@gmail.com", roles = "USER")
    void deleteCommentArticle_authentication_Fail() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";
        final String content = "기존에 있던 내용";

        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, email);
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        doNothing().when(commentArticleRepository).deleteById(commentId);

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentArticleService.deleteCommentArticle(articleId, commentId));
    }

    @Test
    @DisplayName("질문글의 댓글 삭제 - 인증정보 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void deleteCommentArticle_NotExistAuthenticated_Fail() {
        //given
        final Long articleId = 1L;
        final Long commentId = 1L;
        final String content = "기존에 있던 내용";

        CommentArticle comment = createDummyCommentArticle(articleId, commentId, content, null);
        given(commentArticleRepository.findById(anyLong())).willReturn(Optional.of(comment));
        given(memberService.findVerifiedMemberByEmail(anyString())).willReturn(comment.getMember());

        doNothing().when(commentArticleRepository).deleteById(commentId);

        //when, then
        assertThrows(NullPointerException.class,
                () -> commentArticleService.deleteCommentArticle(articleId, commentId));
    }

    @Test
    @DisplayName("질문글의 댓글 조회 - 성공")
    void findCommentArticles() {
        final Long articleId = 1L;
        final Integer page = 1;
        final Integer size = 10;

        given(commentArticleRepository.findCommentArticlesByArticleArticleId(anyLong(), any()))
                .willReturn(new PageImpl<>(List.of(new CommentArticle())));

        assertDoesNotThrow(() -> commentArticleService.findCommentArticles(articleId, page, size));
    }
}