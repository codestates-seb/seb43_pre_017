package com.homunculus.preproject.comment.answer.entity;

import com.homunculus.preproject.answer.entity.Answer;
import com.homunculus.preproject.audit.Auditable;
import com.homunculus.preproject.member.entity.Member;
import com.homunculus.preproject.member.entity.MemberDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CommentAnswer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "MEMBERDETAILS_ID")
    private MemberDetails memberDetails;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CommentAnswerStatus commentAnswerStatus = CommentAnswerStatus.COMMENT_ANSWER_REGISTRY;

    public enum CommentAnswerStatus {
        COMMENT_ANSWER_REGISTRY("등록상태"),
        COMMENT_ANSWER_DELETE("삭제상태");

        private @Getter String status;

        CommentAnswerStatus(String status) {
            this.status = status;
        }
    }
}
