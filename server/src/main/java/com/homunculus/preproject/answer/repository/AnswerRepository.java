package com.homunculus.preproject.answer.repository;

import com.homunculus.preproject.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findById(Long answerId);
    Page<Answer> findAnswersByArticleArticleId(Long articleId, Pageable pageable);
}
