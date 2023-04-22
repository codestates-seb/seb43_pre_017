package com.homunculus.preproject.comment.answer.mapper;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerDto;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerSimpleResponseDto;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import com.homunculus.preproject.comment.answer.dto.CommentAnswerResponseDto;
import com.homunculus.preproject.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentAnswerMapper {
    default CommentAnswer commentAnswerPostDtoToCommentAnswer(CommentAnswerDto.Post commentDtoPost) {
        CommentAnswer result = new CommentAnswer();
        result.setContent(commentDtoPost.getContent());

        Answer resultAnswer = new Answer();
        resultAnswer.setAnswerId(commentDtoPost.getAnswerId());
        result.setAnswer(resultAnswer);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername();

        Member member = new Member();
        member.setEmail(email);
        result.setMember(member);

        return result;
    };

    default CommentAnswer commentAnswerPatchDtoToCommentAnswer(CommentAnswerDto.Patch commentDtoPatch) {
        CommentAnswer result = new CommentAnswer();
        result.setCommentAnswerId(commentDtoPatch.getCommentId());
        result.setContent(commentDtoPatch.getContent());

        Answer resultAnswer = new Answer();
        resultAnswer.setAnswerId(commentDtoPatch.getAnswerId());
        result.setAnswer(resultAnswer);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername();

        Member member = new Member();
        member.setEmail(email);
        result.setMember(member);

        return result;
    }

    default CommentAnswerResponseDto commentAnswersToCommentAnswerResponseDto(Long answerId, List<CommentAnswer> commentAnswers) {
        CommentAnswerResponseDto result = new CommentAnswerResponseDto();
        result.setMessage("댓글들 조회를 완료했습니다.");
        result.setMessageCount(commentAnswers.size());
        result.setAnswerId(answerId);

        List<CommentAnswerResponseDto.Comments> resultComments = new ArrayList<>();
        {
            for(CommentAnswer src : commentAnswers) {
                CommentAnswerResponseDto.Comments comment = new CommentAnswerResponseDto.Comments();
                comment.setId(src.getCommentAnswerId());
                comment.setContent(src.getContent());

                CommentAnswerResponseDto.Comments.Member member = new CommentAnswerResponseDto.Comments.Member();
                member.setId(src.getMember().getMemberId());
                member.setName(src.getMember().getName());
                comment.setMember(member);

                comment.setCreatedAt(src.getCreatedAt());
                comment.setUpdatedAt(src.getUpdatedAt());
                resultComments.add(comment);
            }
        }
        result.setComments(resultComments);

        return result;
    }

    default CommentAnswerSimpleResponseDto commentAnswerToCommentAnswerSimpleResponseDto(CommentAnswer commentAnswer,
                                                                                         CommentAnswerSimpleResponseMessages commentAnswerSimpleResponseMessages) {
        CommentAnswerSimpleResponseDto responseDto = new CommentAnswerSimpleResponseDto();
        responseDto.setMessage(commentAnswerSimpleResponseMessages.getMessage());
        responseDto.setAnswerId(commentAnswer.getAnswer().getAnswerId());
        responseDto.setCommentId(commentAnswer.getCommentAnswerId());

        return responseDto;
    }

}
