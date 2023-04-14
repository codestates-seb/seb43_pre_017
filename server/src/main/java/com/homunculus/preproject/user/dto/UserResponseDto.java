package com.homunculus.preproject.user.dto;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto extends Auditable {

    private Long UserId;
    private String email;
    private String name;
    private String phone;
    private User.UserStatus userStatus;

    public String getUserStatus() {
        return userStatus.getStatus();
    }
}
