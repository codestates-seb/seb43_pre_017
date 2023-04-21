package com.homunculus.preproject.member.entity;

import com.homunculus.preproject.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50)
    private String password;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @OneToOne(mappedBy = "member")
    private MemberDetails memberDetails;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public enum MemberRole {
        ROLE_USER,
        ROLE_ADMIN
    }

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public enum MemberStatus {
        MEMBER_ACTIVE("활동상태"),
        MEMBER_SLEEP("휴면상태"),
        MEMBER_QUIT("탈퇴상태");

        private final @Getter String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
