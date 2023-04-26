package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.service.MemberService;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {

    private final ArticleRepository articleRepository;
    private final AnswerRepository answerRepository;
    private final MemberService memberService;

    public Answer createAnswer(Answer answer) {

        answer.setArticle(
                findVerifiedArticle(answer.getArticle().getArticleId())
        );

        answer.setMember(
            checkAllowedMember(answer.getMember(), false, true)
        );

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer);

        try {
            checkAllowedMember(findAnswer.getMember(), true, false);
        } catch (BusinessLogicException e) {
            if (e.getExceptionCode() == ExceptionCode.MEMBER_NOT_ALLOWED) {
                throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
            } else {
                throw new BusinessLogicException(ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED);
            }
        }

        CustomBeanUtils.copyNonNullProperties(answer, findAnswer);

        return answerRepository.save(findAnswer);
    }

    public Page<Answer> findAnswers(Long articleId, Integer page, Integer size) {
        return answerRepository.findAnswersByArticleArticleId(
                articleId,
                PageRequest.of(page, size, Sort.by("answerId").descending())
        );
    }

    public Answer deleteAnswer(Long articleId, Long answerId) {
        // 유효성 확인
        Answer deletedAnswer = findVerifiedAnswer(articleId, answerId);

        // 삭제 권한 확인
        checkAllowedMember(deletedAnswer.getMember(), true, true);

        answerRepository.deleteById(answerId);

        return deletedAnswer;
    }
    public void checkAllowedMember (Member member, boolean isArticleChecking) {
        checkAllowedMember(member, isArticleChecking, false);
    }

    public Member checkAllowedMember (Member member, boolean isArticleChecking, boolean isAnswerPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserDetails)) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
        }

        UserDetails userDetails = (UserDetails) principal;
        String email = userDetails.getUsername();

        if (!isAnswerPost && member != null && email != null && !member.getEmail().equals(email)) {
            if (isArticleChecking) {
                throw new BusinessLogicException(ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED);
            } else {
                throw new BusinessLogicException(ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED);
            }
        }

        return memberService.findVerifiedMemberByEmail(email);
    }

    private Answer findVerifiedAnswer(Long articleId, Long answerId) {

        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        if (optionalAnswer.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
        }

        Answer answer = optionalAnswer.get();

        if (!Objects.equals(answer.getArticle().getArticleId(), articleId)) {
            throw new BusinessLogicException(ExceptionCode.ARTICLE_NOT_MATCHED);
        }

        return findVerifiedAnswer(answer);
    }

    public Answer findVerifiedAnswer(Answer answer) {

        Optional<Answer> optionalAnswer = answerRepository.findById(answer.getAnswerId());
        if (answer == null || optionalAnswer.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
        }

        Answer foundAnswer = optionalAnswer.get();
        if (!Objects.equals(foundAnswer.getArticle().getArticleId(), answer.getArticle().getArticleId())) {
            throw new BusinessLogicException(ExceptionCode.ARTICLE_NOT_MATCHED);
        }

        return foundAnswer;
    }

    public Article findVerifiedArticle(long articleId) {
        Optional<Article> optionalArticle =
                articleRepository.findById(articleId);
        Article findArticle =
                optionalArticle.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND));
        return findArticle;
    }

    public Answer acceptAnswer(Long articleId, Long answerId){

        // 질문글이 존재하는지 확인
        findVerifiedArticle(articleId);
        Answer findAnswer = findVerifiedAnswer(articleId, answerId);
        // 현재 사용자가 질문글 작성자인지 확인
        checkAllowedMember(findAnswer.getArticle().getMember(), true);

        if (findAnswer == null) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
        }
        // 답변글이 이미 채택된 경우 예외 발생
        if (findAnswer.getIsAccepted()) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_ACCEPTABLE);
        }
        // 답변글 채택 처리
        findAnswer.setIsAccepted(true);
        return answerRepository.save(findAnswer);
    }
}
