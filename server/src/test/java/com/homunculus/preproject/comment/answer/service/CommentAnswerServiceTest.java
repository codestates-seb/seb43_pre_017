package com.homunculus.preproject.comment.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.repository.CommentAnswerRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.member.entity.Member;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
class CommentAnswerServiceTest {

    @Mock
    CommentAnswerRepository commentAnswerRepository;

    @InjectMocks
    CommentAnswerService commentAnswerService;

    @Test
    @DisplayName("답변에 대한 댓글 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void createCommentAnswer() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;

        final String content = "댓글의 내용";
        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, null);

        given(commentAnswerRepository.save(comment)).willReturn(comment);

        //when, then
        assertDoesNotThrow(() -> commentAnswerService.createCommentAnswer(comment));
    }

    private static CommentAnswer createDummyCommentAnswer(Long answerId, Long commentId,
                                                          String content, String email) {
        CommentAnswer comment = new CommentAnswer();
        comment.setCommentAnswerId(commentId);
        comment.setContent(content);

        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        comment.setAnswer(answer);

        Member member = new Member();
        member.setEmail(email);
        comment.setMember(member);

        return comment;
    }

    @Test
    @DisplayName("답변글에 대한 댓글 수정 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void updateCommentAnswerTest() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentAnswer updateComment = createDummyCommentAnswer(answerId, commentId, updateContent, email);

        given(commentAnswerRepository.save(comment)).willReturn(new CommentAnswer());
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        //when
        CommentAnswer updatedComment = commentAnswerService.updateCommentAnswer(updateComment);

        //then
        assertEquals(updatedComment.getCommentAnswerId(), updateComment.getCommentAnswerId());
        assertEquals(updatedComment.getContent(), updateComment.getContent());
        assertEquals(updatedComment.getAnswer().getAnswerId(), updateComment.getAnswer().getAnswerId());
    }

    @Test
    @DisplayName("답변글에 대한 댓글 수정 - 인증 실패")
    @WithMockUser(username = "notEmail@gmail.com", roles = "USER")
    void updateCommentAnswerTest_authentication_Fail() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentAnswer updateComment = createDummyCommentAnswer(answerId, commentId, updateContent, email);

        given(commentAnswerRepository.save(comment)).willReturn(new CommentAnswer());
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentAnswerService.updateCommentAnswer(updateComment) );
    }

    @Test
    @DisplayName("답변글에 대한 댓글 수정 - 인증정보 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void updateCommentAnswerTest_NotExistAuthentication_Fail() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;

        final String content = "댓글의 내용";
        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, null);

        final String updateContent = "변경된 댓글의 내용";
        CommentAnswer updateComment = createDummyCommentAnswer(answerId, commentId, updateContent, null);

        given(commentAnswerRepository.save(comment)).willReturn(new CommentAnswer());
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        //when, then
        assertThrows(NullPointerException.class,
                () -> commentAnswerService.updateCommentAnswer(updateComment) );
    }

    @Test
    @DisplayName("답변글에 대한 댓글 수정 - 댓글 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void updateCommentAnswerTest_NotExistComment_Fail() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentAnswer updateComment = createDummyCommentAnswer(answerId, null, updateContent, email);

        given(commentAnswerRepository.save(comment)).willReturn(new CommentAnswer());
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentAnswerService.updateCommentAnswer(updateComment) );
    }

    @Test
    @DisplayName("답변글에 대한 댓글 수정 - 답변글 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void updateCommentAnswerTest_NotExistAnswer_Fail() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";

        final String content = "댓글의 내용";
        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, email);

        final String updateContent = "변경된 댓글의 내용";
        CommentAnswer updateComment = createDummyCommentAnswer(null, commentId, updateContent, email);

        given(commentAnswerRepository.save(comment)).willReturn(new CommentAnswer());
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentAnswerService.updateCommentAnswer(updateComment) );
    }

    @Test
    @DisplayName("답변글의 댓글 삭제 - 성공")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void deleteCommentAnswer() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";
        final String content = "기존에 있던 내용";

        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, email);
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        doNothing().when(commentAnswerRepository).deleteById(commentId);

        //when, then
        assertDoesNotThrow(() -> commentAnswerService.deleteCommentAnswer(answerId, commentId));
    }

    @Test
    @DisplayName("답변글의 댓글 삭제 - 댓글 없음 실패")
    @WithMockUser(username = "email@gmail.com", roles = "USER")
    void deleteCommentAnswer_NotExistComment_Fail() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;

        given(commentAnswerRepository.findById(anyLong())).willReturn(null);

        doNothing().when(commentAnswerRepository).deleteById(commentId);

        //when, then
        assertThrows(NullPointerException.class,
                () -> commentAnswerService.deleteCommentAnswer(answerId, commentId));
    }

    @Test
    @DisplayName("답변글의 댓글 삭제 - 인증 실패")
    @WithMockUser(username = "notEmail@gmail.com", roles = "USER")
    void deleteCommentAnswer_authentication_Fail() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String email = "email@gmail.com";
        final String content = "기존에 있던 내용";

        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, email);
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        doNothing().when(commentAnswerRepository).deleteById(commentId);

        //when, then
        assertThrows(BusinessLogicException.class,
                () -> commentAnswerService.deleteCommentAnswer(answerId, commentId));
    }

    @Test
    @DisplayName("답변글의 댓글 삭제 - 인증정보 없음 실패")
    @WithMockUser(username = "", roles = "USER")
    void deleteCommentAnswer_NotExistAuthenticated_Fail() {
        //given
        final Long answerId = 1L;
        final Long commentId = 1L;
        final String content = "기존에 있던 내용";

        CommentAnswer comment = createDummyCommentAnswer(answerId, commentId, content, null);
        given(commentAnswerRepository.findById(anyLong())).willReturn(Optional.of(comment));

        doNothing().when(commentAnswerRepository).deleteById(commentId);

        //when, then
        assertThrows(NullPointerException.class,
                () -> commentAnswerService.deleteCommentAnswer(answerId, commentId));
    }

    @Test
    @DisplayName("답변글의 댓글 조회 - 성공")
    void findCommentAnswers() {
        final Long answerId = 1L;
        final Integer page = 1;
        final Integer size = 10;

        given(commentAnswerRepository.findCommentAnswersByAnswerAnswerId(anyLong(), any()))
                .willReturn(new PageImpl<>(List.of(new CommentAnswer())));

        assertDoesNotThrow(() -> commentAnswerService.findCommentAnswers(answerId, page, size));
    }
}