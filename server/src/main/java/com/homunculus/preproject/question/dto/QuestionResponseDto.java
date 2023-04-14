package com.homunculus.preproject.question.dto;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.user.entity.User;

public class QuestionResponseDto extends Auditable {
    private Long questionId;
    private String title;
    private String content;
    private Long viewCount;
    // private List<Answer> answers;  fixme : Answer 추가 후 수정해야함
    private User user;
}
