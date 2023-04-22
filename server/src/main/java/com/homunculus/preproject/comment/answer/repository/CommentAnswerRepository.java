package com.homunculus.preproject.comment.answer.repository;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.comment.answer.entity.CommentAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentAnswerRepository extends JpaRepository<CommentAnswer, Long> {
    Optional<CommentAnswer> findById(Long commentAnswerId);

    Page<CommentAnswer> findCommentAnswersByAnswerAnswerId(Long answerId, Pageable pageable);
}
