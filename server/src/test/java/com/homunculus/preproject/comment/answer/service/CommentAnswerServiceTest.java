package com.homunculus.preproject.comment.answer.service;

import com.homunculus.preproject.comment.answer.repository.CommentAnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CommentAnswerServiceTest {

    @Mock
    CommentAnswerRepository commentAnswerRepository;

    @InjectMocks
    CommentAnswerService commentAnswerService;

    @Test
    void createCommentAnswer() {
    }

    @Test
    void updateCommentAnswer() {
    }

    @Test
    void deleteCommentAnswer() {
    }

    @Test
    void findCommentAnswers() {
    }
}