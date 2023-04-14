package com.homunculus.preproject.user.dto;

import com.homunculus.preproject.user.entity.User;
import com.homunculus.preproject.validator.NotSpace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;

        @NotBlank(message = "이름은 공백이 아니어야 합니다 !")
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'으로 구성되어야 합니다.")
        private String phone;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long userId;

        @NotNull    // fixme : 회원 정보 수정할 때는 비밀번호가 필수 !
        private String password;

        @NotSpace(message = "이름은 공백이 아니어야 합니다 !")
        private String name;

        @NotSpace(message = "휴대폰 번호는 공백이 아니어야 합니다 !")
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'으로 구성되어야 합니다.")
        private String phone;

        private User.UserStatus userStatus;
    }

}
