package com.homunculus.preproject.user.entity;

import com.homunculus.preproject.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50)
    private String password;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserStatus userStatus = UserStatus.USER_ACTIVE;

    public enum UserStatus {
        USER_ACTIVE("활동상태"),
        USER_SLEEP("휴면상태"),
        USER_QUIT("탈퇴상태");

        private final @Getter String status;

        UserStatus(String status) {
            this.status = status;
        }
    }
}
