package com.homunculus.preproject.answer.dto;

import com.homunculus.preproject.audit.Auditable;

public class AnswerResponseDto extends Auditable {
    private Long answerId;
    private String title;
    private String content;
}
