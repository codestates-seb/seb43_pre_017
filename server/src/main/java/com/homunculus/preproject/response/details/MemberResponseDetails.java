package com.homunculus.preproject.response.details;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.member.entity.Member;

import java.time.LocalDateTime;

public class MemberResponseDetails extends Auditable {
    private Long memberId;
    private String name;
    public String getStatus() { return status.getStatus(); }
    private Member.MemberStatus status;
}
