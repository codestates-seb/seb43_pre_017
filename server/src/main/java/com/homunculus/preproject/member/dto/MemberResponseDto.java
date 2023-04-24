package com.homunculus.preproject.member.dto;

import com.homunculus.preproject.response.details.MemberResponseDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private String memberId;
    private String message;
    private MemberResponseDetails member;

}
