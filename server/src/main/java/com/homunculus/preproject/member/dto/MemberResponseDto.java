package com.homunculus.preproject.member.dto;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.response.details.MemberResponseDetails;
import com.homunculus.preproject.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto extends Auditable {
    private String message;
    private MemberResponseDetails member;

    private Member.MemberStatus status;
    public String getStatus() {
        return status.getStatus();
    }
}
