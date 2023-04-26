package com.homunculus.preproject.utils;

import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationUtils {

    private final MemberService memberService;

    public Member findMemberWithCheckAllowed(Member member, boolean isPostMethod, ExceptionCode ec) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER);
        }

        Object principal = authentication.getPrincipal();
        String email = principal.toString();

        // todo : role 추가 시 권한에 따른 등록 방식 추가해야함

        // post 가 아니라면 작성자가 맞는지 체크
        if (!isPostMethod) {
            if (!member.getEmail().equals(email)) {
                throw new BusinessLogicException(ec);
            }
        }

        return memberService.findVerifiedMemberByEmail(email);
    }
}
