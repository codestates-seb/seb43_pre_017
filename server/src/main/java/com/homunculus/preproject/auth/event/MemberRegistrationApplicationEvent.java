package com.homunculus.preproject.auth.event;

import com.homunculus.preproject.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberRegistrationApplicationEvent {
    private final Member member;
    public MemberRegistrationApplicationEvent(Member member) {
        this.member = member;
    }
}