package com.homunculus.preproject.member.mapper;

import com.homunculus.preproject.member.dto.MemberDto;
import com.homunculus.preproject.member.dto.MemberResponseDto;
import com.homunculus.preproject.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post memberDtoPost);
    Member memberPatchDtoToMember(MemberDto.Patch memberDtoPatch);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
