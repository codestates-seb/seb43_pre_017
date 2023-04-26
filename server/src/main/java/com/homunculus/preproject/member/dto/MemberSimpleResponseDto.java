package com.homunculus.preproject.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MemberSimpleResponseDto {
    private Long memberId;
    private String message;
}
