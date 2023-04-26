package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.repository.MemberRepository;
import com.homunculus.preproject.member.service.MemberService;
import com.homunculus.preproject.utils.AuthenticationUtils;
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
    private final MemberRepository memberRepository;

    private final MemberService memberService;
    private final AuthenticationUtils authenticationUtils;


    public Answer createAnswer(Answer answer) {

        answer.setArticle(
                findVerifiedArticle(answer.getArticle().getArticleId())
        );

        answer.setMember(
                authenticationUtils.findMemberWithCheckAllowed(
                        answer.getMember(), true,
                        ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED)
        );

        Member member = answer.getMember();

        if (member.getEmail() != null) { // member가 이미 DB에 저장되어 있는지 확인
            Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
            if (optionalMember.isPresent()) {
                // 이미 저장된 member를 사용
                answer.setMember(optionalMember.get());
            } else {
                // member가 DB에 없으면 새로 저장
                answer.setMember(memberRepository.save(member));
            }
        } else {
            // member email이 없으면 새로 저장
            answer.setMember(memberRepository.save(member));
        }

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {

        Answer findAnswer = findVerifiedAnswer(answer);

        try {
            authenticationUtils.findMemberWithCheckAllowed(
                    answer.getMember(), false,
                    ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED);
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

        Answer deletedAnswer = findVerifiedAnswer(articleId, answerId);

        authenticationUtils.findMemberWithCheckAllowed(
                deletedAnswer.getMember(), false,
                ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED);

        answerRepository.deleteById(answerId);

        return deletedAnswer;
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

        findVerifiedArticle(articleId);
        Answer findAnswer = findVerifiedAnswer(articleId, answerId);

        authenticationUtils.findMemberWithCheckAllowed(
                findAnswer.getMember(), false,
                ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED);

        if (findAnswer == null) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
        }

        if (findAnswer.getIsAccepted()) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_ACCEPTABLE);
        }

        findAnswer.setIsAccepted(true);
        return answerRepository.save(findAnswer);
    }
}
