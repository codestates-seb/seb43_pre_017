package com.homunculus.preproject.response.details;

import com.homunculus.preproject.member.entity.Member;

import java.time.LocalDateTime;

public class MemberResponseDetails {
    private Long id;
    private String name;

    private LocalDateTime createdAt;    // todo : 가입날짜

    private Member.MemberStatus status;
    public String getStatus() { return status.getStatus(); }
}
