package com.homunculus.preproject.member.dto;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.response.details.MemberResponseDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
