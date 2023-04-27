package com.homunculus.preproject.answer.service;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.answer.repository.AnswerRepository;
import com.homunculus.preproject.article.entity.Article;
import com.homunculus.preproject.article.repository.ArticleRepository;
import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.utils.AuthenticationUtils;
import com.homunculus.preproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {

    private final ArticleRepository articleRepository;
    private final AnswerRepository answerRepository;
    private final AuthenticationUtils authenticationUtils;

    public Answer createAnswer(Answer answer) {

        boolean isPostMethod = true;
        answer.setMember(
                authenticationUtils.findMemberWithCheckAllowed(
                        answer.getMember(), isPostMethod,
                        ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED)
        );

        answer.setArticle(
                findVerifiedArticle(answer.getArticle().getArticleId())
        );

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer, true);

        authenticationUtils.findMemberWithCheckAllowed(
                findAnswer.getMember(), false,
                ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED
        );

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
        authenticationUtils.findMemberWithCheckAllowed(
                deletedAnswer.getMember(), false,
                ExceptionCode.ANSWER_MEMBER_NOT_ALLOWED
        );

        answerRepository.deleteById(answerId);

        return deletedAnswer;
    }

    private Answer findVerifiedAnswer(Long articleId, Long answerId) {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);

        Article article = new Article();
        article.setArticleId(articleId);
        answer.setArticle(article);

        return findVerifiedAnswer(answer, true);
    }

    public Answer findVerifiedAnswer(Answer answer, boolean isCheckVerifyArticle) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answer.getAnswerId());

        Answer findAnswer = optionalAnswer.orElseThrow( () ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );

        if(!Objects.equals(findAnswer.getAnswerId(), answer.getAnswerId()))
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_MATCHED);

        if(isCheckVerifyArticle) {
            if (!Objects.equals(findAnswer.getArticle().getArticleId(), answer.getArticle().getArticleId()))
                throw new BusinessLogicException(ExceptionCode.ARTICLE_NOT_MATCHED);
        }
        return findAnswer;
    }

    public Article findVerifiedArticle(long articleId) {
        Optional<Article> optionalArticle =
                articleRepository.findById(articleId);

        return optionalArticle.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ARTICLE_NOT_FOUND));
    }

    public Answer acceptAnswer(Long articleId, Long answerId){

        // 질문글이 존재하는지 확인
        findVerifiedArticle(articleId);
        Answer findAnswer = findVerifiedAnswer(articleId, answerId);

        // 현재 사용자가 질문글 작성자인지 확인
        authenticationUtils.findMemberWithCheckAllowed(
                findAnswer.getArticle().getMember(), false,
                ExceptionCode.ARTICLE_MEMBER_NOT_ALLOWED
        );

        // 답변글이 이미 채택된 경우 예외 발생
        if(findAnswer.getIsAccepted())
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_ACCEPTABLE);
        else
            findAnswer.setIsAccepted(true); // 답변글 채택 처리

        return answerRepository.save(findAnswer);
    }
}
