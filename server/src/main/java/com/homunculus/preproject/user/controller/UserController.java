package com.homunculus.preproject.user.controller;

import com.homunculus.preproject.dto.MultiResponseDto;
import com.homunculus.preproject.user.dto.UserDto;
import com.homunculus.preproject.user.dto.UserResponseDto;
import com.homunculus.preproject.user.entity.User;
import com.homunculus.preproject.user.mapper.UserMapper;
import com.homunculus.preproject.user.service.UserService;
import com.homunculus.preproject.utils.UriCreator;
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
public class UserController {
    private static final String USER_DEFAULT_URL = "/api/user";
    private static final String USER_ALL_MAPPING_URL = "/api/users";

    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping(USER_DEFAULT_URL)
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post userDtoPost) {
        User user = userService.createUser(mapper.userPostDtoToUser(userDtoPost));

        URI location = UriCreator.createUri(USER_DEFAULT_URL, user.getUserId());

        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PatchMapping(USER_DEFAULT_URL + "/{user-id}")
    public ResponseEntity patchUser(@PathVariable("user-id") @Positive Long userId,
                                    @Valid @RequestBody UserDto.Patch userDtoPatch) {
        userDtoPatch.setUserId(userId);
        User user = userService.updateUser(mapper.userPatchDtoToUser(userDtoPatch));

        UserResponseDto responseDto = mapper.userToUserResponseDto(user);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(USER_DEFAULT_URL + "/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive Long userId) {
        User user = userService.findUser(userId);

        UserResponseDto responseDto = mapper.userToUserResponseDto(user);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(USER_ALL_MAPPING_URL)
    public ResponseEntity getUser(@RequestParam @Positive int page,
                                  @RequestParam @Positive int size) {
        Page<User> pageUsers = userService.findUsers(page - 1, size);
        List<User> users = pageUsers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.usersToUserResponseDtos(users), pageUsers),
                HttpStatus.OK);
    }

    @DeleteMapping(USER_DEFAULT_URL + "/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
