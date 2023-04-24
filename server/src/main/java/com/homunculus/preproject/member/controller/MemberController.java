package com.homunculus.preproject.member.controller;

import com.homunculus.preproject.member.dto.MemberDto;
import com.homunculus.preproject.member.dto.MemberResponseDto;
import com.homunculus.preproject.member.dto.MemberSimpleResponseDto;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.mapper.MemberMapper;
import com.homunculus.preproject.member.service.MemberService;
import com.homunculus.preproject.utils.UriCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {
    private static final String MEMBER_DEFAULT_URL = "/api/member";
    private static final String MEMBER_ALL_MAPPING_URL = "/api/members";

    private final MemberService memberService;
    private final MemberMapper mapper;

    @GetMapping("/")
    public ResponseEntity home(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/api/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post memberDtoPost) {
        Member member = mapper.memberPostDtoToMember(memberDtoPost);
        Member createdMember = memberService.createMember(member);

        URI location = UriCreator.createUri("/api/member", member.getMemberId());

        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PatchMapping(MEMBER_DEFAULT_URL + "/{memberId}")
    public ResponseEntity patchMember(@PathVariable("memberId") @Positive Long memberId,
                                      @Valid @RequestBody MemberDto.Patch memberDtoPatch) {
        memberDtoPatch.setMemberId(memberId);
        Member member = mapper.memberPatchDtoToMember(memberDtoPatch);
        Member updateMember = memberService.updateMember(member);

        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(member);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(MEMBER_DEFAULT_URL + "/{memberId}")
    public ResponseEntity getMember(@PathVariable("memberId") @Positive Long memberId) {
        Member findedmember = memberService.findMember(memberId);

        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(findedmember);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(MEMBER_ALL_MAPPING_URL)
    public ResponseEntity getMember(@RequestParam @Positive int page,
                                    @RequestParam @Positive int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();

        List<MemberResponseDto> responseDtos = mapper.membersToMemberResponseDtos(members);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @DeleteMapping(MEMBER_DEFAULT_URL + "/{memberId}")
    public ResponseEntity deleteUser(@PathVariable("memberId") @Positive Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
