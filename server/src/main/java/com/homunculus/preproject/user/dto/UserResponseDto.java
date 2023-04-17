package com.homunculus.preproject.user.dto;

import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.response.details.UserResponseDetails;
import com.homunculus.preproject.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto extends Auditable {
    private String message;
    private UserResponseDetails user;

    private User.UserStatus status;
    public String getStatus() {
        return status.getStatus();
    }
}
