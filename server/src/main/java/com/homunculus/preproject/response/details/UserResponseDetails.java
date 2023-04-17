package com.homunculus.preproject.response.details;

import com.homunculus.preproject.user.entity.User;

import java.time.LocalDateTime;

public class UserResponseDetails {
    private Long id;
    private String name;

    private LocalDateTime createdAt;    // todo : 가입날짜

    private User.UserStatus status;
    public String getStatus() { return status.getStatus(); }
}
