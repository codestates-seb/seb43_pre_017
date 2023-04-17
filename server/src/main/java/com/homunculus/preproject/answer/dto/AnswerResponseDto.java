package com.homunculus.preproject.answer.dto;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.response.details.AnswerResponseDetails;

import java.util.List;

public class AnswerResponseDto {
    private String message;
    private Integer answersCount;    // todo : FE 와 상의 필요
    private List<AnswerResponseDetails> answers;
}
