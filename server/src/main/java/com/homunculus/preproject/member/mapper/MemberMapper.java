package com.homunculus.preproject.member.mapper;

import com.homunculus.preproject.member.dto.MemberDto;
import com.homunculus.preproject.member.dto.MemberResponseDto;
import com.homunculus.preproject.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(source = "name", target = "userName")
    Member memberPostDtoToMember(MemberDto.Post memberDtoPost);

    @Mapping(source = "name", target = "userName")
    Member memberPatchDtoToMember(MemberDto.Patch memberDtoPatch);

    default MemberResponseDto memberToMemberResponseDto(Member member){
        MemberResponseDto response = new MemberResponseDto();
        response.setMemberId(member.getMemberId());
        response.setEmail(member.getEmail());
        response.setName(member.getUserName());
        response.setCreatedAt(member.getCreatedAt());
        response.setUpdatedAt(member.getUpdatedAt());

        return response;
    };
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
