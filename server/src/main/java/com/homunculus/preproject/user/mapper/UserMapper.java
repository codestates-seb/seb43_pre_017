package com.homunculus.preproject.user.mapper;

import com.homunculus.preproject.user.dto.UserDto;
import com.homunculus.preproject.user.dto.UserResponseDto;
import com.homunculus.preproject.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userPostDtoToUser(UserDto.Post userDtoPost);
    User userPatchDtoToUser(UserDto.Patch userDtoPatch);
    UserResponseDto userToUserResponseDto(User user);
    List<UserResponseDto> usersToUserResponseDtos(List<User> users);
}
